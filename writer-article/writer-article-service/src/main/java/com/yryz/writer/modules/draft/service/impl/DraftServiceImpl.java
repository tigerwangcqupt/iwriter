package com.yryz.writer.modules.draft.service.impl;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.redis.utils.StringUtils;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.DateUtil;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.draft.dao.persistence.DraftAuditDao;
import com.yryz.writer.modules.draft.dao.persistence.DraftDao;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.entity.DraftAudit;
import com.yryz.writer.modules.draft.service.DraftService;
import com.yryz.writer.modules.draft.vo.DraftVo;
import com.yryz.writer.modules.draft.vo.UserVo;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.task.dao.persistence.TaskDao;
import com.yryz.writer.modules.task.entity.Task;
import com.yryz.writer.modules.task.vo.AppVo;
import com.yryz.writer.modules.task.vo.TaskVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


@Service
@Transactional
public class DraftServiceImpl extends BaseServiceImpl implements DraftService {

    @Autowired
    private DraftDao draftDao;

    @Autowired
    private DraftAuditDao draftAuditDao;

    @Autowired
    private IdAPI idAPI;

    @Autowired
    private TaskDao taskDao;

    protected BaseDao getDao() {
        return draftDao;
    }

    /**
     * 最大审核次数
     */
    private static final int EDIT_MAX_COUNT = 3;

    @Override
    public <T> int update(T t) {

        /**
         * 查询草稿记录
         * 判断草稿记录审核次数
         * 判断当前审核人员是否为指派人员
         * 新增审核日志记录
         */
        Draft in = (Draft) t;


        /**
         * 审核
         */
        if(in.getDraftStatus() != null){

            //查询
            Draft dbRecord = draftDao.selectByKid(Draft.class,in.getKid());
            if(dbRecord == null){
                throw new YyrzPcException("","未查询到相关草稿信息","");
            }

            //判断审核人员是否一致
            String inAuditorUserId = in.getAuditorUserId();
            if(!inAuditorUserId.equalsIgnoreCase(dbRecord.getAuditorUserId())){
                throw new YyrzPcException("","非指定审核人员不允许操作","");
            }

            //转换
            DraftAudit audit = new DraftAudit();
            try {
                BeanUtils.copyProperties(audit,dbRecord);
                audit.setKid(idAPI.getId("yryz_draft_audit_log"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            //设置 审核状态，审核人员,取值页面上述状态
            audit.setAuditStatus(in.getDraftStatus());
            audit.setCreateUserId(in.getAuditorUserId());
            audit.setLastUpdateUserId(in.getAuditorUserId());
            audit.setDraftKid(dbRecord.getKid());

            audit.setReason(in.getReason());
            audit.setSuggest(in.getSuggest());
            audit.setAuditRemark(in.getAuditRemark());

            audit.setDelFlag(0);
            audit.setShelveFlag(0);

            if(StringUtils.isBlank(audit.getReason())){
                audit.setReason("");
            }
            if(StringUtils.isBlank(audit.getSuggest())){
                audit.setSuggest("");
            }
            if(StringUtils.isBlank(audit.getAuditRemark())){
                audit.setAuditRemark("");
            }
            //保存日志记录
            draftAuditDao.insert(audit);
            //保存审核时间
            in.setAuditDate(new Date());
        }
        return super.update(in);
    }


    public Map<Long,List<DraftAudit>> getDraftAudits(List<Long> kids){

        Map<Long,List<DraftAudit>> map = new HashMap<>();
        if(CollectionUtils.isEmpty(kids)){
            return map;
        }

        List<DraftAudit> list = draftAuditDao.selectByDraftKids(kids);
        if(list==null){
            return map;
        }

        List<DraftAudit> group = null;
        for (DraftAudit audit : list) {
            group = map.get(audit.getDraftKid());
            if(group==null){
                group = new ArrayList<>();
                map.put(audit.getDraftKid(),group);
            }
            group.add(audit);
        }
        return  map;
    }

    public PageList<DraftVo> selectList(DraftDto draftDto) {
        PageUtils.startPage(draftDto.getCurrentPage(), draftDto.getPageSize());

        List<Long> createUserIds = new ArrayList<>();
        String createUserId = draftDto.getCreateUserId();
        if (StringUtils.isNotBlank(createUserId)) {
            createUserIds.add(Long.valueOf(createUserId));
            draftDto.setCreateUserIds(createUserIds);
        } else if (StringUtils.isNotBlank(draftDto.getNickName()) || StringUtils.isNotBlank(draftDto.getUserName()) || StringUtils.isNotBlank(draftDto.getRemark()) || StringUtils.isNotBlank(draftDto.getPhone())) {
            createUserIds = draftDao.selectWriter(draftDto);
            draftDto.setCreateUserIds(createUserIds);
        }
        //
        Map<Long,List<DraftAudit>> auditLogMap = new HashMap<>();

        //获取状态,已发表需查询文章表,其余查询稿件表
        //列表参数:0 全部,1 已发表,2 未通过,3 草稿,4 管理后台待审核,5 管理后台未通过 6，待上架 7，审核中，
        Integer status = draftDto.getStatus();
        List<Draft> list = new ArrayList<>();
        switch (status) {
            case 0:
                list = draftDao.selectList(draftDto);
                break;
            case 1:
                list = draftDao.selectPublish(draftDto);
                break;
            case 2:
                list = draftDao.selectNotPass(draftDto);//未通过
                break;
            case 3:
                list = draftDao.selectDraught(draftDto);
                break;
            case 4:     //统一管理端查询
                list = draftDao.selectAuditForAdmin(draftDto);
                List<Long> kids = new ArrayList<>();
                list.forEach(d->{
                    kids.add(d.getKid());
                });
                //查询审核日志表
                auditLogMap = this.getDraftAudits(kids);

                break;
            case 6:
                list = draftDao.selectWaitShelve(draftDto);
                break;
            case 7:
                list = draftDao.selectAudit(draftDto);//审核中
                break;
            default:
                list = draftDao.selectList(draftDto);
                break;
        }


        //查询稿件表
        List<DraftVo> draftVoList = new ArrayList<DraftVo>();
        if (list != null && list.size() > 0) {
            for (Draft draft : list) {
                DraftVo draftVo = toDraftVo(draft);
                //合并审核记录
                draftVo.setDraftAudits(auditLogMap.get(draft.getKid()));
                draftVoList.add(draftVo);
            }
        }
        return new PageModel<DraftVo>().getPageList(list, draftVoList);
    }


    public DraftVo detail(Long draftId) {
        Draft draft = draftDao.selectByKid(Draft.class, draftId);
        DraftVo draftVo = new DraftVo();
        if (draft != null) {
            //Draft to DraftVo
            draftVo = toDraftVo(draft);
        }
        return draftVo;
    }

    @Override
    public Long add(Draft draft) {

        try {
            //判断任务是否被结束
            Integer draftStatus = draft.getDraftStatus();
            Integer taskFlag = draft.getTaskFlag();
            //是任务稿件且是要提交审核(draftStatus=1)时,校验任务状态.
            if (taskFlag != null && taskFlag == 1 && draftStatus != null && draftStatus == 1) {
                Long taskKid = draft.getTaskKid();
                Task task = taskDao.selectByKid(Task.class, taskKid);
                Integer integer = taskDao.selectSubmitWriterNum(taskKid);
                if (integer != null && integer >= task.getTaskCloseNum()) {
                    throw new YyrzPcException(ExceptionEnum.BusiException.getCode(), "任务已经结束", "任务已经结束");
                }
                if (task.getTaskCloseFlag() != null && task.getTaskCloseFlag() == 1) {
                    throw new YyrzPcException(ExceptionEnum.BusiException.getCode(), "任务已经结束", "任务已经结束");
                }
                if (task.getEndDate() != null) {
                    Date date = new Date();
                    Date endDate = task.getEndDate();
                    if (date.after(endDate)) {
                        throw new YyrzPcException(ExceptionEnum.BusiException.getCode(), "任务已经结束", "任务已经结束");
                    }
                }
            }
            Long kid = draft.getKid();

            if (kid == null) {
                kid = idAPI.getId("yryz_draft");
                draft.setKid(kid);
                //新增时，修改次数为1
                draft.setEditCount(1);
                draftDao.insertByPrimaryKeySelective(draft);
            } else {
                /**
                 * 更新逻辑中有存草稿，发表
                 */

                //发表
                if(draft.getDraftStatus()==1){
                    //查询数据(修改次数加1）
                    Draft dbRecord = draftDao.selectByKid(Draft.class,draft.getKid());
                    if(EDIT_MAX_COUNT <= dbRecord.getEditCount()){
                        throw new YyrzPcException(ExceptionEnum.BusiException.getCode(), "已超过稿件最大修改次数", "已超过稿件最大修改次数");
                    }
                    draft.setEditCount(dbRecord.getEditCount()+1);
                }
                draftDao.update(draft);
            }
            return kid;
        } catch (Exception E) {
            throw E;
        }
    }

    @Override
    public Integer setAuditorUser(List<Long> kids, String auditorUserId, String assignerUserId) {
        return draftDao.setAuditorUser(kids,auditorUserId,assignerUserId);
    }

    public List<AppVo> selectAppByAppliName(String appliName) {
        return taskDao.selectAppByAppliName(appliName);
    }

    @Override
    public List<UserVo> selectUserByUserName(String userName) {
        return draftDao.selectUserByUserName(userName);
    }

    @Override
    public DraftVo detailInfo(Long kid) {
        Draft draft = draftDao.selectArticle(kid);
        DraftVo draftVo = new DraftVo();
        if (draft != null) {
            //Draft to DraftVo
            draftVo = toDraftVo(draft);
        }
        return draftVo;
    }

    private DraftVo toDraftVo(Draft draft) {
        DraftVo draftVo = new DraftVo();
        //Draft to DraftVo
        draftVo.setKid(draft.getKid());
        draftVo.setTitle(draft.getTitle());
        draftVo.setDataType(draft.getDataType());
        draftVo.setContentHtml(draft.getContentHtml());
        draftVo.setContentSource(draft.getContentSource());
        draftVo.setCoverImgUrl(draft.getCoverImgUrl());
        draftVo.setDescription(draft.getDescription());
        draftVo.setDraftStatus(draft.getDraftStatus());
        draftVo.setDraftType(draft.getDraftType());
        draftVo.setTaskFlag(draft.getTaskFlag());
        draftVo.setReason(draft.getReason());
        draftVo.setSuggest(draft.getSuggest());
        draftVo.setVideoUrl(draft.getVideoUrl());
        draftVo.setCreateDate(DateUtil.getString(draft.getCreateDate()));
        draftVo.setClassifyName(draft.getClassifyName());
        draftVo.setLabelName(draft.getLabelName());
        draftVo.setShelveFlag(draft.getShelveFlag());
        draftVo.setDraftFee(draft.getDraftFee());
        draftVo.setAuditRemark(draft.getAuditRemark());
        draftVo.setAssignStatus(draft.getAssignStatus());
        draftVo.setAuditorUserId(draft.getAuditorUserId());


        //聚合任务信息
        Long taskKid = draft.getTaskKid();
        if (draft.getTaskFlag() != null && draft.getTaskFlag() == 1) {
            if (taskKid != null && taskKid != 0) {
                Task task = taskDao.selectByKid(Task.class, taskKid);
                if (task != null) {
                    draftVo.setTaskKid(taskKid);
                    draftVo.setTaskTitle(task.getTitle());
                    draftVo.setTaskCreateDate(task.getCreateDate());
                    draftVo.setTaskAcceptTaskNum(task.getAcceptTaskNum());
                    draftVo.setDraftFee(task.getDraftFee());

                    //判断任务是否过期,任务过期将taskStatus置为1
                    Integer WriterNum = taskDao.selectSubmitWriterNum(taskKid);
                    if (WriterNum != null && WriterNum >= task.getTaskCloseNum()) {
                        draftVo.setTaskStatus(1);
                    }
                    if (task.getTaskCloseFlag() != null && task.getTaskCloseFlag() == 1) {
                        draftVo.setTaskStatus(1);
                    }
                    if (task.getEndDate() != null) {
                        Date date = new Date();
                        Date endDate = task.getEndDate();
                        if (date.after(endDate)) {
                            draftVo.setTaskStatus(1);
                        }
                    }

                }
            }
        }

        //聚合应用信息
        Long appId = draft.getAppId();
        if (appId != null && appId != 0) {
            TaskVo app = taskDao.selectAppById(appId);
            if (app != null) {
                draftVo.setAppId(appId);
                draftVo.setAppliName(app.getAppliName());
                draftVo.setIcon(app.getIcon());
            }
        } else if (appId != null && appId == 0) {
            draftVo.setAppliName(draft.getAppName());
        }

        //聚合写手信息
        String createUserId = draft.getCreateUserId();
        if (StringUtils.isNotBlank(createUserId)) {
            draftVo.setWriterId(Long.valueOf(createUserId));
            DraftVo writer = draftDao.selectWriterByKid(createUserId);
            if (writer != null) {
                draftVo.setWriterName(writer.getWriterName());
                draftVo.setWriterNickName(writer.getWriterNickName());
                draftVo.setWriterPhone(writer.getWriterPhone());
                draftVo.setWriterRemark(writer.getWriterRemark());
                draftVo.setWriterHeadImg(writer.getWriterHeadImg());
            }
        }

        //判断数据来源
        //0为稿件表,1为文章表
        Integer dataType = draft.getDataType();
        if (dataType != null && dataType == 1) {
            //获取文章上下架状态,稿件没有上下架
            Integer shelveFlag = draft.getShelveFlag();
            //上架文章,为已发表
            if (shelveFlag != null && shelveFlag == 0) {
                draftVo.setDraftStatus(3);
                Long kid = draft.getKid();
                //上架文章跟专题是否关联并且是否推荐
                List<Integer> integers = draftDao.selectArticleSubject(kid);
                if (CollectionUtils.isNotEmpty(integers)) {
                    draftVo.setSubjectFlag(1);
                    for (Integer i : integers) {
                        if (i == 1) {
                            draftVo.setRecommend(1);
                        }
                    }
                }
                //获取文章表的推荐状态
                Integer recommend = draft.getRecommend();
                if (recommend != null) {
                    if (recommend == 1 || draftVo.getRecommend() == 1) {
                        draftVo.setRecommend(1);
                    }
                }

                //上架文章聚合统计数据
                DraftVo data = draftDao.selectArticleData(kid);
                if (data != null) {
                    draftVo.setVisitQty(data.getVisitQty());
                    draftVo.setCommentQty(data.getCommentQty());
                    draftVo.setShareQty(data.getShareQty());
                    draftVo.setCollectQty(data.getCollectQty());
                }
            } else if (shelveFlag != null && shelveFlag == 1) {
                draftVo.setWaitShelve(draft.getWaitShelve());
                draftVo.setDraftStatus(4);
            }
        }
        return draftVo;
    }
}

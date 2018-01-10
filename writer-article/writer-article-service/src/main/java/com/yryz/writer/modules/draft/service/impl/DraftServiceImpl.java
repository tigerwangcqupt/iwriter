package com.yryz.writer.modules.draft.service.impl;

import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.redis.utils.StringUtils;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.DateUtil;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.draft.dao.persistence.DraftDao;
import com.yryz.writer.modules.draft.dto.DraftDto;
import com.yryz.writer.modules.draft.entity.Draft;
import com.yryz.writer.modules.draft.service.DraftService;
import com.yryz.writer.modules.draft.vo.DraftVo;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.task.TaskApi;
import com.yryz.writer.modules.task.dao.persistence.TaskDao;
import com.yryz.writer.modules.task.entity.Task;
import com.yryz.writer.modules.task.service.TaskService;
import com.yryz.writer.modules.task.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DraftServiceImpl extends BaseServiceImpl implements DraftService {

    @Autowired
    private DraftDao draftDao;

    @Autowired
    private IdAPI idAPI;

    @Autowired
    private TaskDao taskDao;

    protected BaseDao getDao() {
        return draftDao;
    }

    public PageList<DraftVo> selectList(DraftDto draftDto) {
        PageUtils.startPage(draftDto.getCurrentPage(), draftDto.getPageSize());

        //获取状态,已发表需查询文章表,其余查询稿件表
        //列表参数:0 全部,1 已发表,2 未通过,3 草稿,4 管理后台待审核,5 管理后台未通过
        Integer status = draftDto.getStatus();
        List<Draft> list;
        switch (status) {
            case 0:
                list = draftDao.selectList(draftDto);
                break;
            case 1:
                list = draftDao.selectPublish(draftDto);
                break;
            case 2:
                list = draftDao.selectNotPass(draftDto);
                break;
            case 3:
                list = draftDao.selectDraught(draftDto);
                break;
            case 4:
                list = draftDao.selectPendingForAdmin(draftDto);
                break;
            case 5:
                list = draftDao.selectNotPassForAdmin(draftDto);
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
                draftVoList.add(draftVo);
            }
        }
        return new PageModel<DraftVo>().getPageList(draftVoList);
    }


    public DraftVo detail(Long draftId) {
        Draft draft = draftDao.selectByKid(Draft.class, draftId);
        DraftVo draftVo = new DraftVo();
        if (draft != null) {
            //Draft to DraftVo
            draftVo = toDraftVo(draft);
            Long taskKid = draft.getTaskKid();
            if (taskKid != null && taskKid != 0) {
                Task task = taskDao.selectByKid(Task.class, taskKid);
                if (task != null) {
                    draftVo.setTaskTitle(task.getTitle());
                    draftVo.setTaskCreateDate(task.getCreateDate());
                    draftVo.setTaskAcceptTaskNum(task.getAcceptTaskNum());
                }
            }
        }
        return draftVo;
    }

    @Override
    public int add(Draft draft) {
        Long id = idAPI.getId("yryz_draft");
        draft.setKid(id);
        Long appId = draft.getAppId();
        return draftDao.insertByPrimaryKeySelective(draft);
    }

    private DraftVo toDraftVo(Draft draft) {
        DraftVo draftVo = new DraftVo();
        //Draft to DraftVo
        draftVo.setKid(draft.getKid());
        draftVo.setTitle(draft.getTitle());
        draftVo.setContentHtml(draft.getContentHtml());
        draftVo.setCoverImgUrl(draft.getCoverImgUrl());
        draftVo.setDescription(draft.getDescription());
        draftVo.setDraftFee(draft.getDraftFee());
        draftVo.setDraftStatus(draft.getDraftStatus());
        draftVo.setDraftType(draft.getDraftType());
        draftVo.setTaskFlag(draft.getTaskFlag());
        draftVo.setReason(draft.getReason());
        draftVo.setSuggest(draft.getSuggest());
        draftVo.setVideoUrl(draft.getVideoUrl());
        draftVo.setCreateDate(DateUtil.getString(draft.getCreateDate()));
        draftVo.setClassifyName(draft.getClassifyName());
        draftVo.setLabelName(draft.getLabelName());
        Long appId = draft.getAppId();
        if (appId != null && appId != 0) {
            TaskVo app = taskDao.selectAppById(appId);
            if (app != null) {
                draftVo.setAppliName(app.getAppliName());
            }
        }
        String createUserId = draft.getCreateUserId();
        if (StringUtils.isNotBlank(createUserId)) {
            DraftVo writer = draftDao.selectWriterByKid(createUserId);
            if (writer != null) {
                draftVo.setWriterName(writer.getWriterName());
                draftVo.setWriterNickName(writer.getWriterNickName());
                draftVo.setWriterPhone(writer.getWriterPhone());
                draftVo.setWriterRemark(writer.getWriterRemark());
            }
        }
        return draftVo;
    }
}

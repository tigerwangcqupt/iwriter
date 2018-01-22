package com.yryz.writer.modules.message.service.impl;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.distributed.lock.DistributedLockUtils;
import com.yryz.writer.common.redis.utils.JedisUtils;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.message.constant.MessageConstant;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import com.yryz.writer.modules.message.service.MessageService;
import com.yryz.writer.modules.message.vo.*;
import com.yryz.writer.modules.task.TaskApi;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

/**
 * File Name: MessageServiceImpl
 * Package Name: com.yryz.writer.modules.message.service.impl
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 11:22
 **/
@Service
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {
    private static final String ID_LOCK_NAME = "Message";

    private static final String COMMON_KEY = "common_message_ids";
    /** 首页消息通知栏目列表  */
    private static final ModuleEnum[] indexModuleEnums = {ModuleEnum.NOTICE,
            ModuleEnum.COMMENT, ModuleEnum.SHARE, ModuleEnum.FAVORITE};

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private TaskApi taskApi;

    @Override
    protected BaseDao getDao() {
        return null;
    }

    @Autowired
    private MessageMongo messageMongo;

    @Autowired
    private IdAPI idApi;

    @Override
    public MessageNumVo getMessageNumVo(Long writerId) {
        Assert.notNull(writerId, "写手id不能为空");
        MessageNumVo messageNumVo = null;
        try {
//            //todo设置消息总数
            messageNumVo = new MessageNumVo();
//            messageNumVo.setMessageNum("99");
            long messageNum = 0;
            for (ModuleEnum moduleEnum : indexModuleEnums) {
                Long tipsNum = this.getMessageTipsNum(moduleEnum, writerId);
                if (tipsNum != null){
                    messageNum += tipsNum;
                }
            }
            messageNumVo.setMessageNum(String.valueOf(messageNum));
        } catch (Exception e) {
            logger.error("查询写手首页消息总数失败", e);
            throw e;
        }
        return messageNumVo;
    }

    public List<IndexTipsVo> getIndexTips(Long writerId){
        Assert.notNull(writerId, "写手id不能为空");
        List<IndexTipsVo> indexTips = new ArrayList<IndexTipsVo>(indexModuleEnums.length);
        boolean success = true;
        try {
            for (ModuleEnum moduleEnum : indexModuleEnums) {
                IndexTipsVo indexTipsVo = new IndexTipsVo();
                indexTipsVo.setColumnName(moduleEnum.getName());
                indexTipsVo.setColumnUrl(moduleEnum.getUrl());
                Long tipsNum = this.getMessageTipsNum(moduleEnum, writerId);
                indexTipsVo.setTipsNum(tipsNum != null ? tipsNum.toString() : "0");

                indexTips.add(indexTipsVo);
            }

        } catch (Exception e) {
            logger.error("保存消息缓存气泡失败", e);
            throw e;
        }
        return indexTips;
    }


    @Override
    public Boolean saveMessageTips(ModuleEnum moduleEnum, Long writerId) {
        Assert.notNull(moduleEnum, "模块不能为空");
        Assert.notNull(writerId, "写手id不能为空");
        String key = MessageConstant.getHashKey(writerId);
        String field = MessageConstant.getHashField(moduleEnum.getValue());
        boolean success = true;
        String lock = null;
        try {
            //写手相关的消息业务 进行锁（避免同时新增时冲突）
            lock = DistributedLockUtils.lock(ID_LOCK_NAME, moduleEnum.getValue() + writerId.toString());
            //如果存在业务的缓存气泡数
            if (JedisUtils.mapExists(key, field)) {
                //写手的缓存数增加1
                Long status = JedisUtils.mapIncrby(key, field, 1);
                //如果缓存没有设置成功 做什么
                if (status == null) {
                    success = false;
                }
            } else {
                //写手的缓存数设置1
                //是否设置成功
                success = JedisUtils.mapSetnx(key, field, "1");
                if (!success) {
                    //如果缓存没有设置成功 做什么
                }
            }
        } catch (Exception e) {
            logger.error("保存消息缓存气泡失败", e);
            throw e;
        } finally {
            DistributedLockUtils.unlock(ID_LOCK_NAME, lock);
        }
        return success;
    }

    @Override
    public Boolean setPlatformTaskLooked(Long writerId) {
        Assert.notNull(writerId, "写手id不能为空");
        String key = MessageConstant.getHashKey(writerId);
        String field = MessageConstant.getHashField(ModuleEnum.PLATFORM.getValue());
        boolean success = true;
        String lock = null;
        try {
            //读出有效平台任务id集合 组成id,id格式
            List<Long> ids = Collections.emptyList();
            RpcResponse<List<Long>> idsResponse = taskApi.taskIdList();
            if (idsResponse.success()){
                ids = idsResponse.getData();
            }
            StringBuilder idStr = new StringBuilder();
            ids.stream().forEach(number -> {
                idStr.append(number).append(",");
            });
            //写手相关的消息业务 进行锁（避免同时新增时冲突）
            lock = DistributedLockUtils.lock(ID_LOCK_NAME, ModuleEnum.PLATFORM.getValue() + writerId.toString());
            //如果存在业务的缓存气泡数
            if (JedisUtils.mapExists(key, field)) {
                success = JedisUtils.mapSet(key, field, idStr.substring(0, idStr.length() - 1).toString());
            }else{      //设置 目标写手的已读 任务id 集合
                success = JedisUtils.mapSetnx(key, field, idStr.substring(0, idStr.length() - 1).toString());
            }

//            success = JedisUtils.mapSetnx(key, field, messageNum.toString());
        } catch (Exception e) {
            logger.error("保存消息缓存气泡失败", e);
            throw e;
        } finally {
            DistributedLockUtils.unlock(ID_LOCK_NAME, lock);
        }
        return success;
    }


    @Override
    public Long getPlatformTaskMessageTips(Long writerId) {
        Assert.notNull(writerId, "写手id不能为空");
        String moduleValue = ModuleEnum.PLATFORM.getValue();
//        //平台任务总数缓存
//        String field = MessageConstant.getHashField(moduleValue);

        //写手已查看平台任务缓存数
        String writerKey = MessageConstant.getHashKey(writerId);
        String writerField = MessageConstant.getHashField(moduleValue);
//        //平台任务总数
//        Long result = null;
//        //已查看任务数
//        Long lookedNum = 0l;
//        String lock = null;
        List<Long> ids = Collections.emptyList();
        try {
//            //如果存在业务的缓存气泡数
//            if (JedisUtils.mapExists(COMMON_KEY, field)) {
//                String num = JedisUtils.mapHget(COMMON_KEY, field);
//                result = Long.valueOf(num);
//            }else{
//                result = 0l;
//            }
//
//            //如果存在写手的平台任务已查看数的缓存气泡数
//            if (JedisUtils.mapExists(writerKey, writerField)) {
//                String num = JedisUtils.mapHget(writerKey, writerField);
//                lookedNum = Long.valueOf(num);
//            }
//            //写手当前的平台任务缓存数 = 平台任务的总缓存数 - 当前已查看数
//            result = result - lookedNum;
            //当前有效平台任务列表
            RpcResponse<List<Long>> idsResponse = taskApi.taskIdList();
            if (idsResponse.success()){
                ids.addAll(idsResponse.getData());
            }
            String[] userLookedIds = {};
            if (JedisUtils.mapExists(writerKey, writerField)) {
                String idStr = JedisUtils.mapHget(writerKey, writerField);
                if(StringUtils.contains(idStr, ",")){
                    userLookedIds = idStr.split(",");
                }else{
                    userLookedIds = new String[]{idStr};
                }
            }
            //平台任务数= 有效平台任务id - 已查看任务id 的个数
            ids.removeAll(Arrays.asList(userLookedIds));

        } catch (Exception e) {
            logger.error("获取平台任务消息缓存气泡失败:" + writerId, e);
            throw e;
//            return 0L;
        }
        return Long.valueOf(ids.size());
    }


    @Override
    public Boolean cleanMessageTips(ModuleEnum moduleEnum, Long writerId) {
        Assert.notNull(moduleEnum, "模块不能为空");
        Assert.notNull(writerId, "写手id不能为空");
        String key = MessageConstant.getHashKey(writerId);
        String field = MessageConstant.getHashField(moduleEnum.getValue());
        boolean success = true;
        try {
            //如果存在业务的缓存气泡数
            if (JedisUtils.mapExists(key, field)) {
                Long status = JedisUtils.mapRemove(key, field);
                if (status == null){
                    success = false;
                }
            }
        } catch (Exception e) {
            logger.error("清空消息缓存气泡失败:" + moduleEnum.getName(), e);
            throw e;
        }
        return success;
    }

    @Override
    public Long getMessageTipsNum(ModuleEnum moduleEnum, Long writerId) {
        Assert.notNull(moduleEnum, "模块不能为空");
        Assert.notNull(writerId, "写手id不能为空");
        String key = MessageConstant.getHashKey(writerId);
        String field = MessageConstant.getHashField(moduleEnum.getValue());
        Long result = null;
        try {
            //平台任务 例外处理
            if (moduleEnum == ModuleEnum.PLATFORM){
                result = getPlatformTaskMessageTips(writerId);
            }else{
                //如果存在业务的缓存气泡数
                if (JedisUtils.mapExists(key, field)) {
                    String num = JedisUtils.mapHget(key, field);
                    result = Long.valueOf(num);
                }else{
                    return 0L;
                }
            }


        } catch (Exception e) {
            logger.error("获取消息缓存气泡失败:" + moduleEnum.getName(), e);
            throw e;
//            return 0L;
        }
        return result;
    }

    @Override
    public Boolean saveWriterNoticeMessage(WriterNoticeMessageVo writerNoticeMessageVo) {
        Long result = null;
        try {
            Long kid = idApi.getId("yryz_notice_writer");
            writerNoticeMessageVo.setMessageId(kid.toString());
            writerNoticeMessageVo.setCreateTime(new Date());
            writerNoticeMessageVo.setSendUserId(writerNoticeMessageVo.getSendUserId() == null ? 0l : writerNoticeMessageVo.getSendUserId());
            for (NoticeReceiveWriter writer : writerNoticeMessageVo.getReceiveWriter()){
                if (writer == null) continue;
                //保存写手的消息缓存数
                saveMessageTips(ModuleEnum.NOTICE, writer.getKid());
            }
            messageMongo.saveWriterNotice(writerNoticeMessageVo);
        } catch (Exception e) {
            logger.error("发送写手通知消息失败", e);
            throw e;
//            return false;
        }
        return true;
    }

    @Override
    public PageList<WriterNoticeVo> queryWriterNoticeMessage(WriterNoticeMessageDto writerNoticeMessageDto) {
        Long result = null;
        Assert.notNull(writerNoticeMessageDto.getReceiveWriterId(), "写手id不能为空");
        try {
            PageUtils.startPage(writerNoticeMessageDto.getCurrentPage(), writerNoticeMessageDto.getPageSize());
//            return messageMongo.queryWriterNoticePage(writerNoticeMessageDto);
            List<WriterNoticeMessageVo> writerNoticeMessageVoList = messageMongo.queryWriterNoticePage(writerNoticeMessageDto);
            List<WriterNoticeVo> noticeVos = new ArrayList<>(writerNoticeMessageVoList.size());
            for (WriterNoticeMessageVo writerNoticeMessageVo : writerNoticeMessageVoList){
                WriterNoticeVo writerNoticeVo = new WriterNoticeVo();
                writerNoticeVo.setContent(writerNoticeMessageVo.getContent());
                writerNoticeVo.setCreateDate(writerNoticeMessageVo.getCreateTime());
                noticeVos.add(writerNoticeVo);
            }
            //查询通知的同时清掉消息缓存数
            cleanMessageTips(ModuleEnum.NOTICE, writerNoticeMessageDto.getReceiveWriterId());
            return new PageModel<WriterNoticeVo>().getPageList(noticeVos);
        } catch (Exception e) {
            logger.error("获取通知消息失败", e);
            throw e;
        }
    }


}

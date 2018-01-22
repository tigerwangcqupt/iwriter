package com.yryz.writer.modules.message.service;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import com.yryz.writer.modules.message.vo.*;

import java.util.List;

/**
 * File Name: MessageService
 * Package Name: com.yryz.writer.modules.message.service.impl
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 10:36
 **/
public interface MessageService {

    /**
     * 获得写手的消息总数（通知、评论、分享、收藏）
     * @param writerId
     * @return
     */
    public MessageNumVo getMessageNumVo(Long writerId);


    /**
     * 增加写手的消息缓存数，每次访问列表时清空（通知、评论、分享、收藏）
     * @param writerId
     * @return
     */
    public Boolean saveMessageTips(ModuleEnum moduleEnum, Long writerId);

    /**
     * 设置写手的平台任务已读数
     * @return
     */
    public Boolean setPlatformTaskLooked(Long writerId);

    /**
     * 获取写手的平台任务数
     * @return
     */
    public Long getPlatformTaskMessageTips(Long writerId);

    /**
     * 获得写手的消息栏目（包含每个栏目的消息数）
     * @param writerId
     * @return
     */
    public List<IndexTipsVo> getIndexTips(Long writerId);

    /**
     * 清空某个消息栏目缓存数（通知、评论、分享、收藏）
     * @param writerId
     * @return
     */
    public Boolean cleanMessageTips(ModuleEnum moduleEnum, Long writerId);

    /**
     * 获得写手某个栏目的气泡数
     * @param moduleEnum
     * @param writerId
     * @return
     */
    public Long getMessageTipsNum(ModuleEnum moduleEnum, Long writerId);

    /**
     * 保存发送给写手的消息
     * @param writerNoticeMessageVo
     * @return
     */
    public Boolean saveWriterNoticeMessage(WriterNoticeMessageVo writerNoticeMessageVo);

    /**
     * 查询发送给写手的消息
     * @param writerNoticeMessageDto
     * @return
     */
    public PageList<WriterNoticeVo> queryWriterNoticeMessage(WriterNoticeMessageDto writerNoticeMessageDto);
}

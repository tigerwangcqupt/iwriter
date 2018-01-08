package com.yryz.writer.modules.message.vo;

import javax.sound.midi.Receiver;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * File Name: WriterNoticeMessageVo
 * Package Name: com.yryz.writer.modules.message.vo
 * Description: 写手通知消息
 *
 * @author huyangyang
 * @create 2018-01-08 10:47
 **/
public class WriterNoticeMessageVo implements Serializable {

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 触发类型 1写手资料通过审核  2写手信息管理后台发送通知 3提现后台发送提现失败通知 4写手稿件过审 5写手稿件未过审
     */
    private Integer triggerType;

    /**
     * 发送者 用户kid 如果没有则为0
     */
    private Long sendUserId;

    /**
     * 接受者列表
     */
    private List<NoticeReceiveWriter>  receiveWriter;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date createTime;

    public String getMessageId() {
        return messageId;
    }

    public Integer getTriggerType() {
        return triggerType;
    }

    public Long getSendUserId() {
        return sendUserId;
    }

    public List<NoticeReceiveWriter> getReceiveWriter() {
        return receiveWriter;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setTriggerType(Integer triggerType) {
        this.triggerType = triggerType;
    }

    public void setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    public void setReceiveWriter(List<NoticeReceiveWriter> receiveWriter) {
        this.receiveWriter = receiveWriter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {

        return createTime;
    }
}

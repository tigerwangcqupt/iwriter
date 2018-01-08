package com.yryz.writer.modules.message.vo;

import com.yryz.component.rpc.dto.PageList;

import java.io.Serializable;

/**
 * File Name: WriterNoticeMessageDto
 * Package Name: com.yryz.writer.modules.message.vo
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-08 13:58
 **/
public class WriterNoticeMessageDto extends PageList {

    /**
     * 触发类型 1写手资料通过审核  2写手信息管理后台发送通知 3提现后台发送提现失败通知 4写手稿件过审
     */
    private Integer triggerType;

    /**
     * 接受的写手id
     */
    private Long receiveWriterId;

    public Integer getTriggerType() {
        return triggerType;
    }

    public Long getReceiveWriterId() {
        return receiveWriterId;
    }

    public void setTriggerType(Integer triggerType) {
        this.triggerType = triggerType;
    }

    public void setReceiveWriterId(Long receiveWriterId) {
        this.receiveWriterId = receiveWriterId;
    }
}

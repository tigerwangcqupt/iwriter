package com.yryz.writer.modules.message.vo;
import java.io.Serializable;

/**
 * @ClassName: IndexColumnVo
 * @Description: 消息总数实体
 * @author huyangyang
 * @date 2018-01-02 10:04:46
 *
 */
public class MessageNumVo implements Serializable {

    /** 消息总数  */
    private String messageNum;

    public void setMessageNum(String messageNum) {
        this.messageNum = messageNum;
    }

    public String getMessageNum() {
        return messageNum;
    }
}

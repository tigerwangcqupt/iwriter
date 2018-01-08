package com.yryz.writer.modules.message.vo;

import java.io.Serializable;

/**
 * File Name: NoticeReceiveWriter
 * Package Name: com.yryz.writer.modules.message.vo
 * Description: 消息接受的写手
 *
 * @author huyangyang
 * @create 2018-01-08 11:02
 **/
public class NoticeReceiveWriter implements Serializable {

    /**
     * 唯一标识
     */
    private Long kid;

    /**
     * 接受的写手的昵称
     */
    private String userNickName;

    /**
     * 接受到的消息
     */
    private String content;

    public Long getKid() {
        return kid;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public String getContent() {
        return content;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

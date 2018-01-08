package com.yryz.writer.modules.message.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * File Name: WriterNoticeVo
 * Package Name: com.yryz.writer.modules.message.vo
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-08 16:42
 **/
public class WriterNoticeVo implements Serializable {

    /**
     * 消息通知内容
     */
    private String content;

    /**
     * 创建日期
     */
    private Date createDate;

    public String getContent() {
        return content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

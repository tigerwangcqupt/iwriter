package com.yryz.writer.modules.config.entity;

import com.yryz.writer.common.entity.GenericEntity;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.config.entity
 * @Desc:
 * @Date: 2018/5/29.
 */
public class Config extends GenericEntity {

    private String commentValue;

    private String commentName;

    private String commentType;

    public String getCommentValue() {
        return commentValue;
    }

    public void setCommentValue(String commentValue) {
        this.commentValue = commentValue;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }
}

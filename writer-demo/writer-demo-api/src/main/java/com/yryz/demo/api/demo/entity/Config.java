package com.yryz.demo.api.demo.entity;

import com.yryz.common.entity.GenericEntity;

public class Config extends GenericEntity {
	private static final long serialVersionUID = 1L;

    private String commentValue;

    private String commentName;

    private String commentType;

    public String getCommentValue() {
        return commentValue;
    }

    public void setCommentValue(String commentValue) {
        this.commentValue = commentValue == null ? null : commentValue.trim();
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName == null ? null : commentName.trim();
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType == null ? null : commentType.trim();
    }
}
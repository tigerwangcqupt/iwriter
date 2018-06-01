package com.yryz.writer.modules.draft.entity;

import com.yryz.writer.common.entity.GenericEntity;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/5/29 14:28
 * Created by huangxy
 *
 * 稿件审核记录
 */
public class DraftAudit extends GenericEntity{

    /**
     * 稿件业务KID
     */
    private Long draftKid;


    /**
     * 标题
     */
    private String title;

    /**
     * 图文稿件正文
     */
    private String contentHtml;

    /**
     * 图文稿件正文
     */
    private String contentSource;


    /**
     * 视频稿件视频路径
     */
    private String videoUrl;


    /**
     * 封面图路径
     */
    private String coverImgUrl;


    /**
     * 视频稿件描述
     */
    private String description;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 审核未通过时运营填写的原因
     */
    private String reason;

    /**
     * 审核不通过时运营填写的建议
     */
    private String suggest;

    /**
     * 审核通过时备注
     */
    private String auditRemark;

    /**
     * 删除标识
     */
    private Integer delFlag;

    private Integer shelveFlag;

    public Integer getShelveFlag() {
        return shelveFlag;
    }

    public void setShelveFlag(Integer shelveFlag) {
        this.shelveFlag = shelveFlag;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public Long getDraftKid() {
        return draftKid;
    }

    public void setDraftKid(Long draftKid) {
        this.draftKid = draftKid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getContentSource() {
        return contentSource;
    }

    public void setContentSource(String contentSource) {
        this.contentSource = contentSource;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}

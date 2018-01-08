package com.yryz.writer.modules.draft.entity;

import com.yryz.writer.common.entity.GenericEntity;

import java.math.BigDecimal;

/**
 * @author luohao
 * @ClassName: Draft
 * @Description: 稿件表实体类
 * @date 2017-12-29 14:40:13
 */
public class Draft extends GenericEntity {


    /**
     * 稿件类型:0图文,1视频
     */
    private Integer draftType;


    /**
     * 任务标识:0自由投稿,1任务投稿
     */
    private Integer taskFlag;

    /**
     * 任务标识:0自由投稿,1任务投稿
     */
    private Integer taskKid;


    /**
     * 标题
     */
    private String title;


    /**
     * 图文稿件正文
     */
    private String contentHtml;


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
     * 任务稿件有appId,自由投稿无appId
     */
    private Long appId;


    /**
     * 必填,写手填写,供运营参考
     */
    private String appName;


    /**
     * 非必填,写手填写,供运营参考
     */
    private String classifyName;


    /**
     * 非必填,写手填写,空格分割,供运营参考
     */
    private String labelName;


    /**
     * 稿件状态:0 草稿,1 待审核,2 不通过,3 通过
     */
    private Integer draftStatus;


    /**
     * 审核未通过时运营填写的原因
     */
    private String reason;


    /**
     * 审核不通过时运营填写的建议
     */
    private String suggest;


    /**
     * 稿费
     */
    private BigDecimal draftFee;


    /**
     * 删除标记(0正常，1已删除)
     */
    private Integer delFlag;


    /**
     * 发布状态(0上架，1下架)
     */
    private Integer shelveFlag;


    public Integer getDraftType() {
        return this.draftType;
    }

    public void setDraftType(Integer draftType) {
        this.draftType = draftType;
    }

    public Integer getTaskFlag() {
        return this.taskFlag;
    }

    public void setTaskFlag(Integer taskFlag) {
        this.taskFlag = taskFlag;
    }

    public Integer getTaskKid() {
        return taskKid;
    }

    public void setTaskKid(Integer taskKid) {
        this.taskKid = taskKid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentHtml() {
        return this.contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCoverImgUrl() {
        return this.coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAppId() {
        return this.appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getClassifyName() {
        return this.classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getLabelName() {
        return this.labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getDraftStatus() {
        return this.draftStatus;
    }

    public void setDraftStatus(Integer draftStatus) {
        this.draftStatus = draftStatus;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSuggest() {
        return this.suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public BigDecimal getDraftFee() {
        return this.draftFee;
    }

    public void setDraftFee(BigDecimal draftFee) {
        this.draftFee = draftFee;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getShelveFlag() {
        return this.shelveFlag;
    }

    public void setShelveFlag(Integer shelveFlag) {
        this.shelveFlag = shelveFlag;
    }

}
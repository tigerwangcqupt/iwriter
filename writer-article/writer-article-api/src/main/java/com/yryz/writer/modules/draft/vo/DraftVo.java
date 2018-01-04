package com.yryz.writer.modules.draft.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author luohao
 * @ClassName: DraftVo
 * @Description: DraftVo
 * @date 2017-12-29 14:40:13
 */
public class DraftVo implements Serializable {
    private Long id;

    /**
     * 稿件类型:0图文,1视频
     */
    private Integer draftType;

    /**
     * 任务标识:0自由投稿,1任务投稿
     */
    private Integer taskFlag;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDraftType() {
        return draftType;
    }

    public void setDraftType(Integer draftType) {
        this.draftType = draftType;
    }

    public Integer getTaskFlag() {
        return taskFlag;
    }

    public void setTaskFlag(Integer taskFlag) {
        this.taskFlag = taskFlag;
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

    public Integer getDraftStatus() {
        return draftStatus;
    }

    public void setDraftStatus(Integer draftStatus) {
        this.draftStatus = draftStatus;
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

    public BigDecimal getDraftFee() {
        return draftFee;
    }

    public void setDraftFee(BigDecimal draftFee) {
        this.draftFee = draftFee;
    }
}

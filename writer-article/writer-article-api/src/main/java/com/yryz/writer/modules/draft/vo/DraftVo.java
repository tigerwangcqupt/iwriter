package com.yryz.writer.modules.draft.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author luohao
 * @ClassName: DraftVo
 * @Description: DraftVo
 * @date 2017-12-29 14:40:13
 */
public class DraftVo implements Serializable {
    private Long kid;

    /**
     * 稿件类型:0图文,1视频
     */
    private Integer draftType;

    /**
     * 任务标识:0自由投稿,1任务投稿
     */
    private Integer taskFlag;

    private Long taskKid;

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
     * 应用id
     */
    private Long appId;

    /**
     * 应用名
     */
    private String appliName;

    /**
     * 应用图标
     */
    private String icon;

    /**
     * 写手id
     */
    private Long writerId;

    /**
     * 写手姓名
     */
    private String writerName;
    /**
     * 写手昵称
     */
    private String writerNickName;
    /**
     * 写手电话
     */
    private String writerPhone;
    /**
     * 写手备注
     */
    private String writerRemark;

    private String createDate;
    /**
     * 非必填,写手填写,供运营参考
     */
    private String classifyName;


    /**
     * 非必填,写手填写,空格分割,供运营参考
     */
    private String labelName;

    private String taskTitle;

    private Date taskCreateDate;

    private Integer taskAcceptTaskNum;

    private Integer shelveFlag;

    private Integer dataType;

    public Integer getShelveFlag() {
        return shelveFlag;
    }

    public void setShelveFlag(Integer shelveFlag) {
        this.shelveFlag = shelveFlag;
    }

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
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

    public Long getTaskKid() {
        return taskKid;
    }

    public void setTaskKid(Long taskKid) {
        this.taskKid = taskKid;
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

    public String getAppliName() {
        return appliName;
    }

    public void setAppliName(String appliName) {
        this.appliName = appliName;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriterNickName() {
        return writerNickName;
    }

    public void setWriterNickName(String writerNickName) {
        this.writerNickName = writerNickName;
    }

    public String getWriterPhone() {
        return writerPhone;
    }

    public void setWriterPhone(String writerPhone) {
        this.writerPhone = writerPhone;
    }

    public String getWriterRemark() {
        return writerRemark;
    }

    public void setWriterRemark(String writerRemark) {
        this.writerRemark = writerRemark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getTaskCreateDate() {
        return taskCreateDate;
    }

    public void setTaskCreateDate(Date taskCreateDate) {
        this.taskCreateDate = taskCreateDate;
    }

    public Integer getTaskAcceptTaskNum() {
        return taskAcceptTaskNum;
    }

    public void setTaskAcceptTaskNum(Integer taskAcceptTaskNum) {
        this.taskAcceptTaskNum = taskAcceptTaskNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
}

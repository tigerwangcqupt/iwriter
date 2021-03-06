package com.yryz.writer.modules.task.entity;

import com.yryz.writer.common.entity.GenericEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author luohao
 * @ClassName: Task
 * @Description: 任务表实体类
 * @date 2018-01-02 20:07:17
 */
public class Task extends GenericEntity {


    /**
     * 任务标题
     */
    private String title;


    /**
     * 任务关联应用id
     */
    private Long appId;


    /**
     * 稿费
     */
    private BigDecimal draftFee;


    /**
     * 任务开始时间
     */
    private Date startDate;


    /**
     * 任务结束时间
     */
    private Date endDate;


    /**
     * 接受任务人数
     */
    private Integer acceptTaskNum;


    /**
     * 任务截至人数
     */
    private Integer taskCloseNum;


    /**
     * 投稿写手数
     */
    private Integer submitNum;


    /**
     * 稿件类型:0 图文,1 视频
     */
    private Integer draftType;


    /**
     * 任务描述
     */
    private String contentHtml;

    /**
     * 正文数据
     */
    private String contentSource;


    /**
     * 删除标记(0正常，1已删除)
     */
    private Integer delFlag;


    /**
     * 发布状态(0上架，1下架)
     */
    private Integer shelveFlag;

    /**
     * 任务结束标识
     */
    private Integer taskCloseFlag;

    public Integer getTaskCloseFlag() {
        return taskCloseFlag;
    }

    public void setTaskCloseFlag(Integer taskCloseFlag) {
        this.taskCloseFlag = taskCloseFlag;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAppId() {
        return this.appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public BigDecimal getDraftFee() {
        return this.draftFee;
    }

    public void setDraftFee(BigDecimal draftFee) {
        this.draftFee = draftFee;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAcceptTaskNum() {
        return this.acceptTaskNum;
    }

    public void setAcceptTaskNum(Integer acceptTaskNum) {
        this.acceptTaskNum = acceptTaskNum;
    }

    public Integer getTaskCloseNum() {
        return taskCloseNum;
    }

    public void setTaskCloseNum(Integer taskCloseNum) {
        this.taskCloseNum = taskCloseNum;
    }

    public Integer getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(Integer submitNum) {
        this.submitNum = submitNum;
    }

    public Integer getDraftType() {
        return this.draftType;
    }

    public void setDraftType(Integer draftType) {
        this.draftType = draftType;
    }

    public String getContentHtml() {
        return this.contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
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

    public String getContentSource() {
        return contentSource;
    }

    public void setContentSource(String contentSource) {
        this.contentSource = contentSource;
    }
}
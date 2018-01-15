package com.yryz.writer.modules.task.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.yryz.writer.modules.task.entity.Task;

/**
 * @author luohao
 * @ClassName: TaskVo
 * @Description: TaskVo
 * @date 2018-01-02 20:07:17
 */
public class TaskVo implements Serializable {
    private Long kid;
    /**
     * 任务标题
     */
    private String title;


    /**
     * 任务关联应用id
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
     * 开发商名
     */
    private String companyName;


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
    private Integer writerNum;

    private Integer draftNum;


    /**
     * 稿件类型:0 图文,1 视频
     */
    private Integer draftType;


    /**
     * 任务描述
     */
    private String contentHtml;
    private String contentSource;

    /**
     * 创建时间
     */
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public BigDecimal getDraftFee() {
        return draftFee;
    }

    public void setDraftFee(BigDecimal draftFee) {
        this.draftFee = draftFee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAcceptTaskNum() {
        return acceptTaskNum;
    }

    public void setAcceptTaskNum(Integer acceptTaskNum) {
        this.acceptTaskNum = acceptTaskNum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getTaskCloseNum() {
        return taskCloseNum;
    }

    public void setTaskCloseNum(Integer taskCloseNum) {
        this.taskCloseNum = taskCloseNum;
    }

    public Integer getWriterNum() {
        return writerNum;
    }

    public void setWriterNum(Integer writerNum) {
        this.writerNum = writerNum;
    }

    public Integer getDraftNum() {
        return draftNum;
    }

    public void setDraftNum(Integer draftNum) {
        this.draftNum = draftNum;
    }

    public Integer getDraftType() {
        return draftType;
    }

    public void setDraftType(Integer draftType) {
        this.draftType = draftType;
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

    public String getAppliName() {
        return appliName;
    }

    public void setAppliName(String appliName) {
        this.appliName = appliName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

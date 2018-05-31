package com.yryz.writer.modules.draft.dto;

import com.yryz.component.rpc.dto.PageList;

import java.util.List;

/**
 * @author luohao
 * @ClassName: DraftDto
 * @Description: DraftDto
 * @date 2017-12-29 14:40:13
 */
public class DraftDto extends PageList {
    private String title;
    private Integer draftStatus;
    private Integer status;
    //根据应用查询的条件
    private Long appId;
    private String appliName;

    //根据用户信息查询的条件
    //写手id
    private String createUserId;
    //写手ids
    private List<Long> createUserIds;
    private String nickName;
    private String userName;
    private String phone;
    private String remark;
    /**
     * 审核人员
     */
    private String auditorUserId;

    /**
     * 派稿状态
     */
    private Integer assignStatus;
    /**
     * 审核次数
     */
    private Integer editCount;
    /**
     * 生成排序字符串
     */
    private String orderStr;

    /**
     * 派稿开始时间
     */
    private String assignerBeginDate;

    private String assignerEndDate;

    /**
     * 审核开始时间
     */
    private String auditBeginDate;

    private String auditEndDate;

    /**
     * orderFiled
     */
    private String orderFiled;

    /**
     * orderValue
     */
    private String orderValue;

    public String getAssignerBeginDate() {
        return assignerBeginDate;
    }

    public void setAssignerBeginDate(String assignerBeginDate) {
        this.assignerBeginDate = assignerBeginDate;
    }

    public String getAssignerEndDate() {
        return assignerEndDate;
    }

    public void setAssignerEndDate(String assignerEndDate) {
        this.assignerEndDate = assignerEndDate;
    }

    public String getAuditBeginDate() {
        return auditBeginDate;
    }

    public void setAuditBeginDate(String auditBeginDate) {
        this.auditBeginDate = auditBeginDate;
    }

    public String getAuditEndDate() {
        return auditEndDate;
    }

    public void setAuditEndDate(String auditEndDate) {
        this.auditEndDate = auditEndDate;
    }

    public Integer getEditCount() {
        return editCount;
    }

    public void setEditCount(Integer editCount) {
        this.editCount = editCount;
    }

    public Integer getAssignStatus() {
        return assignStatus;
    }

    public void setAssignStatus(Integer assignStatus) {
        this.assignStatus = assignStatus;
    }

    public String getAuditorUserId() {
        return auditorUserId;
    }

    public void setAuditorUserId(String auditorUserId) {
        this.auditorUserId = auditorUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public List<Long> getCreateUserIds() {
        return createUserIds;
    }

    public void setCreateUserIds(List<Long> createUserIds) {
        this.createUserIds = createUserIds;
    }

    public Integer getDraftStatus() {
        return draftStatus;
    }

    public void setDraftStatus(Integer draftStatus) {
        this.draftStatus = draftStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppliName() {
        return appliName;
    }

    public void setAppliName(String appliName) {
        this.appliName = appliName;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public String getOrderFiled() {
        return orderFiled;
    }

    public void setOrderFiled(String orderFiled) {
        this.orderFiled = orderFiled;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

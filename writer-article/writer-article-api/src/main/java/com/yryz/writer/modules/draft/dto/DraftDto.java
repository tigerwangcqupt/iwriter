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
     * 生成排序字符串
     */
    private String orderStr;

    /**
     * orderFiled
     */
    private String orderFiled;

    /**
     * orderValue
     */
    private String orderValue;

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

package com.yryz.writer.modules.draft.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @author luohao
 * @ClassName: DraftDto
 * @Description: DraftDto
 * @date 2017-12-29 14:40:13
 */
public class DraftDto extends PageList {
    private String title;
    private String createUserId;
    private Integer draftStatus;
    private Integer status;
    private Long appId;
    private String appliName;
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
}

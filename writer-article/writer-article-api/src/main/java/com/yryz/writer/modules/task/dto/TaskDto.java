package com.yryz.writer.modules.task.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @author luohao
 * @ClassName: TaskDto
 * @Description: TaskDto
 * @date 2018-01-02 20:07:17
 */
public class TaskDto extends PageList {
    private String title;
    private Long appId;
    private Long taskId;
    private Integer status;
    private Long writerId;
    private Integer order;
    //区分app和admin   0:app   1:admin
    private Integer appOrAdmin;
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

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public Integer getAppOrAdmin() {
        return appOrAdmin;
    }

    public void setAppOrAdmin(Integer appOrAdmin) {
        this.appOrAdmin = appOrAdmin;
    }
}

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
}

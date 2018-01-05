package com.yryz.writer.modules.task.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @author luohao
 * @ClassName: TaskDto
 * @Description: TaskDto
 * @date 2018-01-02 20:07:17
 */
public class TaskDto extends PageList {
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

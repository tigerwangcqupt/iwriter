package com.yryz.writer.modules.task.vo;

import java.io.Serializable;

import com.yryz.writer.modules.task.entity.Task;

/**
 * @author luohao
 * @ClassName: TaskVo
 * @Description: TaskVo
 * @date 2018-01-02 20:07:17
 */
public class TaskVo implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

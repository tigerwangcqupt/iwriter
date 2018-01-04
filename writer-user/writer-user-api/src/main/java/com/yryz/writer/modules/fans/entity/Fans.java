package com.yryz.writer.modules.fans.entity;

import com.yryz.common.entity.GenericEntity;

import java.util.Date;

/**
 * @author luohao
 * @ClassName: Fans
 * @Description: 粉丝表实体类
 * @date 2018-01-02 20:08:19
 */
public class Fans extends GenericEntity {


    /**
     * 用户id
     */
    private Long userId;


    /**
     * 写手id
     */
    private Long writerId;


    /**
     * 删除标记(0正常，1已删除)
     */
    private Integer delFlag;


    /**
     * 发布状态(0上架，1下架)
     */
    private Integer shelveFlag;


    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
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

}
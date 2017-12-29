package com.yryz.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1599925460585783120L;

    //protected Integer revision;
    protected String createUserId;
    protected Date createDate;
    protected String lastUpdateUserId;
    protected Date lastUpdateDate;

    /**
     * 租户ID
     */
    //private  String tenantId;

    /**
     * 功能枚举
     */
    private  String moduleEnum;


    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getModuleEnum() {
        return moduleEnum;
    }

    public void setModuleEnum(String moduleEnum) {
        this.moduleEnum = moduleEnum;
    }
}

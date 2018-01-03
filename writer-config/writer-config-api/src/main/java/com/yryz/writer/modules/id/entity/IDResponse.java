package com.yryz.writer.modules.id.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2017/9/18
 * @description
 */
public class IDResponse implements Serializable {
    private static final long serialVersionUID = 1635957641085491701L;

    private Boolean flag;
    private String msg;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

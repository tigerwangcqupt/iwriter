package com.yryz.openapi.core.auth.entity;

import java.io.Serializable;

public class SignInfo implements Serializable {
    /**
     * 签名后的字符串
     */
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

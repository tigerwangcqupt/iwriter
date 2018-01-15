package com.yryz.openapi.core.auth.entity;

import java.io.Serializable;

public class SignInfo implements Serializable {
    /**
     * 原文字符串
     */
    private String originText;
    /**
     * 签名后的字符串
     */
    private String sign;

    public String getOriginText() {
        return originText;
    }

    public void setOriginText(String originText) {
        this.originText = originText;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

package com.yryz.writer.modules.platform.dto;


import com.yryz.service.api.user.entity.CustRegister;

public class CustRegisterDto extends CustRegister {

    /**
     * 功能码
     */
    private String code;
    /**
     * 验证码
     */
    private String veriCode;
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 三方唯一id
     */
    private String openId;
    /**
     * 0: 绑定， 1：解绑
     */
    private Integer action;
    /**
     * 三方token
     */
    private String accessToken;
    /**
     * 1,wx 2,wb 3,qq 4,phone
     */
    private Integer bindType;

    /**
     * 安装渠道
     */
    private String installChannel;

    /**
     * 图形验证码
     */
    private String imageCode;

    /**
     * 唯一标识码
     */
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getInstallChannel() {
        return installChannel;
    }

    public void setInstallChannel(String installChannel) {
        this.installChannel = installChannel;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getVeriCode() {
        return veriCode;
    }

    public void setVeriCode(String veriCode) {
        this.veriCode = veriCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

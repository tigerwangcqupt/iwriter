package com.yryz.writer.modules.platform.vo;

import com.yryz.writer.modules.writer.entity.Writer;
import org.apache.commons.lang3.StringUtils;

public class AuthInfoVo extends AuthInfo {

    /**
     * 0:成功 1:校验失败 2:已过期
     */
    private Integer code;

    private Long userId;

    private String custImg;

    private String custNname;

    private String custPhone;

    private String custQr;

    /**
     * 邀请别人的邀请码
     */
    private String myInviteCode;

    /**
     * 1.三方注册并登录，2.三方登录
     */
    private Integer type;

    /**
     * 是否设置过密码
     */
    private Boolean pwdFlag;

    /**
     * 用户识别码
     */
    private String idCard;

    public AuthInfoVo() {
    }

    public AuthInfoVo(AuthInfo authInfo, Writer user) {
        this.setCustId(authInfo.getCustId());
        this.setToken(authInfo.getToken());
        this.setExpireAt(authInfo.getExpireAt());
        this.setUserId(user.getKid());
        this.setCustImg(user.getHeadImg());
        this.setCustNname(user.getNickName());
        this.setCustPhone(user.getAccount());
   /*     this.setCustQr(user.getQrCode());
        this.setType(user.getType());
        this.setIdCard(user.getIdCard());
        this.setPwdFlag(StringUtils.isNotBlank(user.getNewPassword()));*/
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Boolean getPwdFlag() {
        return pwdFlag;
    }

    public void setPwdFlag(Boolean pwdFlag) {
        this.pwdFlag = pwdFlag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMyInviteCode() {
        return myInviteCode;
    }

    public void setMyInviteCode(String myInviteCode) {
        this.myInviteCode = myInviteCode;
    }

    public String getCustImg() {
        return custImg;
    }

    public void setCustImg(String custImg) {
        this.custImg = custImg;
    }

    public String getCustNname() {
        return custNname;
    }

    public void setCustNname(String custNname) {
        this.custNname = custNname;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustQr() {
        return custQr;
    }

    public void setCustQr(String custQr) {
        this.custQr = custQr;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

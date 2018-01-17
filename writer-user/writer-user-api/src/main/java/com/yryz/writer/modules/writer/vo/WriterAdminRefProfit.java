package com.yryz.writer.modules.writer.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class WriterAdminRefProfit implements Serializable {

    /**
     * 写手kid
     */
    private Long kid;

    /**
     * 写手身份证
     */
    private String identityCard;

    /**
     * 昵称
     */
    private  String nickName;

    /**
     * 姓名
     */
    private  String userName;

    /**
     * 手机号
     */
    private  String phone;

    /**
     *  可提现金额
     */
    private BigDecimal withdrawAmount;

    /**
     * 累计提现金额
     */
    private BigDecimal sumWithdrawAmount;

    /**
     * 银行卡号
     */
    private  String userBankCart;

    /**
     * 流水号
     */
    private String profitSn;

    /**
     * 省
     */
    private  String provice;

    /**
     * 市
     */
    private  String city;

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getSumWithdrawAmount() {
        return sumWithdrawAmount;
    }

    public void setSumWithdrawAmount(BigDecimal sumWithdrawAmount) {
        this.sumWithdrawAmount = sumWithdrawAmount;
    }

    public String getUserBankCart() {
        return userBankCart;
    }

    public void setUserBankCart(String userBankCart) {
        this.userBankCart = userBankCart;
    }

    public String getProfitSn() {
        return profitSn;
    }

    public void setProfitSn(String profitSn) {
        this.profitSn = profitSn;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
}

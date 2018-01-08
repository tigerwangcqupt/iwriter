package com.yryz.writer.modules.writer.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class WriterAdminRefProfit implements Serializable {

    /**
     * 写手kid
     */
    private Long kid;

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
}

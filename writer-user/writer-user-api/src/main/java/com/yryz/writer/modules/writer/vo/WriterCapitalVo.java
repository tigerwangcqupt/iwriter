package com.yryz.writer.modules.writer.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WriterCapitalVo implements Serializable{

    //写手姓名
    private  String userName;
    //手机号
    private  String phone;
    //主键
    private Long kid;
    //主体编码
    private Long ownerCode;
    //主体外码
    private String ownerFcode;
    //最近提现金额
    private BigDecimal latelyWithdrawAmount;
    //可提现金额
    private  BigDecimal withdrawAmount;
    //累计提现金额
    private BigDecimal sumWithdrawAmount;
    //放款日期(最近提现金额日期)
    private String withdrawDate;

    /**
     * 流水类型
     * 1:申请提现 2:提现成功  3:提现失败 4:稿费
     */
    private Integer settlementType;

    public Integer getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public Long getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(Long ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerFcode() {
        return ownerFcode;
    }

    public void setOwnerFcode(String ownerFcode) {
        this.ownerFcode = ownerFcode;
    }

    public BigDecimal getLatelyWithdrawAmount() {
        return latelyWithdrawAmount;
    }

    public void setLatelyWithdrawAmount(BigDecimal latelyWithdrawAmount) {
        this.latelyWithdrawAmount = latelyWithdrawAmount;
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

    public String getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(String withdrawDate) {
        this.withdrawDate = withdrawDate;
    }
}

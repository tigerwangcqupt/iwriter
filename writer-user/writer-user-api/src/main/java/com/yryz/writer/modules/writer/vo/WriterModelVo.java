package com.yryz.writer.modules.writer.vo;

import java.math.BigDecimal;
import java.util.Date;

public class WriterModelVo {
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
    private Date withdraw_date;


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

    public Date getWithdraw_date() {
        return withdraw_date;
    }

    public void setWithdraw_date(Date withdraw_date) {
        this.withdraw_date = withdraw_date;
    }
}

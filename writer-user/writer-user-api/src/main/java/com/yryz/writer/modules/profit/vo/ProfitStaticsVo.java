package com.yryz.writer.modules.profit.vo;
import java.io.Serializable;
import java.math.BigDecimal;


public class ProfitStaticsVo implements Serializable {

    //写手姓名
    private  String userName;
    //手机号
    private  String phone;
    //主键
    private Long kid;

    //最近提现金额
    private BigDecimal latelyWithdrawAmount;
    //可提现金额
    private  BigDecimal withdrawAmount;
    //累计提现金额
    private BigDecimal sumWithdrawAmount;
    //放款日期(最近提现金额日期)
    private String withdrawDate;
    //是否可提现
    private Integer withdrawalsFlag;
    /**
     * 流水类型
     * 1:申请提现 2:提现成功  3:提现失败 4:稿费
     */
    private Integer settlementType;

    /**
     * 是否实名认证(0，未实名 1，已实名认证)
     */
    private Integer certificationFlag;

    public Integer getCertificationFlag() {
        return certificationFlag;
    }

    public void setCertificationFlag(Integer certificationFlag) {
        this.certificationFlag = certificationFlag;
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

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
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

    public String getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(String withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    public Integer getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    public Integer getWithdrawalsFlag() {
        return withdrawalsFlag;
    }

    public void setWithdrawalsFlag(Integer withdrawalsFlag) {
        this.withdrawalsFlag = withdrawalsFlag;
    }
}

package com.yryz.writer.modules.profit.vo;

/**
 * @ClassName: ProfitAdminVo
 * @Description: ProfitAdminVo
 * @author wangsenyong
 * @date 2018-01-09 15:36:15
 * 管理后台提现管理界面
 */
import java.io.Serializable;
import java.math.BigDecimal;

public class ProfitAdminVo implements Serializable {

    //流水id
    private Long flowId;
    //提现日期
    private String settlementDate;
    //写手昵称
    private  String nickName;
     //姓名
    private  String userName;
    //手机号
    private  String phone;
    //所在地
    private String location;

    //申请提现金额
    private BigDecimal settlementAmount;

    //开户支行
    private  String userAccountOpenBank;

    //交易银行
    private  String userTradeBankCart;
    //银行卡号
    private  String userBankCart;

    //流水号
    private String profitSn;

    //写手id
    private Long writerId;

    //银行卡卡主姓名
    private  String userRefBankName;

    //流水消息
    private  String settlementMsg;

    //流水类型
    private  String settlementType;

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getUserAccountOpenBank() {
        return userAccountOpenBank;
    }

    public void setUserAccountOpenBank(String userAccountOpenBank) {
        this.userAccountOpenBank = userAccountOpenBank;
    }

    public String getUserTradeBankCart() {
        return userTradeBankCart;
    }

    public void setUserTradeBankCart(String userTradeBankCart) {
        this.userTradeBankCart = userTradeBankCart;
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

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public String getUserRefBankName() {
        return userRefBankName;
    }

    public void setUserRefBankName(String userRefBankName) {
        this.userRefBankName = userRefBankName;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getSettlementMsg() {
        return settlementMsg;
    }

    public void setSettlementMsg(String settlementMsg) {
        this.settlementMsg = settlementMsg;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }
}

package com.yryz.writer.modules.profit.dto;

import com.yryz.component.rpc.dto.PageList;

import java.math.BigDecimal;

/**
 * @ClassName: ProfitDto
 * @Description: ProfitDto
 * @author zhongying
 * @date 2017-12-29 15:36:15
 *
 */
public class ProfitDto extends PageList {

    //流水id
    private Long flowId;
    //写手id
    private Long createUserId;

    //流水type
    private Integer settlementType;

    //手机号
    private  String phone;
    //写手姓名
    private  String userName;
    //写手昵称
    private  String nickName;

    //排序字段
    private String orderFiled;
    //排序字段 forward_cnt:转发次数，forward_amount:转发金额，beforward_cnt:被转发次数，
    //integral_total:总收益，account_sum:消费账户余额,      integral_sum:积分账户余额，generalize_num:推广人数
    //排序方式 desc倒序,asc升序
    private String orderValue;

    //写手id
    private Long writerId;

    /**
     * 生成排序字符串
     */
    private String orderStr;

    //提现金额
    private BigDecimal settlementAmount;

    //提现消息
    private String settlementMsg;

    //修改人
    private String lastUpdateUserId;

    //处理标识,0代表未处理 1代表处理
    private Integer handleFlag;

    //流水号
    private  String profitSn;

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOrderFiled() {
        return orderFiled;
    }

    public void setOrderFiled(String orderFiled) {
        this.orderFiled = orderFiled;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public Integer getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getSettlementMsg() {
        return settlementMsg;
    }

    public void setSettlementMsg(String settlementMsg) {
        this.settlementMsg = settlementMsg;
    }

    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Integer getHandleFlag() {
        return handleFlag;
    }

    public void setHandleFlag(Integer handleFlag) {
        this.handleFlag = handleFlag;
    }

    public String getProfitSn() {
        return profitSn;
    }

    public void setProfitSn(String profitSn) {
        this.profitSn = profitSn;
    }
}

package com.yryz.writer.modules.profit.vo;

/**
 * @ClassName: ProfitDetailVo
 * @Description: ProfitDetailVo
 * @author wangsenyong
 * @date 2017-12-29 15:36:15
 *
 */
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProfitDetailVo implements Serializable {

    //流水id
    private Long flowId;
    /**
     * 结算金额
     */
    private BigDecimal settlementAmount;

    /**
     * 结算日期
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private String settlementDate;

    /**
     * 结算来源
     */
    private String settlementType;

    /**
     * 流水号
     */
    private String profitSn;

    /**
     * 剩余可提现金额
     */
    private BigDecimal surplusAmount;

    //流水消息
    private  String settlementMsg;

    public String getSettlementMsg() {
        return settlementMsg;
    }

    public void setSettlementMsg(String settlementMsg) {
        this.settlementMsg = settlementMsg;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public BigDecimal getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(BigDecimal surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public String getProfitSn() {
        return profitSn;
    }

    public void setProfitSn(String profitSn) {
        this.profitSn = profitSn;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }
}

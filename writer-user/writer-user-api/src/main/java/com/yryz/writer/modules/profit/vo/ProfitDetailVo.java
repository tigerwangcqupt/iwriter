package com.yryz.writer.modules.profit.vo;

/**
 * @ClassName: ProfitDetailVo
 * @Description: ProfitDetailVo
 * @author wangsenyong
 * @date 2017-12-29 15:36:15
 *
 */
import java.io.Serializable;
import java.math.BigDecimal;

public class ProfitDetailVo implements Serializable {

    /**
     * 结算金额
     */
    private BigDecimal settlementAmount;

    /**
     * 结算日期
     */
    private String settlementDate;

    /**
     * 结算来源
     */
    private String settlementType;

    /**
     * 剩余可提现金额
     */
    private BigDecimal surplusAmount;

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

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
}

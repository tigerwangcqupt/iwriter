package com.yryz.writer.modules.profit.vo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.yryz.writer.modules.profit.entity.Profit;

/**
 * @ClassName: ProfitVo
 * @Description: ProfitVo
 * @author zhongying
 * @date 2017-12-29 15:36:15
 *
 */
public class ProfitVo implements Serializable {

    /**
     * 累计提现金额
     */
    private BigDecimal accumulativeAmount;

    /**
     * 可提现金额
     */
    private BigDecimal availableAmount;

    /**
     * 最近提现金额
     */
    private BigDecimal currentAmount;


    private List<ProfitDetailVo> list;
    /**
     * 日期
     */
    private String settlementDate;

    public BigDecimal getAccumulativeAmount() {
        return accumulativeAmount;
    }

    public void setAccumulativeAmount(BigDecimal accumulativeAmount) {
        this.accumulativeAmount = accumulativeAmount;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public List<ProfitDetailVo> getList() {
        return list;
    }

    public void setList(List<ProfitDetailVo> list) {
        this.list = list;
    }
}

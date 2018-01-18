package com.yryz.writer.modules.profit.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 平台现金资金信息
 */
public class ProfitAccountVo implements Serializable{
    /**
     * 现金账户
     */
    private BigDecimal ammount;

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }
}

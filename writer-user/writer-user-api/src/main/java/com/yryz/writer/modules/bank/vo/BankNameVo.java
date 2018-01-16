package com.yryz.writer.modules.bank.vo;

import java.io.Serializable;

/**
 * @ClassName: BankVo
 * @Description: BankVo
 * @author wangsenyong
 * @date 2018-1-16 15:38:16
 *
 */
public class BankNameVo implements Serializable{

    /**
     * 银行卡号
     */
    private String bankCard;

    /**
     * 银行卡号对应银行名称
     */
    private String bankName;

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}

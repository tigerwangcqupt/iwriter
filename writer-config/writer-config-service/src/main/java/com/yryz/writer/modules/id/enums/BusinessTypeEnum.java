package com.yryz.writer.modules.id.enums;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2017/8/16
 * @description 业务类型编号范围枚举
 */
public enum BusinessTypeEnum {
    /**
     * 主体
     */
    OWNER("owner", 18),
    /**
     * 账户
     */
    ACCOUNT("account", 18),

    /**
     * 银行卡
     */
    BANK_CARD("bank_card", 18);

    private String type;
    private Integer range;

    BusinessTypeEnum(String type, Integer range) {
        this.type = type;
        this.range = range;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }
}

package com.yryz.writer.modules.profit.constant;

import com.yryz.writer.common.constant.ExceptionEnum;

import java.util.HashMap;
import java.util.Map;

public enum ProfitEnum {

    WITHDRAWALS_FEE(1, "提现"),

    WITHDRAWALS_SUCCESS(2, "提现成功"),

    WITHDRAWALS_FAIL(3, "提现失败"),

    ROYALTIES_FEE(4, "稿费");


    private Integer code;

    private String msg;




    ProfitEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static final Map<Integer, ProfitEnum> profitEnumMap = new HashMap<>();

    static {
        if (ExceptionEnum.values() != null && ExceptionEnum.values().length > 0) {
            for (ProfitEnum profitEnum : ProfitEnum.values()) {
                profitEnumMap.put(profitEnum.getCode(), profitEnum);
            }
        }
    }
}

package com.yryz.writer.common.exception;


import com.yryz.writer.common.constant.ExceptionEnum;
import org.apache.commons.lang3.StringUtils;

public class YyrzPcException extends IllegalArgumentException {

    private String code;
    private String msg;
    private String errorMsg;

    public YyrzPcException(String code, String msg, String errorMsg) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
    }

    public static YyrzPcException busiError(String errorMsg) {
        return busiError(null, errorMsg);
    }

    public static YyrzPcException busiError(String msg, String errorMsg) {
        if (StringUtils.isBlank(msg)) {
            msg = ExceptionEnum.BusiException.getMsg();
        }
        if (StringUtils.isBlank(errorMsg)) {
            errorMsg = ExceptionEnum.BusiException.getErrorMsg();
        }
        return new YyrzPcException(ExceptionEnum.BusiException.getCode(), msg, errorMsg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

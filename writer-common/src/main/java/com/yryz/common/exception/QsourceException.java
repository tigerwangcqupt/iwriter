package com.yryz.common.exception;


import org.apache.commons.lang3.StringUtils;

import com.yryz.common.constant.ExceptionEnum;

public class QsourceException extends IllegalArgumentException {

    private String code;
    private String msg;
    private String errorMsg;

    public QsourceException(String code, String msg, String errorMsg) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
    }

    public static QsourceException busiError(String errorMsg) {
        return busiError(null, errorMsg);
    }

    public static QsourceException busiError(String msg, String errorMsg) {
        if (StringUtils.isBlank(msg)) {
            msg = ExceptionEnum.BusiException.getMsg();
        }
        if (StringUtils.isBlank(errorMsg)) {
            errorMsg = ExceptionEnum.BusiException.getErrorMsg();
        }
        return new QsourceException(ExceptionEnum.BusiException.getCode(), msg, errorMsg);
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

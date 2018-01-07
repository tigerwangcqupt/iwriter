package com.yryz.writer.common.constant;

public enum ExceptionEnum {

    SysException("1001", "系统异常", "系统异常"),
    ValidateException("2000", "数据验证失败！", "数据验证失败！"),
    LockException("3000", "分布式锁异常", "分布式锁异常"),
    BusiException("4000", "业务逻辑异常", "业务逻辑异常"),

    Exception("1000", "Server error; please try again later.", "服务器内部错误，未知异常！"),


    AddOwnerException("5001", "增加资金主体异常", "增加资金主体异常"),

    AddAccountException("5002", "增加资金账户异常", "增加资金账户异常"),

    BindBankException("5003", "绑定银行卡异常", "绑定银行卡异常"),

    //写手消息的异常码由8000开始
    MODULEENUM_NOTFOUND("7000", "Oops, the moduleenum is notfound!", "不存在的模块名！");

    private String code;

    private String msg;

    private String errorMsg;

    ExceptionEnum(String code, String msg, String errorMsg) {
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
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

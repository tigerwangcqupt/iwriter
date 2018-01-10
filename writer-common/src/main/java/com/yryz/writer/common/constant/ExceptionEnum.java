package com.yryz.writer.common.constant;

public enum ExceptionEnum {

    SysException("1001", "系统异常", "系统异常"),
    ValidateException("2000", "数据验证失败！", "数据验证失败！"),
    LockException("3000", "分布式锁异常", "分布式锁异常"),
    BusiException("4000", "业务逻辑异常", "业务逻辑异常"),

    Exception("1000", "Server error; please try again later.", "服务器内部错误，未知异常！"),

    //用户异常码从5000开始，应原生需求，与平台跳登录页的状态码统一，OTHER_DEVICE_LOGIN = "4"，NO_TOKEN_RE_LOGIN = "6"
    //用户异常码从5000开始，应原生需求，与平台跳登录页的状态码统一，OTHER_DEVICE_LOGIN = "4"，NO_TOKEN_RE_LOGIN = "6"
    //跳转登录页
    NOT_LOGIN("5000",  "用户未登录", "暂未获取到您的信息，请先登录"),

    //跳转登录页
    OTHER_DEVICE_LOGIN("4",  "您已在其他设备登录，请注意账户安全！", "您的账号已在其他设备登录，请注意账号安全！"),

    PHONE_REGISTERED("5001", "该手机号已注册！", "该手机号已注册！"),
    PHONE_UNREGISTERED("5002", "该手机号未注册！", "该手机号未注册！"),
    USER_REGISTERED("5003", "该用户已注册，请检查后再试！", "该用户已注册，请检查后再试！"),

    REGISTER_UNSUCCESSFUL("5004", "注册失败，请检查后再试!", "注册失败，请检查后再试!"),
    PIN_ERROR("5005", "您输入的验证码不正确！", "您输入的验证码不正确！"),
    USER_UNREGISTERED("5006", "您还未注册，请先去注册！", "您还未注册，请先去注册！"),

    PASSWORD_ERROR("5007", "您输入的密码有误！", "您输入的密码有误！"),

    AUTHORIZED_LOGIN_FAILED("5009", "授权登录失败，请稍候再试！", "授权登录失败，请稍候再试！"),

    OLD_PASSWORD_ERROR("5010", "旧密码不正确！", "旧密码不正确！"),
    CAN_NOT_ALERT_PASSWORD("5011", "系统繁忙，暂时无法为您修改密码！", "系统繁忙，暂时无法为您修改密码！"),
    BIND_UNSUCCESSFUL("5012", "绑定失败！该账户已绑定！", "绑定失败！该账户已绑定！"),

    UNBIND_UNSUCCESSFUL("5013", "解绑失败！", "解绑失败！"),
    GET_APP_VERSION_UNSUCCESSFUL("5014", "获取APP版本信息失败，请稍候再试！", "获取APP版本信息失败，请稍候再试！"),
    PHONE_UNBIND("5015", "该手机号未绑定，无法设置密码！", "该手机号未绑定，无法设置密码！"),
    HAS_SET_PASSWORD("5016", "已设置过密码！", "已设置过密码！"),
    PIN_FREQUENTLY("5024", "发送验证码过于频繁，请稍候再试！", "发送验证码过于频繁，请稍候再试！"),


    AddOwnerException("6001", "增加资金主体异常", "增加资金主体异常"),

    AddAccountException("6002", "增加资金账户异常", "增加资金账户异常"),

    BindBankException("6003", "绑定银行卡异常", "绑定银行卡异常"),

    TxMoreThanSurplusException("6004", "当前提现金额大于剩余可提现金额", "当前提现金额大于剩余可提现金额"),

    TxNotIntException("6005", "当前提现金额不是整数", "当前提现金额不是整数"),
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

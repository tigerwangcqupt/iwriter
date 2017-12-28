package com.yryz.common.constant;

public enum ExceptionEnum {

    SysException("1001", "Server error; please try again later.", "系统异常"),
    ValidateException("2000", "Server error; please try again later.", "数据验证失败！"),
    LockException("3000", "Server error; please try again later.", "分布式锁异常"),
    BusiException("4000", "Server error; please try again later.", "业务逻辑异常"),
    COMMENT_SOURCE_NOTEXIST_EXCEPTION("4001", "Oops, the comment was deleted.", "回复的评论不存在或者已经删除"),
    COMMENT_INFOID_TOPID_EMPTY("4002", "Server error; please try again later.", "资源ID与父评论ID不能同时为空"),
    INFORM_REPEAT_USERID("4003", "You've already reported!", "您已经举报过此资源"),
    INFORM_UNPROCESSED("4004", "This report has been processed.", "此举报已处理"),
    FAVORITE_IS_NULL("4005", "Server error; please try again later.", "收藏数据内容不能为空"),
    FAVORITE_NOT_EXIST("4006", "Oops, this favorite has been removed.", "此收藏记录不存在或者已删除"),

    //用户异常码从5000开始，应原生需求，与平台跳登录页的状态码统一，OTHER_DEVICE_LOGIN = "4"，NO_TOKEN_RE_LOGIN = "6"
    //跳转登录页
    NOT_LOGIN("5000", "Please log in first.", "用户未登录"),
    //跳转登录页
    ACCOUNT_FROZEN("6", "Your account has been suspended! Reason:{reason} Please contact customer service.", "您的账户已被冻结！"),
    //跳转登录页
    OTHER_DEVICE_LOGIN("4", "Please pay attention to account security; your account has logged in on another device.", "您已在其他设备登录，请注意账户安全！"),
    //跳转登录页
    NO_TOKEN_RE_LOGIN("6", "Network error; please log in again.", "亲，网络开小差了，重新登录一下就好了！"),
    //跳转登录页
    BLACK_LIST("6", "You were blacklisted. Reason:{reason} Please contact customer service.", "您已经被加入黑名单，请联系客服。"),

    PHONE_REGISTERED("5001", "This phone number has been registered.", "该手机号已注册！"),
    PHONE_UNREGISTERED("5002", "This phone number has not been registered.", "该手机号未注册！"),
    USER_REGISTERED("5003", "You have registered! Please log in with registered account.", "该用户已注册，请检查后再试！"),

    REGISTER_UNSUCCESSFUL("5004", "Registration failed; please try again later.", "注册失败，请检查后再试!"),
    PIN_ERROR("5005", "Wrong PIN!", "您输入的验证码不正确！"),
    USER_UNREGISTERED("5006", "The user does not exist!", "您还未注册，请先去注册！"),

    PASSWORD_ERROR("5007", "Wrong password!", "您输入的密码有误！"),

    AUTHORIZED_LOGIN_FAILED("5009", "Authorized login failed! Please try again later.", "授权登录失败，请稍候再试！"),

    OLD_PASSWORD_ERROR("5010", "Wrong old password!", "旧密码不正确！"),
    CAN_NOT_ALERT_PASSWORD("5011", "System is busy! please modify password later.", "系统繁忙，暂时无法为您修改密码！"),
    BIND_UNSUCCESSFUL("5012", "Account already bound.", "绑定失败！该账户已绑定！"),

    UNBIND_UNSUCCESSFUL("5013", "The unbind failed.", "解绑失败！"),
    GET_APP_VERSION_UNSUCCESSFUL("5014", "Please try again later for App version!", "获取APP版本信息失败，请稍候再试！"),
    PHONE_UNBIND("5015", "Bind phone to set password!", "该手机号未绑定，无法设置密码！"),
    HAS_SET_PASSWORD("5016", "Password already set!", "已设置过密码！"),

    NETWORK_ERROR("5019", "Network error; please try again later.", "网络开小差了，请您稍候再试！"),
    ID_LENGTH_ILLEGAL("5020", "ID length should be 6 to 20 digits.", "ID的长度必须为6-20个字符"),
    ID_CAN_NOT_ALERT_AGAIN("5021", "ID can be modified once only!", "您已修改过一次ID了！"),

    ID_EXIST("5022", "This ID exists, please input another one.", "该ID已存在!"),

    USER_NOT_EXIST("5023", "Not exist.", "该用户不存在！"),


    //沙龙
    SALON_FULL("6001", "This salon is full, please join in next term.", "报名人数已满，等待下次活动"),
    SALON_END("6002", "The salon ends.", "活动已结束"),
    SALON_GROUP_DISMISSED("6003", "The group is dismissed.", "群聊已解散"),

    //问答的异常码由7000开始
    QUESTION_SHELVE("7000", "Oops, the question was deleted!", "您回答的问题已被下架或已被删除！"),
    QUESTION_HAS_BEEN_ANSWERED("7001", "You've already answered!", "您已经回答过一次该问题了！"),
    QUESTION_PIC_TOO_MANY("7002", "You have uploaded more than 10 of the pictures!", "您上传的图片超过了10张！"),

    Exception("1000", "Server error; please try again later.", "服务器内部错误，未知异常！");

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

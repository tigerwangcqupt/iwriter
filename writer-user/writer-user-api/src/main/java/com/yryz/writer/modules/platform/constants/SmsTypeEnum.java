package com.yryz.writer.modules.platform.constants;

/**
 * 短信功能码定义
 * 
 * @author pengnian
 *
 */
public class SmsTypeEnum {
	/* 功能码定义 */
	/**
	 *  CODE_UNKNOW(Integer.valueOf(0), "0"),
	 	CODE_REGISTER(Integer.valueOf(1), "1"),
	 	CODE_FIND_PWD(Integer.valueOf(2), "2"),
	 	CODE_IDENTITY(Integer.valueOf(3), "3"),
	 	CODE_SET_PAYPWD(Integer.valueOf(4), "4"),
	 	CODE_CHANGE_PHONE(Integer.valueOf(5), "5"),
	 	CODE_CHANGE_PAYPWD(Integer.valueOf(6), "6"),
	 	CODE_TAKE_CASH(Integer.valueOf(7), "7"),
	 	CODE_OTHER(Integer.valueOf(8), "8"),
	 	CODE_LOGIN(Integer.valueOf(9), "9");
	 */

	/**
	 * check 注册 、 找回密码
	 */
	public static final String CODE_UNKNOW = "0";
	/**
	 * 注册
	 */
	public static final String CODE_REGISTER = "1";
	/**
	 * 找回密码
	 */
	public static final String CODE_FIND_PWD = "2";
	/**
	 * 实名认证
	 */
	public static final String CODE_IDENTITY = "3";
	/**
	 * 设置支付密码
	 */
	public static final String CODE_SET_PAYPWD = "4";
	/**
	 * 变更手机
	 */
	public static final String CODE_CHANGE_PHONE = "5";
	/**
	 * 找回支付密码
	 */
	public static final String CODE_CHANGE_PAYPWD = "6";
	/**
	 * 提现
	 */
	public static final String CODE_TAKE_CASH = "7";
	
	/**
	 * 其他（只取验证码）
	 */
	public static final String CODE_OTHER = "8";

	/**
	 * 登录
	 */
	public static final String CODE_LOGIN = "9";

}

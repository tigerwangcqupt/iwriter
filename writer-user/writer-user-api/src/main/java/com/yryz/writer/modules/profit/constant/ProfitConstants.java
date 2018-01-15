package com.yryz.writer.modules.profit.constant;

public class ProfitConstants {

    //订单前缀
    public static final String OREDERPREFIX= "yryz_new_";

    //profit表名
    public static final String PROFITTABLE= "yryz_profit";

    //手续费固定成2元
    public static  final  Integer CHARGEFEE=2;

    //资金主体个人
    public static final Byte OWNERTYPE = 2;

    //用户收益类型
    public static final Integer USERACCOUNTTYPECODE = 4;

    //平台收益类型
    public static final Integer PLATACCOUNTTYPECODE = 3;

    //平台现金类型
    public static final Integer PLATCASHTYPECODE = 1;

    //入账
    public static final Integer ACCOUNTINGFLAG = 10;

    //出账
    public static final Integer ACCOUNTINGSUBFLAG = 20;

    //现金
    public static final Integer CASHFLAG = 10;

    //非现金
    public static final Integer NOTCASHFLAG = 20;

    //核算
    public static final Integer CHECKFLAG = 10;

    //不核算
    public static final Integer NOTCHECKFLAG = 20;

    //账户状态,生效
    public static final Byte ACCOUNTSTATUS = 1;

    //稿费流水条数
    public static final Byte ROYALTIESFLOWNUM = 2;

    //提现流水
    public static final Byte TXFLOWNUM = 3;

    //前台调用
    public static final Integer CALLEDBYFRONT = 1;

    //是否可提现标识 1:可提现 0:不可提现
    public static final Integer WITHDRAWALSFLAG = 1;

    //是否可提现标识 1:可提现 0:不可提现
    public static final Integer NOTWITHDRAWALSFLAG = 0;

    //提现最小金额
    public static final Integer MIN_AMOUNT = 500;

    //提现最大金额
    public static final Integer MAX_AMOUNT = 10000;

    //申请提现是否处理, 0:未处理
    public static final Integer NOT_HANDLEFLAG = 0;

}

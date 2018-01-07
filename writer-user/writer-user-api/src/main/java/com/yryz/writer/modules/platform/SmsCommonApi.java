package com.yryz.writer.modules.platform;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.service.api.basic.entity.SmsReqVo;
import com.yryz.service.api.basic.entity.SmsVerifyCode;

public interface SmsCommonApi {

    /**
     * 发送验证码
     *
     * @param phone
     * @param code
     * @return
     */
    public RpcResponse<SmsVerifyCode> sendVerifyCode(String phone, String code);

    /**
     * 发送自定消息验证码
     *
     * @param smsReq
     * @return
     */
    public RpcResponse<SmsVerifyCode> sendVerifyCode(SmsReqVo smsReq);

    /**
     * 验证短信、成功后删除
     *
     * @param phone      手机号
     * @param code       功能码
     * @param verifyCode 验证码
     * @return 功能码类型
     */
    public RpcResponse<Boolean> checkVerifyCode(String phone, String code, String verifyCode);

    /**
     * 发送自定义短信消息
     *
     * @param smsReq
     * @return
     * @throws com.yryz.service.api.api.exception.ServiceException
     */
    public RpcResponse<Boolean> sendMessage(SmsReqVo smsReq);
}

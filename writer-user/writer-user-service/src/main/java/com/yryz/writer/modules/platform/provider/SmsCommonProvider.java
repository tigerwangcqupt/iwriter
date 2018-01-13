package com.yryz.writer.modules.platform.provider;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.service.api.basic.entity.SmsReqVo;
import com.yryz.service.api.basic.entity.SmsVerifyCode;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.platform.SmsCommonApi;
import com.yryz.writer.modules.platform.service.SmsCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsCommonProvider implements SmsCommonApi {

    @Autowired
    private SmsCommonService smsService;

    public static final Logger LOGGER = LoggerFactory.getLogger(SmsCommonProvider.class);

    @Override
    public RpcResponse<SmsVerifyCode> sendVerifyCode(String phone, String code) throws ServiceException {
        try {
            return ResponseModel.returnObjectSuccess(smsService.sendVerifyCode(phone, code));
        } catch (Exception e) {
            LOGGER.error("发送验证码失败！" + e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<SmsVerifyCode> sendVerifyCode(SmsReqVo smsReq) throws ServiceException {
        try {
            return ResponseModel.returnObjectSuccess(smsService.sendVerifyCode(smsReq));
        } catch (Exception e) {
            LOGGER.error("发送验证码失败！" + e);
            if (e instanceof YyrzPcException) {
                YyrzPcException qsourceException = (YyrzPcException) e;
                qsourceException.setCode(ExceptionEnum.PIN_FREQUENTLY.getCode());
                qsourceException.setMsg(ExceptionEnum.PIN_FREQUENTLY.getMsg());
                qsourceException.setErrorMsg(ExceptionEnum.PIN_FREQUENTLY.getErrorMsg());
            }
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Boolean> checkVerifyCode(String phone, String code, String verifyCode) throws ServiceException {
        try {
            return ResponseModel.returnObjectSuccess(smsService.checkVerifyCode(phone, code, verifyCode));
        } catch (Exception e) {
            LOGGER.error("校验验证码失败！" + e);
            return ResponseModel.returnException(e);
        }
    }

    @Override
    public RpcResponse<Boolean> sendMessage(SmsReqVo smsReq) throws ServiceException {
        try {
            return ResponseModel.returnObjectSuccess(smsService.sendMessage(smsReq));
        } catch (Exception e) {
            LOGGER.error("发送消息失败！" + e);
            return ResponseModel.returnException(e);
        }
    }
}

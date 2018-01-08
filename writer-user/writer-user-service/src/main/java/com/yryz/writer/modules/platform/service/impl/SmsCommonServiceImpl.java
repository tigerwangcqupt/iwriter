package com.yryz.writer.modules.platform.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.yryz.service.api.basic.api.SmsAPI;
import com.yryz.service.api.basic.entity.SmsReqVo;
import com.yryz.service.api.basic.entity.SmsVerifyCode;
import com.yryz.writer.common.exception.QsourceException;
import com.yryz.writer.modules.platform.service.SmsCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsCommonServiceImpl implements SmsCommonService {

    @Autowired
    private SmsAPI smsAPI;

    @Value("${appId}")
    private String appId;

    public static final Logger LOGGER = LoggerFactory.getLogger(SmsCommonServiceImpl.class);

    /**
     * 发送验证码
     *
     * @param phone
     * @param code
     * @return
     */
    @Override
    public SmsVerifyCode sendVerifyCode(String phone, String code) {
        try {
            RpcContext.getContext().setAttachment("appId", appId);

            SmsReqVo smsReqVo = new SmsReqVo();
            smsReqVo.setPhone(phone);
            smsReqVo.setCode(code);
            //return smsAPI.sendVerifyCode(smsReqVo);
            return null;
        } catch (com.yryz.service.api.api.exception.ServiceException e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw QsourceException.busiError("调用平台PRC接口出错！" + e.getMsg());
        } catch (Exception e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw QsourceException.busiError("调用平台PRC接口出错！" + e.getMessage());
        }
    }

    /**
     * 发送自定消息验证码
     *
     * @param smsReq
     * @return
     */
    @Override
    public SmsVerifyCode sendVerifyCode(SmsReqVo smsReq) {
        try {
            RpcContext.getContext().setAttachment("appId", appId);
            return smsAPI.sendVerifyCode(smsReq);
        } catch (com.yryz.service.api.api.exception.ServiceException e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw QsourceException.busiError("调用平台PRC接口出错！" + e.getMsg());
        } catch (Exception e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw QsourceException.busiError("调用平台PRC接口出错！" + e.getMessage());
        }
    }

    /**
     * 验证短信、成功后删除
     *
     * @param phone      手机号
     * @param code       功能码
     * @param verifyCode 验证码
     * @return 功能码类型
     */
    @Override
    public Boolean checkVerifyCode(String phone, String code, String verifyCode) {
        try {
            RpcContext.getContext().setAttachment("appId", appId);
            //return smsAPI.checkVerifyCode(phone, code, verifyCode);
            return null;
        } catch (com.yryz.service.api.api.exception.ServiceException e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw QsourceException.busiError("调用平台PRC接口出错！" + e.getMsg());
        } catch (Exception e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw QsourceException.busiError("调用平台PRC接口出错！" + e.getMessage());
        }
    }

    /**
     * 发送自定义短信消息
     *
     * @param smsReq
     * @return
     * @throws com.yryz.service.api.api.exception.ServiceException
     */
    @Override
    public Boolean sendMessage(SmsReqVo smsReq) {
        try {
            RpcContext.getContext().setAttachment("appId", appId);
            //return smsAPI.sendMessage(smsReq);
            return null;
        } catch (com.yryz.service.api.api.exception.ServiceException e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw QsourceException.busiError("调用平台PRC接口出错！" + e.getMsg());
        } catch (Exception e) {
            LOGGER.error("调用平台PRC接口出错！详细原因：" + e);
            throw QsourceException.busiError("调用平台PRC接口出错！" + e.getMessage());
        }
    }

}

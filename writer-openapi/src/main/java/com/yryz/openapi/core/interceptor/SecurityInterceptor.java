package com.yryz.openapi.core.interceptor;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.service.api.user.entity.AuthCheck;
import com.yryz.writer.common.Annotation.Login;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.constant.AppConstants;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.QsourceException;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.entity.Writer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 *
 * @Description: 判断接口是否需要登录，用户是否违规，并校验用户Token合法性
 * @Date: Created in 2017 2017/11/16 13:50
 * @Author: pn
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private WriterApi writerApi;

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object bean = handlerMethod.getBean();
        boolean login = false;
        if (bean instanceof BaseController) {
            BaseController baseController = (BaseController) bean;
            login = baseController.getClass().isAnnotationPresent(Login.class);
        }
        boolean notLogin = handlerMethod.getMethod().isAnnotationPresent(NotLogin.class);
        if (login && notLogin) {
            return true;
        }

        String userId = request.getHeader(AppConstants.USER_ID);
        String token = request.getHeader(AppConstants.TOKEN);
        /*if (StringUtils.isBlank(userId)) {
            userId = request.getParameter(AppConstants.USER_ID);

        }
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(AppConstants.TOKEN);
        }*/

        if(userId == null || userId.trim().length()==0){
            throw new QsourceException(ExceptionEnum.NOT_LOGIN.getCode(), ExceptionEnum.NOT_LOGIN.getMsg(), ExceptionEnum.NOT_LOGIN.getErrorMsg());
        }

        if(token == null || token.trim().length()==0){
            throw new QsourceException(ExceptionEnum.NOT_LOGIN.getCode(), ExceptionEnum.NOT_LOGIN.getMsg(), ExceptionEnum.NOT_LOGIN.getErrorMsg());
        }

        RpcResponse<Writer> rpcResponseWriterSave = writerApi.get(Long.valueOf(userId));
        Writer user = isSuccess(rpcResponseWriterSave);
        if (user == null) {
            throw new QsourceException(ExceptionEnum.NOT_LOGIN.getCode(), ExceptionEnum.NOT_LOGIN.getMsg(), ExceptionEnum.NOT_LOGIN.getErrorMsg());
        }


        RpcResponse<String> tokenRpcResponse = writerApi.getUserToken(String.valueOf(userId));
        String tokenReids = isSuccess(tokenRpcResponse);
        if(!tokenReids.equals(token)){
            throw new QsourceException(
                    ExceptionEnum.OTHER_DEVICE_LOGIN.getCode(),
                    ExceptionEnum.OTHER_DEVICE_LOGIN.getMsg(),
                    ExceptionEnum.OTHER_DEVICE_LOGIN.getErrorMsg());
        }

        return true;
    }


    /**
     * 判断PRC接口返回结果不判断null值，根据业务需求自己判断
     *
     * @param rpcResponse
     * @param <T>
     * @return
     */
    public <T> T isSuccess(RpcResponse<T> rpcResponse) {
        Assert.notNull(rpcResponse, "调用PRC接口异常！");
        if (rpcResponse.success()) {
            return rpcResponse.getData();
        }
        throw new QsourceException(rpcResponse.getCode(), rpcResponse.getMsg(), rpcResponse.getErrorMsg());
    }

}

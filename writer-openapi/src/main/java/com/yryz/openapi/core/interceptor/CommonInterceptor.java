package com.yryz.openapi.core.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangsenyong
 * @version 1.0
 * @date 2018年1月5日 下午3:07:18
 * @Description 通用，支持跨域
 */
public class CommonInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Method", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Encoding, Accept, X-Requested-With, "
                + "Content-Type, X-Forwarded-For, Proxy-Client-IP, WL-Proxy-Client-IP, HTTP_CLIENT_IP, "
                + "HTTP_X_FORWARDED_FOR,originText,sign, token, appVersion, v, devType, devName, devId, ip, net, custId, appId, appSecret, userId, language");
        response.setContentType("text/json;charset=utf-8");
        String httpMethod = request.getMethod();
        if (RequestMethod.OPTIONS.name().equals(httpMethod)) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }


}
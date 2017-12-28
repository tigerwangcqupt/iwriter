package com.yryz.common.web;

import com.yryz.common.Annotation.Login;
import com.yryz.common.exception.QsourceException;
import com.yryz.component.rpc.RpcResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 *
 * @Description: 控制器基类
 * @Date: Created in 2017 2017/11/16 15:24
 * @Author: pn
 */
@Login
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Value("${tenantId}")
    protected String tenantId;

    /**
     * 判断PRC接口返回结果，判断若为null值，则抛出异常
     *
     * @param rpcResponse
     * @param <T>
     * @return
     */
    public <T> T isSuccessNotNull(RpcResponse<T> rpcResponse) {
        Assert.notNull(rpcResponse, "调用PRC接口异常！");
        if (rpcResponse.success()) {
            T t = rpcResponse.getData();
            if (t == null) {
                throw QsourceException.busiError("调用RPC接口，返回结果数据为：null");
            }
            return t;
        }
        throw new QsourceException(rpcResponse.getCode(), rpcResponse.getMsg(), rpcResponse.getErrorMsg());
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

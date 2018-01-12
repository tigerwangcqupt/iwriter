package com.yryz.writer.common.web;


import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.QsourceException;
import com.yryz.component.rpc.ResponseCode;
import com.yryz.component.rpc.ResponseProducer;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.internal.DubboResponse;
import com.yryz.writer.common.exception.YyrzPcException;
import org.springframework.web.bind.MissingServletRequestParameterException;

public class ResponseModel extends ResponseProducer {

    public static <T> RpcResponse<T> returnObjectSuccess(T t) {
        return new DubboResponse<>(true, ResponseCode.INVOKE_SUCCESS, ResponseCode.INVOKE_SUCCESS_MSG, "", t);
    }

    public static <T> RpcResponse<T> returnListSuccess(T t) {
        return new DubboResponse<>(true, ResponseCode.INVOKE_SUCCESS, ResponseCode.INVOKE_SUCCESS_MSG, "", t);
    }

    public static <T> RpcResponse<T> returnException(Exception e) {
        if (e instanceof QsourceException) {
            QsourceException qe = (QsourceException) e;
            return new DubboResponse<T>(false, qe.getCode(), qe.getMsg(), qe.getErrorMsg(), null);
        }
        else if (e instanceof YyrzPcException) {
            YyrzPcException qe = (YyrzPcException) e;
            return new DubboResponse<T>(false, qe.getCode(), qe.getMsg(), qe.getErrorMsg(), null);
        }
        else if (e instanceof IllegalArgumentException) {
            IllegalArgumentException ll = (IllegalArgumentException) e;
            return new DubboResponse<T>(false,
                    ExceptionEnum.ValidateException.getCode(),
                    ExceptionEnum.ValidateException.getMsg(),
                    ll.getMessage(),
                    null);
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException msrp = (MissingServletRequestParameterException) e;
            return new DubboResponse<T>(false,
                    ExceptionEnum.ValidateException.getCode(),
                    ExceptionEnum.ValidateException.getMsg(),
                    msrp.getMessage(),
                    null);
        } else {
            return new DubboResponse<T>(false,
                    ExceptionEnum.Exception.getCode(),
                    ExceptionEnum.Exception.getMsg(),
                    ExceptionEnum.Exception.getErrorMsg(),
                    null);
        }
    }

}

package com.yryz.openapi.core.auth.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yryz.openapi.core.interceptor.SecurityInterceptor;
import com.yryz.openapi.core.validator.annotation.Validate;
import com.yryz.writer.common.constant.AppConstants;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.utils.DateUtil;
import com.yryz.writer.common.utils.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

public class ResourceAuthHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceAuthHandler.class);

    @Value("${appSercet}")
    protected String appSercet;

    /**
     * 验证签名是否有效
     *
     * @param jp 切面方法对象
     */
    protected void validateSign(JoinPoint jp) {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        //得到参数值集合(开始)
        Date date  = new Date();
        Object[] paramValues = jp.getArgs();
        String[] paramNames = ((CodeSignature) signature).getParameterNames();
        //原文
        String originText = "";
        //签名后的sign
        String sign = "";
        for(int i=0;i<paramNames.length;i++){
            LOGGER.info(paramNames[i]+","+paramValues[i]);
            if(AppConstants.originText.equals(paramNames[i])){
                originText = paramValues[i].toString();
                String timeStamp = originText.substring(originText.lastIndexOf("=")+1);
                String timeStampDateStr = DateUtil.stampToDate(timeStamp);
                int diffMinutes = DateUtil.getDiffMinutes(timeStampDateStr,date);
                if(diffMinutes>AppConstants.diffMinutes){
                    LOGGER.error("调用接口频率过高！");
                    throw new YyrzPcException(ExceptionEnum.CallFrequentlyException.getCode(),ExceptionEnum.CallFrequentlyException.getMsg(),
                            ExceptionEnum.CallFrequentlyException.getErrorMsg());
                }
            }
            if(AppConstants.sign.equals(paramNames[i])){
                sign = paramValues[i].toString();
            }
        }
        //原文为空
        if(StringUtils.isEmpty(originText)){
            LOGGER.error("原文为空");
            throw new YyrzPcException(ExceptionEnum.NullOriginException.getCode(),ExceptionEnum.NullOriginException.getMsg(),
                    ExceptionEnum.NullOriginException.getErrorMsg());
        }
        //签名为空
        if(StringUtils.isEmpty(sign)){
            LOGGER.error("签名为空");
            throw new YyrzPcException(ExceptionEnum.NullSignException.getCode(),ExceptionEnum.NullSignException.getMsg(),
                    ExceptionEnum.NullSignException.getErrorMsg());
        }
        //原文加密后的sign
        String signCheck = Md5Utils.encode(originText+appSercet);
        if(!sign.equals(signCheck)){
            LOGGER.error("接口签名不正确");
            throw new YyrzPcException(ExceptionEnum.NotValidSignException.getCode(),ExceptionEnum.NotValidSignException.getMsg(),
                    ExceptionEnum.NotValidSignException.getErrorMsg());
        }
    }

}

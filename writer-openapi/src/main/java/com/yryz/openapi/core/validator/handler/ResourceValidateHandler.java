package com.yryz.openapi.core.validator.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.openapi.core.validator.annotation.Validate;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.QsourceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Set;

public class ResourceValidateHandler {

    @Autowired
    private Validator validator;

    /**
     * 验证实体对象属性有效性
     *
     * @param jp 切面方法对象
     */
    protected void validate(JoinPoint jp) {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Validate validate = method.getDeclaredAnnotation(Validate.class);
        if (null == validate) {
            return;
        }

        //得到参数值集合(开始)
        Object[] paramValues = jp.getArgs();
        String[] paramNames = ((CodeSignature) signature).getParameterNames();
        for(int i=0;i<paramNames.length;i++){
            System.out.println(paramNames[i]+","+paramValues[i]);
        }
        //得到参数值集合(结束)



        Object[] args = jp.getArgs();
        if (null != args && args.length > 0) {
            for (Object obj : args) {
                if (obj != null) {
                    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
                    if (!CollectionUtils.isEmpty(constraintViolations)) {
                        JSONObject errorMessage = new JSONObject();
                        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                            String fieldName = constraintViolation.getPropertyPath().toString();
                            if (errorMessage.containsKey(fieldName)) {
                                errorMessage.getJSONArray(fieldName).add(constraintViolation.getMessage());
                            } else {
                                JSONArray array = new JSONArray();
                                array.add(constraintViolation.getMessage());
                                errorMessage.put(fieldName, array);
                            }
                        }
                        throw new YyrzPcException(ExceptionEnum.ValidateException.getCode(),
                                ExceptionEnum.ValidateException.getMsg(), errorMessage.toJSONString());
                    }
                }
            }
        }
    }

}

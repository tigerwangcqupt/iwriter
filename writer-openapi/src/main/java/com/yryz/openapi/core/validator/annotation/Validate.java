package com.yryz.openapi.core.validator.annotation;

import java.lang.annotation.*;

/**
 * Created by lifan on 2017/5/18.
 * 用于注解update场景时不对Pojo对象做验证
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
}

package com.yryz.openapi.core.auth.annotation;

import java.lang.annotation.*;

/**
 * Created by wangsenyong on 2018/1/13.
 * 用于注解接口鉴权
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InterFaceAuth {
}

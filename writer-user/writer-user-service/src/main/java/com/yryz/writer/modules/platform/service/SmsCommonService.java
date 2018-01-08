package com.yryz.writer.modules.platform.service;

import com.yryz.service.api.basic.entity.SmsReqVo;
import com.yryz.service.api.basic.entity.SmsVerifyCode;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2017 2017/11/20 14:02
 * @Author: pn
 */
public interface SmsCommonService {

    SmsVerifyCode sendVerifyCode(String phone, String code);

    SmsVerifyCode sendVerifyCode(SmsReqVo smsReq);

    Boolean checkVerifyCode(String phone, String code, String verifyCode);

    Boolean sendMessage(SmsReqVo smsReq);
}

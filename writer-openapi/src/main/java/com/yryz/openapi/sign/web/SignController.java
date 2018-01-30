package com.yryz.openapi.sign.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.openapi.core.auth.entity.SignInfo;
import com.yryz.service.api.basic.entity.SmsReqVo;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.utils.Md5Utils;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.bank.entity.Bank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("services/app/v1/sign")
public class SignController extends BaseController {

    @Value("${appSercet}")
    protected String appSercet;

    /**
     * 根据原文得到签名信息
     * 返回原文加盐后的md5串
     * @param originText
     * @return
     */
    @NotLogin
    @RequestMapping(value="/detail", method = RequestMethod.POST)
    @ResponseBody
    public RpcResponse<SignInfo> getSign(@RequestBody Map<String, Object> map){

        //originText：a=1&b=2&timeStamp=123456
        String originText = (String) map.get("originText");

        Assert.notNull(originText, "原文不能为空");
        SignInfo signInfo = new SignInfo();
        signInfo.setOriginText(originText);
        signInfo.setSign(Md5Utils.encode(originText+appSercet));
        return ResponseModel.returnObjectSuccess(signInfo);
    }
}

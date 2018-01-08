package com.yryz.openapi.user.web;

import com.alibaba.fastjson.JSON;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.internal.DubboResponse;
import com.yryz.service.api.basic.constants.SmsContants;
import com.yryz.service.api.basic.entity.SmsReqVo;
import com.yryz.service.api.basic.entity.SmsVerifyCode;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.QsourceException;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.modules.platform.SmsCommonApi;
import com.yryz.writer.modules.platform.constants.SmsTypeEnum;
import com.yryz.writer.modules.platform.dto.CustRegisterDto;
import com.yryz.writer.modules.platform.vo.AuthInfoVo;
import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.entity.Writer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Controller
@RequestMapping("services/app/v1/user")
public class UserAppController extends BaseController {

    @Autowired
    private SmsCommonApi smsCommonApi;

    @Autowired
    private WriterApi writerApi;

    @Value("${user.defaultImg}")
    private String userDefaultImg;

    @Value("${appId}")
    private String appId;

    @Value("${refreshToken}")
    private Boolean refreshToken;

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAppController.class);

    //TODO
    //要修改称新悠然一指的短信模板
    private static final String YRYZ_CHANNEL = "YRYZ";



    /**
     * 获取短信验证码
     *
     * @return
     */
    @RequestMapping(value = "register/code", method = {RequestMethod.POST})
    @ResponseBody
    @NotLogin
    public RpcResponse<SmsVerifyCode> getCode(@RequestBody SmsReqVo smsReq) {
        Assert.notNull(smsReq.getPhone(), "手机号不能为空！");
        Assert.notNull(smsReq.getCode(), "功能码不能为空！");

        Assert.hasText(smsReq.getPhone(), "手机号不能为空！");
        Assert.hasText(smsReq.getCode(), "功能码不能为空！");

        RpcResponse<Writer> rpcResponse = writerApi.selectByPhone(smsReq.getPhone());
        Writer user = isSuccess(rpcResponse);

        //注册
        if (SmsTypeEnum.CODE_REGISTER.equals(smsReq.getCode())) {
            phoneRegistered(user);
        //找回密码
        } else if (SmsTypeEnum.CODE_FIND_PWD.equals(smsReq.getCode())) {
            phoneUnregistered(user);
        //其他（只取验证码）
        } else if (SmsTypeEnum.CODE_OTHER.equals(smsReq.getCode())) {
            phoneUnregistered(user);
        //变更手机
        } else if (SmsTypeEnum.CODE_CHANGE_PHONE.equals(smsReq.getCode())) {
            phoneRegistered(user);
        }


        //选取短信模板
        smsReq.setTemplateType(Integer.valueOf(SmsContants.CODE_REGISTER));
        smsReq.setAppId(appId);
        smsReq.setChannel(YRYZ_CHANNEL);
        return smsCommonApi.sendVerifyCode(smsReq);
    }


    /**
     * 注册接口
     *
     * @param custRegister
     * @return
     */
    @RequestMapping(value = "register", method = {RequestMethod.POST})
    @ResponseBody
    @NotLogin
    public RpcResponse<AuthInfoVo> register(@RequestBody CustRegisterDto custRegister) {
        //检验邀请码
        Assert.notNull(custRegister, "参数缺少或错误！");

        Assert.notNull(custRegister.getCustPhone(), "手机号不能为空!");
        Assert.notNull(custRegister.getVeriCode(), "验证码不能为空!");
        Assert.notNull(custRegister.getCode(), "功能码不能为空!");

        Assert.hasText(custRegister.getCustPhone(), "手机号不能为空!");
        Assert.hasText(custRegister.getVeriCode(), "验证码不能为空!");
        Assert.hasText(custRegister.getCode(), "功能码不能为空!");

        //校验验证码
        RpcResponse<Boolean> rpcResponse = smsCommonApi.checkVerifyCode(custRegister.getCustPhone(), custRegister.getCode(), custRegister.getVeriCode());
        Boolean b = isSuccessNotNull(rpcResponse);
        if (b) {
            //判断安装渠道
           /* if (devType == AppConstants.DEVTYPE_IOS) {
                user.setChannel(AppConstants.INSTALL_CHANNEL);
            } else if (devType == AppConstants.DEVTYPE_ANDROID) {
                user.setChannel(custRegister.getInstallChannel());
            }*/

            RpcResponse<Writer> rpcResponseWriter = writerApi.selectByPhone(custRegister.getCustPhone());
            Writer writer = isSuccess(rpcResponseWriter);

            //验证手机号是否已注册
            phoneRegistered(writer);

            //用户注册
            Writer user = new Writer();
            user.setAccount(custRegister.getCustPhone());
            user.setPhone(custRegister.getCustPhone());
            user.setAccount(custRegister.getCustPhone());

            writerApi.register(user);

            //注册成功，消息推送
            /*executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    messagePush(user);
                }
            }, 10, TimeUnit.SECONDS);*/

            //输出参数
            AuthInfoVo authInfoVo = new AuthInfoVo();
            authInfoVo.setCustPhone(custRegister.getCustPhone());
            return new DubboResponse<AuthInfoVo>(true, "200", "success", "", authInfoVo);
        }

        //您输入的验证码不正确
        throw new QsourceException(
                ExceptionEnum.PIN_ERROR.getCode(),
                ExceptionEnum.PIN_ERROR.getMsg(),
                ExceptionEnum.PIN_ERROR.getErrorMsg());
    }

    /**
     * 判断是否设置过密码
     *
     * @param custRegisterDto
     * @return true 没有设置 false 已设置
     */
    @RequestMapping(value = "judgePwd", method = {RequestMethod.POST})
    @ResponseBody
    public RpcResponse<Boolean> judgePwd(@RequestBody CustRegisterDto custRegisterDto) {
        Assert.notNull(custRegisterDto, "缺少参数或参数错误！");
        Assert.notNull(custRegisterDto.getCustPhone(), "手机号不能为空！");
        Assert.hasText(custRegisterDto.getCustPhone(), "手机号不能为空！");


        RpcResponse<Writer> rpcResponseWriter = writerApi.selectByPhone(custRegisterDto.getCustPhone());
        Writer user = isSuccess(rpcResponseWriter);
        if (user != null) {
            if (user.getPwd() != null && user.getPwd() != null) {
                return new DubboResponse<Boolean>(true, "200", "success", "", false);
            } else {
                return new DubboResponse<Boolean>(true, "200", "success", "", true);
            }
        }
        throw new QsourceException(
                ExceptionEnum.USER_UNREGISTERED.getCode(),
                ExceptionEnum.USER_UNREGISTERED.getMsg(),
                ExceptionEnum.USER_UNREGISTERED.getErrorMsg());
    }


    /**
     * 设置密码
     *
     * @param custRegisterDto
     * @return
     */
    @RequestMapping(value = "setPwd", method = {RequestMethod.POST})
    @ResponseBody
    public RpcResponse<AuthInfoVo> setPwd(@RequestBody CustRegisterDto custRegisterDto) {
        Assert.notNull(custRegisterDto, "缺少参数或参数错误！");
        Assert.notNull(custRegisterDto.getCustPhone(), "手机号不能为空！");
        Assert.hasText(custRegisterDto.getCustPhone(), "手机号不能为空！");

        Assert.notNull(custRegisterDto.getCustPwd(), "密码不能为空！");
        Assert.hasText(custRegisterDto.getCustPwd(), "密码不能为空！");


        RpcResponse<Writer> rpcResponseWriter = writerApi.selectByPhone(custRegisterDto.getCustPhone());
        Writer user = isSuccess(rpcResponseWriter);
        if (user == null) {
            throw new QsourceException(
                    ExceptionEnum.PHONE_UNBIND.getCode(),
                    ExceptionEnum.PHONE_UNBIND.getMsg(),
                    ExceptionEnum.PHONE_UNBIND.getErrorMsg());
        }
        if (StringUtils.isNotBlank(user.getPwd())) {
            throw new QsourceException(
                    ExceptionEnum.HAS_SET_PASSWORD.getCode(),
                    ExceptionEnum.HAS_SET_PASSWORD.getMsg(),
                    ExceptionEnum.HAS_SET_PASSWORD.getErrorMsg());
        }


        //修改用户信息
        Writer writer = new Writer();
        writer.setKid(user.getKid());
        writer.setPwd(custRegisterDto.getCustPwd());
        writerApi.updateByPrimaryKeySelective(writer);


        return new DubboResponse<AuthInfoVo>(true, "200", "success", "", new AuthInfoVo());
    }

    /**
     * 手机号登陆
     *
     * @return
     */
    @RequestMapping(value = "login/phone", method = {RequestMethod.POST})
    @ResponseBody
    @NotLogin
    public RpcResponse<AuthInfoVo> login(@RequestBody Map<String, Object> map) {
        LOGGER.info("=======login user1: {}", JSON.toJSONString(map));
        AuthInfoVo authInfoVo = new AuthInfoVo();
        Assert.notNull(map, "用户信息为空！");

        Assert.notNull(map.get("phone"), "手机号不能为空！");
        Assert.notNull(map.get("password"), "密码不能为空！");

        String  phone = (String) map.get("phone");
        String  password = (String) map.get("password");
        Assert.hasText(phone, "手机号不能为空！");
        Assert.hasText(password, "密码不能为空！");


        //查询用户
        RpcResponse<Writer> userRpcResponse = writerApi.selectByPhone(phone);
        Writer user = isSuccess(userRpcResponse);
        if (user == null) {
            throw new QsourceException(
                    ExceptionEnum.USER_UNREGISTERED.getCode(),
                    ExceptionEnum.USER_UNREGISTERED.getMsg(),
                    ExceptionEnum.USER_UNREGISTERED.getErrorMsg());
        } else {
            if (password.equals(user.getPwd())) {
                RpcResponse<String> tokenRpcResponse = writerApi.getUserToken(String.valueOf(user.getKid()));
                String token = isSuccess(tokenRpcResponse);
                authInfoVo.setToken(token);
            } else {
                throw new QsourceException(
                        ExceptionEnum.PASSWORD_ERROR.getCode(),
                        ExceptionEnum.PASSWORD_ERROR.getMsg(),
                        ExceptionEnum.PASSWORD_ERROR.getErrorMsg());
            }
        }

        authInfoVo.setCustPhone(phone);
        return new DubboResponse<AuthInfoVo>(true, "200", "success", "", authInfoVo);
    }


    /**
     * 该手机号未注册
     * @param user
     */
    private void phoneUnregistered(Writer user) {
        if (user == null) {
            throw new QsourceException(
                    ExceptionEnum.PHONE_UNREGISTERED.getCode(),
                    ExceptionEnum.PHONE_UNREGISTERED.getMsg(),
                    ExceptionEnum.PHONE_UNREGISTERED.getErrorMsg());
        }
    }

    /**
     * 该手机号已注册
     * @param user
     */
    private void phoneRegistered(Writer user) {
        if (user != null) {
            throw new QsourceException(
                    ExceptionEnum.PHONE_REGISTERED.getCode(),
                    ExceptionEnum.PHONE_REGISTERED.getMsg(),
                    ExceptionEnum.PHONE_REGISTERED.getErrorMsg());
        }
    }

}

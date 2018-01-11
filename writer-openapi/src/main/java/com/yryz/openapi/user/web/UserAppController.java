package com.yryz.openapi.user.web;

import com.alibaba.fastjson.JSON;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.internal.DubboResponse;
import com.yryz.service.api.basic.constants.SmsContants;
import com.yryz.service.api.basic.entity.SmsReqVo;
import com.yryz.service.api.basic.entity.SmsVerifyCode;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.BaseException;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.utils.ImageUtils;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
        //Boolean b = true;
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

            RpcResponse<Long> rpcResponseRegister = writerApi.register(user);
            Long kid = isSuccess(rpcResponseRegister);

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
            authInfoVo.setUserId(kid);

            RpcResponse<String> tokenRpcResponse = writerApi.addUserToken(String.valueOf(kid));
            String token = isSuccess(tokenRpcResponse);
            authInfoVo.setToken(token);
            return new DubboResponse<AuthInfoVo>(true, "200", "success", "", authInfoVo);
        }

        //您输入的验证码不正确
        throw new YyrzPcException(
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
    public RpcResponse<Boolean> judgePwd(@RequestHeader("userId") String userId) {
        Assert.notNull(userId, "userId不能为空！");
        Assert.hasText(userId, "userId不能为空！");

        RpcResponse<Writer> rpcResponseWriter = writerApi.get(Long.valueOf(userId));
        Writer user = isSuccess(rpcResponseWriter);
        if (user != null) {
            if (user.getPwd() != null && user.getPwd() != null) {
                return new DubboResponse<Boolean>(true, "200", "success", "", false);
            } else {
                return new DubboResponse<Boolean>(true, "200", "success", "", true);
            }
        }
        throw new YyrzPcException(
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
    public RpcResponse<AuthInfoVo> setPwd(@RequestBody CustRegisterDto custRegisterDto,@RequestHeader("userId") String userId) {
        Assert.notNull(custRegisterDto, "缺少参数或参数错误！");

        Assert.notNull(custRegisterDto.getCustPwd(), "密码不能为空！");
        Assert.hasText(custRegisterDto.getCustPwd(), "密码不能为空！");

        Assert.notNull(userId, "userId不能为空！");
        Assert.hasText(userId, "userId不能为空！");

        RpcResponse<Writer> rpcResponseWriter = writerApi.get(Long.valueOf(userId));
        Writer user = isSuccess(rpcResponseWriter);

        if (user == null) {
            throw new YyrzPcException(
                    ExceptionEnum.PHONE_UNBIND.getCode(),
                    ExceptionEnum.PHONE_UNBIND.getMsg(),
                    ExceptionEnum.PHONE_UNBIND.getErrorMsg());
        }
        if (StringUtils.isNotBlank(user.getPwd())) {
            throw new YyrzPcException(
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
     * 验证码登录
     *
     * @param custRegisterDto
     * @param devId
     * @return
     */
    @RequestMapping(value = "login/verifyCode", method = {RequestMethod.POST})
    @ResponseBody
    @NotLogin
    public RpcResponse<AuthInfoVo> loginVerifyCode(@RequestBody CustRegisterDto custRegisterDto) {
        AuthInfoVo authInfoVo = new AuthInfoVo();
        Assert.notNull(custRegisterDto, "缺少参数或参数错误！");

        Assert.notNull(custRegisterDto.getCustPhone(), "手机号不能为空！");
        Assert.notNull(custRegisterDto.getCode(), "功能码不能为空！");
        Assert.notNull(custRegisterDto.getVeriCode(), "验证码不能为空！");
        Assert.hasText(custRegisterDto.getImageCode(), "图形验证码不能为空！");

        Assert.hasText(custRegisterDto.getCustPhone(), "手机号不能为空！");
        Assert.hasText(custRegisterDto.getCode(), "功能码不能为空！");
        Assert.hasText(custRegisterDto.getVeriCode(), "验证码不能为空！");

        Assert.hasText(custRegisterDto.getImageCode(), "图形验证码不能为空！");

        //第一步验证图形验证码
        RpcResponse<Boolean> iamgeRpcResponse = writerApi.checkImageCode(custRegisterDto.getCustPhone(),custRegisterDto.getImageCode());
        boolean iamgeFlag = isSuccess(iamgeRpcResponse);
        if(!iamgeFlag){
            throw new YyrzPcException(
                    ExceptionEnum.IMAGE_CODE_ERROR.getCode(),
                    ExceptionEnum.IMAGE_CODE_ERROR.getMsg(),
                    ExceptionEnum.IMAGE_CODE_ERROR.getErrorMsg());
        }

        //校验验证码
        RpcResponse<Boolean> rpcResponse = smsCommonApi.checkVerifyCode(custRegisterDto.getCustPhone(), custRegisterDto.getCode(), custRegisterDto.getVeriCode());
        Boolean aBoolean = isSuccessNotNull(rpcResponse);
        if (aBoolean){

            //查询用户
            RpcResponse<Writer> userRpcResponse = writerApi.selectByPhone(custRegisterDto.getCustPhone());
            Writer user = isSuccess(userRpcResponse);
            if (user == null) {
                throw new YyrzPcException(
                        ExceptionEnum.USER_UNREGISTERED.getCode(),
                        ExceptionEnum.USER_UNREGISTERED.getMsg(),
                        ExceptionEnum.USER_UNREGISTERED.getErrorMsg());
            }

            RpcResponse<String> tokenRpcResponse = writerApi.addUserToken(String.valueOf(user.getKid()));
            String token = isSuccess(tokenRpcResponse);
            authInfoVo.setCustPhone(custRegisterDto.getCustPhone());
            authInfoVo.setToken(token);
            authInfoVo.setUserId(user.getKid());
            authInfoVo.setCustImg(user.getHeadImg());
            authInfoVo.setCustNname(user.getNickName());
            return new DubboResponse<AuthInfoVo>(true, "200", "success", "", authInfoVo);
        }
        throw new YyrzPcException(
                ExceptionEnum.PIN_ERROR.getCode(),
                ExceptionEnum.PIN_ERROR.getMsg(),
                ExceptionEnum.PIN_ERROR.getErrorMsg());
    }

    /**
     * 获取图形验证码图
     * @param phone
     * @param response
     */
    @RequestMapping(value = "image", method = {RequestMethod.GET})
    @NotLogin
    public void image(String phone,HttpServletResponse response){

        Assert.notNull(phone, "手机号不能为空！");
        Assert.hasText(phone, "手机号不能为空！");

        //设置服务器到客户端的响应内容类型-〉mime图片格式
        response.setContentType("image/jpeg");

        //设置客户端浏览器显示图像不缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("expires", 0);
        try {
            //查询用户
            RpcResponse<String> userRpcResponse = writerApi.getImageCode(phone);
            String code = isSuccess(userRpcResponse);
            ImageUtils.getSmsImgByCode(code, response);
        }  catch (BaseException e) {
            return;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return;
        } finally {
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        Assert.notNull(map.get("imageCode"), "图形验证码不能为空！");

        String  phone = (String) map.get("phone");
        String  password = (String) map.get("password");
        String  imageCode = (String) map.get("imageCode");
        Assert.hasText(phone, "手机号不能为空！");
        Assert.hasText(password, "密码不能为空！");
        Assert.hasText(imageCode, "图形验证码不能为空！");

        //第一步验证图形验证码
        RpcResponse<Boolean> iamgeRpcResponse = writerApi.checkImageCode(phone,imageCode);
        boolean iamgeFlag = isSuccess(iamgeRpcResponse);
        if(!iamgeFlag){
            throw new YyrzPcException(
                    ExceptionEnum.IMAGE_CODE_ERROR.getCode(),
                    ExceptionEnum.IMAGE_CODE_ERROR.getMsg(),
                    ExceptionEnum.IMAGE_CODE_ERROR.getErrorMsg());
        }

        //查询用户
        RpcResponse<Writer> userRpcResponse = writerApi.selectByPhone(phone);
        Writer user = isSuccess(userRpcResponse);
        if (user == null) {
            throw new YyrzPcException(
                    ExceptionEnum.USER_UNREGISTERED.getCode(),
                    ExceptionEnum.USER_UNREGISTERED.getMsg(),
                    ExceptionEnum.USER_UNREGISTERED.getErrorMsg());
        } else {
            if (password.equals(user.getPwd())) {
                RpcResponse<String> tokenRpcResponse = writerApi.addUserToken(String.valueOf(user.getKid()));
                String token = isSuccess(tokenRpcResponse);
                authInfoVo.setToken(token);
            } else {
                throw new YyrzPcException(
                        ExceptionEnum.PASSWORD_ERROR.getCode(),
                        ExceptionEnum.PASSWORD_ERROR.getMsg(),
                        ExceptionEnum.PASSWORD_ERROR.getErrorMsg());
            }
        }

        authInfoVo.setUserId(user.getKid());
        authInfoVo.setCustPhone(phone);
        authInfoVo.setCustImg(user.getHeadImg());
        authInfoVo.setCustNname(user.getNickName());
        return new DubboResponse<AuthInfoVo>(true, "200", "success", "", authInfoVo);
    }


    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "pwd", method = {RequestMethod.POST})
    @ResponseBody
    public RpcResponse<AuthInfoVo> updatePwd(@RequestBody Map<String, Object> map,@RequestHeader("userId") String userId) {
        Assert.notNull(map, "缺少参数或参数错误");

        Assert.notNull(map.get("password"), "旧密码不能为空！");
        Assert.notNull(map.get("newPassword"), "新密码不能为空！");

        String  password = (String) map.get("password");
        String  newPassword = (String) map.get("newPassword");
        Assert.hasText(password, "旧密码不能为空！");
        Assert.hasText(newPassword, "新密码不能为空！");

        Assert.notNull(userId, "userId不能为空！");
        Assert.hasText(userId, "userId不能为空！");



        RpcResponse<Writer> rpcResponseWriter = writerApi.get(Long.valueOf(userId));
        Writer user = isSuccess(rpcResponseWriter);

        if (user != null) {
            if (password.equals(user.getPwd())) {
                //修改用户信息
                Writer writer = new Writer();
                writer.setKid(user.getKid());
                writer.setPwd(newPassword);
                writerApi.updateByPrimaryKeySelective(writer);
                return new DubboResponse<AuthInfoVo>(true, "200", "success", "", new AuthInfoVo());
            } else {
                throw new YyrzPcException(
                        ExceptionEnum.OLD_PASSWORD_ERROR.getCode(),
                        ExceptionEnum.OLD_PASSWORD_ERROR.getMsg(),
                        ExceptionEnum.OLD_PASSWORD_ERROR.getErrorMsg());
            }
        }
        throw new YyrzPcException(
                ExceptionEnum.CAN_NOT_ALERT_PASSWORD.getCode(),
                ExceptionEnum.CAN_NOT_ALERT_PASSWORD.getMsg(),
                ExceptionEnum.CAN_NOT_ALERT_PASSWORD.getErrorMsg());
    }

    /**
     * 重置密码
     *
     * @param custRegisterDto
     * @return
     */
    @RequestMapping(value = "resetPwd", method = {RequestMethod.POST})
    @ResponseBody
    @NotLogin
    public RpcResponse<AuthInfoVo> resetPwd(@RequestBody CustRegisterDto custRegisterDto) {
        AuthInfoVo authInfoVo = new AuthInfoVo();
        Assert.notNull(custRegisterDto, "缺少参数或参数错误！");
        Assert.notNull(custRegisterDto.getCustPhone(), "手机号不能为空！");
        Assert.hasText(custRegisterDto.getCustPhone(), "手机号不能为空！");

        Assert.notNull(custRegisterDto.getCustPwd(), "密码不能为空！");
        Assert.hasText(custRegisterDto.getCustPwd(), "密码不能为空！");


        Assert.notNull(custRegisterDto.getVeriCode(), "验证码不能为空！");
        Assert.hasText(custRegisterDto.getVeriCode(), "验证码不能为空！");


        RpcResponse<Boolean> rpcResponse = smsCommonApi.checkVerifyCode(custRegisterDto.getCustPhone(), SmsTypeEnum.CODE_FIND_PWD, custRegisterDto.getVeriCode());
        boolean b = isSuccessNotNull(rpcResponse);
        if (b) {
            RpcResponse<Writer> userRpcResponse = writerApi.selectByPhone(custRegisterDto.getCustPhone());
            Writer user = isSuccess(userRpcResponse);
            if (user != null) {
                //修改用户信息
                Writer writer = new Writer();
                writer.setKid(user.getKid());
                writer.setPwd(custRegisterDto.getCustPwd());
                writerApi.updateByPrimaryKeySelective(writer);

                //自动登录，获取token
                RpcResponse<String> tokenRpcResponse = writerApi.getUserToken(String.valueOf(user.getKid()));
                String token = isSuccess(tokenRpcResponse);
                authInfoVo.setToken(token);
                return new DubboResponse<AuthInfoVo>(true, "200", "success", "", authInfoVo);
            } else {
                throw new YyrzPcException(
                        ExceptionEnum.USER_UNREGISTERED.getCode(),
                        ExceptionEnum.USER_UNREGISTERED.getMsg(),
                        ExceptionEnum.USER_UNREGISTERED.getErrorMsg());
            }
        }
        throw new YyrzPcException(
                ExceptionEnum.PIN_ERROR.getCode(),
                ExceptionEnum.PIN_ERROR.getMsg(),
                ExceptionEnum.PIN_ERROR.getErrorMsg());
    }



    /**
     * 解绑旧手机号验证
     *
     * @param custRegisterDto
     * @return
     */
    @RequestMapping(value = "unbindPhoneAccount", method = {RequestMethod.POST})
    @ResponseBody
    public RpcResponse<Map<String,Object>> unbindPhoneAccount(@RequestBody CustRegisterDto custRegisterDto,@RequestHeader("userId") String userId) {
        Assert.notNull(custRegisterDto, "参数错误！");
        Assert.notNull(custRegisterDto.getVeriCode(), "验证码不能为空！");
        Assert.hasText(custRegisterDto.getVeriCode(), "验证码不能为空！");

        RpcResponse<Writer> rpcResponseWriter = writerApi.get(Long.valueOf(userId));
        Writer user = isSuccess(rpcResponseWriter);
        if (user == null) {
            throw new YyrzPcException(
                    ExceptionEnum.USER_UNREGISTERED.getCode(),
                    ExceptionEnum.USER_UNREGISTERED.getMsg(),
                    ExceptionEnum.USER_UNREGISTERED.getErrorMsg());
        }

        RpcResponse<Boolean> booleanRpcResponse = smsCommonApi.checkVerifyCode(user.getPhone(), SmsTypeEnum.CODE_UNKNOW, custRegisterDto.getVeriCode());
        boolean b = isSuccessNotNull(booleanRpcResponse);
        if (b) {
            Map<String,Object> mapResult = new HashMap <String,Object>();
            mapResult.put("custPhone",user.getPhone());
            return new DubboResponse<Map<String,Object>>(true, "200", "success", "", mapResult);
        } else {
            throw new YyrzPcException(
                    ExceptionEnum.PIN_ERROR.getCode(),
                    ExceptionEnum.PIN_ERROR.getMsg(),
                    ExceptionEnum.PIN_ERROR.getErrorMsg());
        }
    }


    /**
     * 绑定手机号
     *
     * @param custRegisterDto
     * @return
     */
    @RequestMapping(value = "bindPhoneAccount", method = {RequestMethod.POST})
    @ResponseBody
    public RpcResponse<AuthInfoVo> bindPhoneAccount(@RequestBody CustRegisterDto custRegisterDto,@RequestHeader("userId") String userId) {
        Assert.notNull(custRegisterDto, "参数错误！");

        Assert.notNull(custRegisterDto.getCustPhone(), "手机号不能为空！");
        Assert.hasText(custRegisterDto.getCustPhone(), "手机号不能为空！");

        Assert.notNull(custRegisterDto.getVeriCode(), "验证码不能为空！");
        Assert.hasText(custRegisterDto.getVeriCode(), "验证码不能为空！");


        RpcResponse<Boolean> booleanRpcResponse = smsCommonApi.checkVerifyCode(custRegisterDto.getCustPhone(), SmsTypeEnum.CODE_CHANGE_PHONE, custRegisterDto.getVeriCode());
        boolean b = isSuccessNotNull(booleanRpcResponse);
        if (b) {
            RpcResponse<Writer> rpcResponseWriter = writerApi.selectByPhone(custRegisterDto.getCustPhone());
            Writer user = isSuccess(rpcResponseWriter);
            if (user == null) {
                //可以绑定
                RpcResponse<Writer> rpcResponseWriterSave = writerApi.get(Long.valueOf(userId));
                Writer writer = isSuccess(rpcResponseWriterSave);
                writer.setPhone(custRegisterDto.getCustPhone());
                writer.setAccount(custRegisterDto.getCustPhone());
                writerApi.updateByPrimaryKeySelective(writer);
                Map<String,Object> mapResult = new HashMap <String,Object>();
                mapResult.put("custPhone",custRegisterDto.getCustPhone());
                return new DubboResponse<Map<String,Object>>(true, "200", "success", "", mapResult);
            }else{
                throw new YyrzPcException(
                        ExceptionEnum.BIND_UNSUCCESSFUL.getCode(),
                        ExceptionEnum.BIND_UNSUCCESSFUL.getMsg(),
                        ExceptionEnum.BIND_UNSUCCESSFUL.getErrorMsg());
            }
        } else {
            throw new YyrzPcException(
                    ExceptionEnum.PIN_ERROR.getCode(),
                    ExceptionEnum.PIN_ERROR.getMsg(),
                    ExceptionEnum.PIN_ERROR.getErrorMsg());
        }
    }


    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "logOut", method = {RequestMethod.POST})
    @ResponseBody
    public RpcResponse<AuthInfoVo> logOut(@RequestHeader String userId) {
        Assert.notNull(userId, "userId不能为空！");
        Assert.hasText(userId, "userId不能为空！");
        RpcResponse<Integer> rpcResponse = writerApi.deleteUserToken(userId);
        return new DubboResponse<AuthInfoVo>(true, "200", "success", "", new AuthInfoVo());
    }



    /**
     * 该手机号未注册
     * @param user
     */
    private void phoneUnregistered(Writer user) {
        if (user == null) {
            throw new YyrzPcException(
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
            throw new YyrzPcException(
                    ExceptionEnum.PHONE_REGISTERED.getCode(),
                    ExceptionEnum.PHONE_REGISTERED.getMsg(),
                    ExceptionEnum.PHONE_REGISTERED.getErrorMsg());
        }
    }

}

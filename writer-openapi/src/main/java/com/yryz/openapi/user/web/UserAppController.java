package com.yryz.openapi.user.web;

import com.alibaba.fastjson.JSON;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.internal.DubboResponse;
import com.yryz.openapi.core.auth.annotation.InterFaceAuth;
import com.yryz.service.api.api.exception.ServiceException;
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
import com.yryz.writer.modules.profit.ProfitApi;
import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.WriterAuditApi;
import com.yryz.writer.modules.writer.WriterStatisticsApi;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.entity.WriterStatistics;
import com.yryz.writer.modules.writer.vo.WriterUserVo;
import com.yryz.writer.modules.writer.vo.WriterVo;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @InterFaceAuth
    public RpcResponse<SmsVerifyCode> getCode(@RequestBody SmsReqVo smsReq, @RequestHeader String originText, @RequestHeader String sign) {
        Assert.notNull(smsReq.getPhone(), "手机号不能为空！");
        Assert.notNull(smsReq.getCode(), "功能码不能为空！");

        Assert.hasText(smsReq.getPhone(), "手机号不能为空！");
        Assert.hasText(smsReq.getCode(), "功能码不能为空！");

        //验证手机号格式
        checkPhone(smsReq.getPhone());

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
    public RpcResponse<WriterUserVo> register(@RequestBody CustRegisterDto custRegister) {
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
            user.setUserStatus(2);
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
            WriterUserVo authInfoVo = new WriterUserVo();
            authInfoVo.setPhone(custRegister.getCustPhone());
            authInfoVo.setUserId(String.valueOf(kid));


            RpcResponse<String> tokenRpcResponse = writerApi.addUserToken(String.valueOf(kid));
            String token = isSuccess(tokenRpcResponse);
            authInfoVo.setToken(token);
            return new DubboResponse<WriterUserVo>(true, "200", "success", "", authInfoVo);
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
    public RpcResponse<WriterUserVo> loginVerifyCode(@RequestBody CustRegisterDto custRegisterDto) {
        WriterUserVo authInfoVo = new WriterUserVo();
        Assert.notNull(custRegisterDto, "缺少参数或参数错误！");

        Assert.notNull(custRegisterDto.getCustPhone(), "手机号不能为空！");
        Assert.notNull(custRegisterDto.getCode(), "功能码不能为空！");
        Assert.notNull(custRegisterDto.getVeriCode(), "验证码不能为空！");
        Assert.hasText(custRegisterDto.getImageCode(), "图形验证码不能为空！");

        Assert.hasText(custRegisterDto.getCustPhone(), "手机号不能为空！");
        Assert.hasText(custRegisterDto.getCode(), "功能码不能为空！");
        Assert.hasText(custRegisterDto.getVeriCode(), "验证码不能为空！");

        Assert.hasText(custRegisterDto.getImageCode(), "图形验证码不能为空！");

        Assert.notNull(custRegisterDto.getKey(), "唯一标识不能为空！");
        Assert.hasText(custRegisterDto.getKey(), "唯一标识不能为空！");

        //第一步验证图形验证码
        RpcResponse<Boolean> iamgeRpcResponse = writerApi.checkImageCode(custRegisterDto.getKey(),custRegisterDto.getImageCode());
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
            authInfoVo.setPhone(custRegisterDto.getCustPhone());
            authInfoVo.setToken(token);
            authInfoVo.setUserId(String.valueOf(user.getKid()));
            authInfoVo.setHeadImg(user.getHeadImg());
            authInfoVo.setNickName(user.getNickName());

            return new DubboResponse<WriterUserVo>(true, "200", "success", "", authInfoVo);
        }
        throw new YyrzPcException(
                ExceptionEnum.PIN_ERROR.getCode(),
                ExceptionEnum.PIN_ERROR.getMsg(),
                ExceptionEnum.PIN_ERROR.getErrorMsg());
    }

    /**
     * 获取图形验证码图
     * @param key
     * @param response
     */
    @RequestMapping(value = "image", method = {RequestMethod.GET})
    @NotLogin
    public void image(String key,HttpServletResponse response){

        Assert.notNull(key, "唯一标识不能为空！");
        Assert.hasText(key, "唯一标识不能为空！");

        //设置服务器到客户端的响应内容类型-〉mime图片格式
        response.setContentType("image/jpeg");

        //设置客户端浏览器显示图像不缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("expires", 0);
        try {
            //查询用户
            RpcResponse<String> userRpcResponse = writerApi.getImageCode(key);
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
    public RpcResponse<WriterUserVo> login(@RequestBody Map<String, Object> map) {
        LOGGER.info("=======login user1: {}", JSON.toJSONString(map));
        WriterUserVo authInfoVo = new WriterUserVo();
        Assert.notNull(map, "用户信息为空！");

        Assert.notNull(map.get("phone"), "手机号不能为空！");
        Assert.notNull(map.get("password"), "密码不能为空！");
        Assert.notNull(map.get("imageCode"), "图形验证码不能为空！");
        Assert.notNull(map.get("key"), "唯一标识不能为空！");

        String  phone = (String) map.get("phone");
        String  password = (String) map.get("password");
        String  imageCode = (String) map.get("imageCode");
        String  key = (String) map.get("key");

        Assert.hasText(phone, "手机号不能为空！");
        Assert.hasText(password, "密码不能为空！");
        Assert.hasText(imageCode, "图形验证码不能为空！");
        Assert.hasText(key, "唯一标识不能为空！");

        //第一步验证图形验证码
        RpcResponse<Boolean> iamgeRpcResponse = writerApi.checkImageCode(key,imageCode);
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
            if(null == user.getPwd() || user.getPwd().length() == 0){
                throw new YyrzPcException(
                        ExceptionEnum.UN_SET_PASSWORD_.getCode(),
                        ExceptionEnum.UN_SET_PASSWORD_.getMsg(),
                        ExceptionEnum.UN_SET_PASSWORD_.getErrorMsg());
            }

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

        authInfoVo.setPhone(phone);
        authInfoVo.setUserId(String.valueOf(user.getKid()));
        authInfoVo.setHeadImg(user.getHeadImg());
        authInfoVo.setNickName(user.getNickName());
        return new DubboResponse<WriterUserVo>(true, "200", "success", "", authInfoVo);
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
                //新密码和旧密码相同，则提示
                if(password.equals(newPassword)){
                    throw new YyrzPcException(
                            ExceptionEnum.ACCOUNT_PASSWORD_EQUAL_NEW_PASSWORD.getCode(),
                            ExceptionEnum.ACCOUNT_PASSWORD_EQUAL_NEW_PASSWORD.getMsg(),
                            ExceptionEnum.ACCOUNT_PASSWORD_EQUAL_NEW_PASSWORD.getErrorMsg());
                }

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
            mapResult.put("veriCode",custRegisterDto.getVeriCode());

            //将旧手机号的验证码保存到redis
            writerApi.addUserPhoneVeriCode(userId,custRegisterDto.getVeriCode());

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

        Assert.notNull(custRegisterDto.getVeriCode(), "新手机验证码不能为空！");
        Assert.hasText(custRegisterDto.getVeriCode(), "新手机验证码不能为空！");

        Assert.notNull(custRegisterDto.getOldVeriCode(), "旧手机验证码不能为空！");
        Assert.hasText(custRegisterDto.getOldVeriCode(), "旧手机验证码不能为空！");

        RpcResponse<Writer> rpcResponseRegister = writerApi.get(Long.valueOf(userId));
        Writer userRegister = isSuccess(rpcResponseRegister);
        if (userRegister == null) {
            throw new YyrzPcException(
                    ExceptionEnum.USER_UNREGISTERED.getCode(),
                    ExceptionEnum.USER_UNREGISTERED.getMsg(),
                    ExceptionEnum.USER_UNREGISTERED.getErrorMsg());
        }

        //验证旧手机号是否正确
        RpcResponse<String> rpcResponseWriterRedis  = writerApi.getUserPhoneVeriCode(userId);
        String oldVericodeRedis =  isSuccess(rpcResponseWriterRedis);
        if(oldVericodeRedis!=null && !oldVericodeRedis.equals(custRegisterDto.getOldVeriCode())){
            throw new YyrzPcException(
                    ExceptionEnum.OLD_PHONE_VERICODE_ERROR.getCode(),
                    ExceptionEnum.OLD_PHONE_VERICODE_ERROR.getMsg(),
                    ExceptionEnum.OLD_PHONE_VERICODE_ERROR.getErrorMsg());
        }


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
                        ExceptionEnum.BIND_PHONE_UNSUCCESSFUL.getCode(),
                        ExceptionEnum.BIND_PHONE_UNSUCCESSFUL.getMsg(),
                        ExceptionEnum.BIND_PHONE_UNSUCCESSFUL.getErrorMsg());
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


    /**
     * 判断手机号是否合法
     * @param user
     */
    private void checkPhone(String phone) {
        //三大运营商手机号匹配正则表达式
        String PHONE_REGEX = "^(13[0-9]|15[012356789]|16[6]|17[0135678]|18[0-9]|14[56789]|19[89])[0-9]{8}$";

        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.matches()){
            throw new YyrzPcException(
                    ExceptionEnum.CELL_PHONE_NUMBER_ILLEGAL.getCode(),
                    ExceptionEnum.CELL_PHONE_NUMBER_ILLEGAL.getMsg(),
                    ExceptionEnum.CELL_PHONE_NUMBER_ILLEGAL.getErrorMsg());
        }

    }



}

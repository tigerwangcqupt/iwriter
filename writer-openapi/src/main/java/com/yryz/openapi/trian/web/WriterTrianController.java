package com.yryz.openapi.trian.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.redis.utils.StringUtils;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.platform.SmsCommonApi;
import com.yryz.writer.modules.platform.constants.SmsTypeEnum;
import com.yryz.writer.modules.trian.api.WriterTrianApi;
import com.yryz.writer.modules.trian.dto.WriterTrianDto;
import com.yryz.writer.modules.trian.entity.WriterTrian;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liupan
 * @Path: com.yryz.openapi.trian
 * @Desc:
 * @Date: 2018/5/29.
 */
@Controller
@RequestMapping("services/app/v1/writertrian")
public class WriterTrianController extends BaseController {

    @Autowired
    private WriterTrianApi writerTrianApi;

    @Autowired
    private SmsCommonApi smsCommonApi;

    @NotLogin
    @ResponseBody
    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public RpcResponse<Integer> single(@RequestBody WriterTrianDto writerTrianDto) {
        try {
            //校验入参
            if (StringUtils.isEmpty(writerTrianDto.getVerifyCode()) ||
                    StringUtils.isEmpty(writerTrianDto.getNickName()) ||
                    StringUtils.isEmpty(writerTrianDto.getPhone()) ||
                    StringUtils.isEmpty(writerTrianDto.getSpecialtyArticle()) ||
                    writerTrianDto.getTrainMode() == null ||
                    writerTrianDto.getTrainTime() == null ||
                    StringUtils.isEmpty(writerTrianDto.getTrainContent()) ||
                    StringUtils.isEmpty(writerTrianDto.getDraftPlan())) {
                return ResponseModel.returnException(new YyrzPcException(
                        ExceptionEnum.ValidateException.getCode(),
                        ExceptionEnum.ValidateException.getMsg(),
                        ExceptionEnum.ValidateException.getErrorMsg()));
            }
            //校验验证码
            RpcResponse<Boolean> smsRes = smsCommonApi.checkVerifyCode(writerTrianDto.getPhone(), SmsTypeEnum.CODE_UNKNOW, writerTrianDto.getVerifyCode());
            if (smsRes.getData() != null && smsRes.getData()) {
                WriterTrian writerTrian = new WriterTrian();
                BeanUtils.copyProperties(writerTrianDto, writerTrian);
                writerTrian.setCreateUserId(request.getHeader("userId"));
                return writerTrianApi.insert(writerTrian);
            } else {
                return ResponseModel.returnException(new YyrzPcException(
                        ExceptionEnum.PIN_ERROR.getCode(),
                        ExceptionEnum.PIN_ERROR.getMsg(),
                        ExceptionEnum.PIN_ERROR.getErrorMsg()));
            }
        } catch (Exception e) {
            return ResponseModel.returnException(e);
        }
    }

    /**
     * 检查手机号是否已报名
     *
     * @param phone
     * @return
     */
    @NotLogin
    @ResponseBody
    @RequestMapping(value = "/checkPhone", method = RequestMethod.GET)
    public RpcResponse<Map<String, Boolean>> checkPhone(String phone) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("flag", true);
        if (StringUtils.isNotEmpty(phone)) {
            WriterTrianDto writerTrianDto = new WriterTrianDto();
            writerTrianDto.setPhone(phone);
            RpcResponse<Integer> response = writerTrianApi.getCount(writerTrianDto);
            if (response.getData() != null && response.getData() == 0) {
                map.put("flag", false);
            }
        }
        return ResponseModel.returnObjectSuccess(map);
    }
}

package com.yryz.openapi.bank.web;

import com.yryz.openapi.core.auth.annotation.InterFaceAuth;
import com.yryz.openapi.core.validator.annotation.Validate;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.constant.YyrzModuleEnumConstants;
import com.yryz.writer.common.web.BaseController;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.bank.BankApi;
import com.yryz.writer.modules.bank.dto.BankDto;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.bank.vo.BankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:sun
 * @version:
 * @Description:
 * @Date:Created in 18:02 2017/11/24
 */
@Controller
@RequestMapping("services/app/v1/bank")
public class BankController extends BaseController {

    @Autowired
    private BankApi bankApi;


    @RequestMapping(value="/detail", method = RequestMethod.GET)
    @ResponseBody
    @NotLogin
    public RpcResponse<BankVo> detail(@RequestHeader String userId){
        BankDto bankDto = new BankDto();
        bankDto.setCreateUserId(userId);
        return bankApi.selectByParameters(bankDto);
    }

    @Validate
    @NotLogin
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public RpcResponse<Bank> saveBank(@RequestBody Bank bank, @RequestHeader String userId,@RequestHeader String sign,@RequestHeader String originText){
        bank.setCreateUserId(userId);
        bank.setModuleEnum(YyrzModuleEnumConstants.BANK_INFO);
        return bankApi.insertBank(bank);
    }

    @NotLogin
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public RpcResponse<Bank> updateBank(@RequestBody Bank bank, @RequestHeader String userId){
        bank.setCreateUserId(userId);
        return bankApi.updateBank(bank);
    }

}

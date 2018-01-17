package com.yryz.openapi.bank.web;

import com.yryz.openapi.core.auth.annotation.InterFaceAuth;
import com.yryz.openapi.core.validator.annotation.Validate;
import com.yryz.qstone.core.utils.BankUtils;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.constant.YyrzModuleEnumConstants;
import com.yryz.writer.common.web.BaseController;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.bank.BankApi;
import com.yryz.writer.modules.bank.dto.BankDto;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.bank.vo.BankNameVo;
import com.yryz.writer.modules.bank.vo.BankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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
    public RpcResponse<BankVo> detail(@RequestHeader String userId){
        Assert.notNull(userId, "用户id为空!");
        BankDto bankDto = new BankDto();
        bankDto.setCreateUserId(userId);
        return bankApi.selectByParameters(bankDto);
    }

    @Validate
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public RpcResponse<Bank> saveBank(@RequestBody Bank bank, @RequestHeader String userId){
        Assert.notNull(bank, "参数缺少或错误！");
        Assert.notNull(userId, "用户id为空!");
        bank.setCreateUserId(userId);
        bank.setModuleEnum(YyrzModuleEnumConstants.BANK_INFO);
        return bankApi.insertBank(bank);
    }


    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public RpcResponse<Bank> updateBank(@RequestBody Bank bank, @RequestHeader String userId){
        Assert.notNull(bank, "参数缺少或错误！");
        Assert.notNull(userId, "用户id为空!");
        Assert.notNull(bank.getBankcardFcode(), "银行卡外码为空!");
        bank.setLastUpdateUserId(userId);
        bank.setCreateUserId(userId);
        return bankApi.updateBank(bank);
    }


    @RequestMapping(value="/getBankName", method = RequestMethod.GET)
    @ResponseBody
    public RpcResponse<BankNameVo> getBankName(String bankCard,@RequestHeader String userId){
        Assert.notNull(userId, "用户id为空!");
        Assert.notNull(bankCard, "银行卡为空!");
        return bankApi.selectBankNameByCard(bankCard);
    }

}

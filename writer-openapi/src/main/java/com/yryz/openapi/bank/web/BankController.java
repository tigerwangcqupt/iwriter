package com.yryz.openapi.bank.web;

import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.web.BaseController;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.bank.BankApi;
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
    public RpcResponse<BankVo> detail(Long id){
        return bankApi.detail(id);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public RpcResponse<Bank> saveBank(@RequestBody Bank bank, @RequestHeader String userId){
        bank.setCreateUserId(userId);
        return bankApi.insertBank(bank);
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public RpcResponse<Bank> updateBank(@RequestBody Bank bank, @RequestHeader String userId){
        bank.setCreateUserId(userId);
        return bankApi.updateBank(bank);
    }

}

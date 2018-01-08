package com.yryz.openapi.profit.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.profit.vo.ProfitVo;
import com.yryz.writer.modules.profit.dto.ProfitDto;
import com.yryz.writer.modules.profit.entity.Profit;
import com.yryz.writer.modules.profit.ProfitApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("services/app/v1/profit")
public class ProfitController {
   @Autowired
   private ProfitApi profitApi;

   @ResponseBody
   @RequestMapping(value="/getcash/detail", method = RequestMethod.GET)
   public RpcResponse<ProfitVo> detail(@RequestHeader Long userId) {
       return profitApi.detailProfit(userId);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<PageList<ProfitVo>> list(ProfitDto profitDto) {
        return profitApi.list(profitDto);
   }


   @RequestMapping(value="/add", method = RequestMethod.POST)
   @ResponseBody
   public RpcResponse<Profit> saveBank(@RequestBody Profit profit, @RequestHeader String userId){
      profit.setCreateUserId(userId);
      return profitApi.insertProfit(profit);
   }
}

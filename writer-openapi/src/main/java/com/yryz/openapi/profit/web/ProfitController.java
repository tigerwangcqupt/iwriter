package com.yryz.openapi.profit.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.utils.MoneyUtils;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.profit.vo.ProfitDetailVo;
import com.yryz.writer.modules.profit.vo.ProfitStaticsVo;
import com.yryz.writer.modules.profit.vo.ProfitVo;
import com.yryz.writer.modules.profit.dto.ProfitDto;
import com.yryz.writer.modules.profit.entity.Profit;
import com.yryz.writer.modules.profit.ProfitApi;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("services/app/v1/profit")
public class ProfitController extends BaseController{
   @Autowired
   private ProfitApi profitApi;

   /**
    * 统计信息
    * @param userId
    * @return
    */
   @NotLogin
   @ResponseBody
   @RequestMapping(value="/getcash/statics", method = RequestMethod.GET)
   public RpcResponse<ProfitStaticsVo> statics(@RequestHeader Long userId) {
      return profitApi.staticsProfitVo(userId);
   }

   /**
    * 统计信息列表
    * @param userId
    * @return
    */
   @NotLogin
   @ResponseBody
   @RequestMapping(value="/getcash/staticsList", method = RequestMethod.GET)
   public RpcResponse<PageList<ProfitDetailVo>> staticsList(@RequestHeader Long userId) {
      ProfitDto profitDto = new ProfitDto();
      profitDto.setWriterId(userId);
      RpcResponse<PageList<ProfitDetailVo>> rpcResponse = profitApi.selectFlowList(profitDto);
      if(null != rpcResponse && null != rpcResponse.getData()){
         List<ProfitDetailVo> list = rpcResponse.getData().getEntities();
         if(CollectionUtils.isNotEmpty(list)){
            for(ProfitDetailVo profitDetailVo : list){
               profitDetailVo.setSettlementAmount(MoneyUtils.getMoney(profitDetailVo.getSettlementAmount()));
               profitDetailVo.setSurplusAmount(MoneyUtils.getMoney(profitDetailVo.getSurplusAmount()));
            }
         }
      }
      return profitApi.selectFlowList(profitDto);
   }


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


   /**
    * 提现
    * @param profit
    * @param userId
    * @return
    */
   @RequestMapping(value="/add", method = RequestMethod.POST)
   @ResponseBody
   public RpcResponse<Profit> addProfit(@RequestBody Profit profit, @RequestHeader String userId){
      profit.setCreateUserId(userId);
      return profitApi.insertProfit(profit);
   }
}

package com.yryz.openapi.profit.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.openapi.core.auth.annotation.InterFaceAuth;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.constant.YyrzModuleEnumConstants;
import com.yryz.writer.common.utils.MoneyUtils;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.profit.constant.ProfitConstants;
import com.yryz.writer.modules.profit.constant.ProfitEnum;
import com.yryz.writer.modules.profit.vo.ProfitDetailVo;
import com.yryz.writer.modules.profit.vo.ProfitStaticsVo;
import com.yryz.writer.modules.profit.vo.ProfitVo;
import com.yryz.writer.modules.profit.dto.ProfitDto;
import com.yryz.writer.modules.profit.entity.Profit;
import com.yryz.writer.modules.profit.ProfitApi;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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
   @ResponseBody
   @RequestMapping(value="/getcash/statics", method = RequestMethod.GET)
   public RpcResponse<ProfitStaticsVo> statics(@RequestHeader Long userId) {
      Assert.notNull(userId, "用户id为空!");
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
   public RpcResponse<PageList<ProfitDetailVo>> staticsList(ProfitDto profitDto,@RequestHeader Long userId) {
      Assert.notNull(userId, "用户id为空!");
      Assert.notNull(profitDto, "参数缺少或错误！");
      profitDto.setWriterId(userId);
      profitDto.setFrontCall(ProfitConstants.CALLEDBYFRONT);
      String orderStr = "order by settlement_date1 desc";
      profitDto.setOrderStr(orderStr);
      RpcResponse<PageList<ProfitDetailVo>> rpcResponse = profitApi.selectFlowList(profitDto);
      return rpcResponse;
   }




   /**
    * 提现
    * @param profit
    * @param userId
    * @return
    */
   @RequestMapping(value="/add", method = RequestMethod.POST)
   @ResponseBody
   @InterFaceAuth
   public RpcResponse<Profit> addProfit(@RequestBody Profit profit, @RequestHeader Long userId, @RequestHeader String originText, @RequestHeader String sign){

      Assert.notNull(profit, "参数缺少或错误！");
      Assert.notNull(userId, "用户id为空!");
      profit.setWriterId(userId);
      profit.setModuleEnum(YyrzModuleEnumConstants.PROFIT_INFO);
      profit.setSettlementType(ProfitEnum.WITHDRAWALS_FEE.getCode());
      return profitApi.insertProfit(profit);
   }
}

package com.yryz.writer.modules.profit.service;

import com.yryz.common.service.BaseService;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.modules.profit.dto.ProfitDto;
import com.yryz.writer.modules.profit.entity.Profit;
import com.yryz.writer.modules.profit.vo.ProfitVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: ProfitService
  * @Description: Profit业务访问接口
  * @author zhongying
  * @date 2017-12-29 15:36:15
  *
 */
@Repository
public interface ProfitService extends BaseService {

   PageList<ProfitVo> selectList(ProfitDto profitDto);

   ProfitVo detail(Long profitId);

   ProfitVo detailProfit(Long userId);

}
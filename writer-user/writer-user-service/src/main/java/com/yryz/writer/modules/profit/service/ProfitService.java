package com.yryz.writer.modules.profit.service;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.qstone.entity.base.model.Owner;
import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.profit.dto.ProfitDto;
import com.yryz.writer.modules.profit.entity.Profit;
import com.yryz.writer.modules.profit.vo.ProfitAdminVo;
import com.yryz.writer.modules.profit.vo.ProfitDetailVo;
import com.yryz.writer.modules.profit.vo.ProfitStaticsVo;
import com.yryz.writer.modules.profit.vo.ProfitVo;
import com.yryz.writer.modules.writer.entity.Writer;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

import java.util.List;


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

   Profit insertProfit(Profit profit);

   Profit updateProfit(Profit profit);

   /**
    * 绑定资金主体
    * @param writer
    * @return
    */
   Writer bindCapital(Writer writer);

   /**
    * 查询流水列表
    * @param profitDto
    * @return
    */
   PageList<ProfitDetailVo> selectFlowList(ProfitDto profitDto);

   /**
    * 管理后台提现管理列表
    * @param profitDto
    * @return
    */
   PageList<ProfitAdminVo> selectProfitAdminVoList(ProfitDto profitDto);

   /**
    * 管理后台提现管理(查询所有)
    * @param profitDto
    * @return
    */
   List<ProfitAdminVo> selectAllProfitAdminVoList(ProfitDto profitDto);

   /**
    *  根据用户id获取Profit明细数据
    *  @param  userId
    *  @return
    * */
   ProfitStaticsVo staticsProfitVo(Long userId);

}
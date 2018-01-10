package com.yryz.writer.modules.profit;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.qstone.entity.base.model.Owner;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.profit.vo.ProfitAdminVo;
import com.yryz.writer.modules.profit.vo.ProfitDetailVo;
import com.yryz.writer.modules.profit.vo.ProfitVo;
import com.yryz.writer.modules.profit.dto.ProfitDto;
import com.yryz.writer.modules.profit.entity.Profit;
import com.yryz.writer.modules.writer.entity.Writer;

import java.util.List;

/**
 * 
 * @ClassName: ProfitApi
 * @Description: ProfitApi接口
 * @author zhongying
 * @date 2017-12-29 15:36:15
 *
 */
public interface ProfitApi {

	/**
	*  获取Profit明细
	*  @param  id
	*  @return
	* */
	RpcResponse<Profit> get(Long id);

    /**
    *  获取Profit明细
    *  @param  id
    *  @return
    * */
    RpcResponse<ProfitVo> detail(Long id);

	/**
	 *  根据用户id获取Profit明细
	 *  @param  userId
	 *  @return
	 * */
	RpcResponse<ProfitVo> detailProfit(Long userId);

	/**
	 * 添加提现流水
	 *
	 * @param profit
	 * @return
	 */
	RpcResponse<Profit> insertProfit(Profit profit);

	/**
	 * 修改提现流水
	 *
	 * @param profit
	 * @return
	 */
	RpcResponse<Profit> updateProfit(Profit profit);

    /**
    * 获取Profit列表
    * @param profitDto
    * @return
    * */
    RpcResponse<PageList<ProfitVo>> list(ProfitDto profitDto);

	/**
	 * 审核通过时，绑定资金信息
	 * @param writer
	 * @return
	 */
	RpcResponse<Writer> bindCapital(Writer writer);

	/**
	 * 获取Profit流水列表
	 * @param profitDto
	 * @return
	 * */
	RpcResponse<PageList<ProfitDetailVo>> selectFlowList(ProfitDto profitDto);

	/**
	 * 管理后台提现管理
	 * @param profitDto
	 * @return
	 */
	RpcResponse<PageList<ProfitAdminVo>> selectProfitAdminVoList(ProfitDto profitDto);

	/**
	 * 管理后台提现管理(查询所有)
	 * @param profitDto
	 * @return
	 */
	RpcResponse<List<ProfitAdminVo>> selectAllProfitAdminVoList(ProfitDto profitDto);
}

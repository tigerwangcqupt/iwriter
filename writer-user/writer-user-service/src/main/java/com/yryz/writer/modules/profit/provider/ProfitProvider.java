package com.yryz.writer.modules.profit.provider;
import com.yryz.common.constant.YyrzModuleEnumConstants;
import com.yryz.common.distributed.lock.DistributedLockUtils;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.profit.ProfitApi;
import com.yryz.writer.modules.profit.constant.ProfitEnum;
import com.yryz.writer.modules.profit.entity.Profit;
import com.yryz.writer.modules.profit.vo.ProfitVo;
import com.yryz.writer.modules.profit.dto.ProfitDto;
import com.yryz.writer.modules.profit.service.ProfitService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ProfitProvider implements ProfitApi {

	private static final Logger logger = LoggerFactory.getLogger(ProfitProvider.class);


	@Autowired
	private ProfitService profitService;



	/**
	*  获取Profit明细
	*  @param  profitId
	*  @return
	* */
	public RpcResponse<Profit> get(Long profitId) {
		try {
			return ResponseModel.returnObjectSuccess(profitService.get(Profit.class, profitId));
		} catch (Exception e) {
			logger.error("获取Profit明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取Profit明细
	*  @param  profitId
	*  @return
	* */
	public RpcResponse<ProfitVo> detail(Long profitId) {
		try {
			return ResponseModel.returnObjectSuccess(profitService.detail(profitId));
		} catch (Exception e) {
			logger.error("获取Profit明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

	/**
	 * 获取profit信息
	 * @param  userId
	 * @return
	 */
	@Override
	public RpcResponse<ProfitVo> detailProfit(Long userId) {
		try {
			return ResponseModel.returnObjectSuccess(profitService.detailProfit(userId));
		} catch (Exception e) {
			logger.error("获取Profit明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Profit> insertProfit(Profit profit) {
		try {
			return ResponseModel.returnObjectSuccess(profitService.insertProfit(profit));
		} catch (Exception e) {
			logger.error("新增收益失败", e);
			return ResponseModel.returnException(e);
		}
	}

	/**
    * 获取Profit列表
    * @param profitDto
    * @return
    *
	*/
    public RpcResponse<PageList<ProfitVo>> list(ProfitDto profitDto) {
        try {
			 return ResponseModel.returnListSuccess(profitService.selectList(profitDto));
        } catch (Exception e) {
        	logger.error("获取Profit列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

}

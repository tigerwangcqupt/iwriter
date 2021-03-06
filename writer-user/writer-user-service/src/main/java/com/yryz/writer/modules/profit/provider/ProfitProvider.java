package com.yryz.writer.modules.profit.provider;
import com.yryz.qstone.entity.base.model.Owner;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.profit.ProfitApi;
import com.yryz.writer.modules.profit.entity.Profit;
import com.yryz.writer.modules.profit.vo.*;
import com.yryz.writer.modules.profit.dto.ProfitDto;
import com.yryz.writer.modules.profit.service.ProfitService;

import com.yryz.writer.modules.writer.entity.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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



	@Override
	public RpcResponse<Profit> insertProfit(Profit profit) {
		try {
			return ResponseModel.returnObjectSuccess(profitService.insertProfit(profit));
		} catch (Exception e) {
			logger.error("新增收益失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Profit> updateProfit(Profit profit) {
		try {
			return ResponseModel.returnObjectSuccess(profitService.updateProfit(profit));
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

	@Override
	public RpcResponse<Writer> bindCapital(Writer writer) {
		try {
			return ResponseModel.returnObjectSuccess(profitService.bindCapital(writer));
		} catch (Exception e) {
			logger.error("写手绑定资金主体失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<PageList<ProfitDetailVo>> selectFlowList(ProfitDto profitDto) {
		try {
			return ResponseModel.returnListSuccess(profitService.selectFlowList(profitDto));
		} catch (Exception e) {
			logger.error("获取Profit流水列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<PageList<ProfitAdminVo>> selectProfitAdminVoList(ProfitDto profitDto) {
		try {
			return ResponseModel.returnListSuccess(profitService.selectProfitAdminVoList(profitDto));
		} catch (Exception e) {
			logger.error("获取提现管理列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<List<ProfitAdminVo>> selectAllProfitAdminVoList(ProfitDto profitDto) {
		try {
			return ResponseModel.returnListSuccess(profitService.selectAllProfitAdminVoList(profitDto));
		} catch (Exception e) {
			logger.error("获取提现管理列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<ProfitStaticsVo> staticsProfitVo(Long userId) {
		try {
			return ResponseModel.returnObjectSuccess(profitService.staticsProfitVo(userId));
		} catch (Exception e) {
			logger.error("查询写手统计信息失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<ProfitAccountVo> getAccountInfo() {
		try {
			return ResponseModel.returnObjectSuccess(profitService.getAccountInfo());
		} catch (Exception e) {
			logger.error("查询PC现金账户失败", e);
			return ResponseModel.returnException(e);
		}
	}

}

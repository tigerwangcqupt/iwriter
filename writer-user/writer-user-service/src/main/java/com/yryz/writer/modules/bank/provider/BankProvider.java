package com.yryz.writer.modules.bank.provider;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.bank.BankApi;
import com.yryz.writer.modules.bank.constant.BankUtil;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.bank.service.BankService;
import com.yryz.writer.modules.bank.vo.BankNameVo;
import com.yryz.writer.modules.bank.vo.BankVo;
import com.yryz.writer.modules.bank.dto.BankDto;
import com.yryz.writer.modules.id.api.IdAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankProvider implements BankApi {

	private static final Logger logger = LoggerFactory.getLogger(BankProvider.class);

	@Autowired
	private BankService bankService;


	/**
	*  获取Bank明细
	*  @param  bankId
	*  @return
	* */
	public RpcResponse<Bank> get(Long bankId) {
		try {
			return ResponseModel.returnObjectSuccess(bankService.get(Bank.class, bankId));
		} catch (Exception e) {
			logger.error("获取Bank明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取Bank明细
	*  @param  bankId
	*  @return
	* */
	public RpcResponse<BankVo> detail(Long bankId) {
		try {
			return ResponseModel.returnObjectSuccess(bankService.detail(bankId));
		} catch (Exception e) {
			logger.error("获取Bank明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取Bank列表
    * @param bankDto
    * @return
    *
	*/
    public RpcResponse<PageList<BankVo>> list(BankDto bankDto) {
        try {
			 return ResponseModel.returnListSuccess(bankService.selectList(bankDto));
        } catch (Exception e) {
        	logger.error("获取Bank列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	/**
	 * 添加银行卡
	 * @param bank
	 * @return
	 */
	@Override
	public RpcResponse<Bank> insertBank(Bank bank) {
		try {
			return ResponseModel.returnObjectSuccess(bankService.insertBank(bank));
		} catch (Exception e) {
			logger.error("保存bank失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Bank> updateBank(Bank bank) {
		try {
			return ResponseModel.returnObjectSuccess(bankService.updateBank(bank));
		} catch (Exception e) {
			logger.error("修改bank失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<BankVo> selectByParameters(BankDto bankDto) {
		try {
			return ResponseModel.returnObjectSuccess(bankService.selectByParameters(bankDto));
		} catch (Exception e) {
			logger.error("获取Bank明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<BankNameVo> selectBankNameByCard(String bankCard) {
		try {
			//验证bankCard是否真实
			boolean checkBankCard = BankUtil.matchLuhn(bankCard);
			if(!checkBankCard){
				logger.error("银行卡卡号不存在");
				throw new YyrzPcException(ExceptionEnum.NOT_FOUNTD_BANKCARD_EXCEPTION.getCode(),ExceptionEnum.NOT_FOUNTD_BANKCARD_EXCEPTION.getMsg(),
						ExceptionEnum.NOT_FOUNTD_BANKCARD_EXCEPTION.getErrorMsg());
			}
			BankNameVo bankNameVo = new BankNameVo();
			String bankName = BankUtil.getBankNameByCard(bankCard);
			bankNameVo.setBankCard(bankCard);
			bankNameVo.setBankName(bankName);
			return ResponseModel.returnObjectSuccess(bankNameVo);
		} catch (Exception e) {
			logger.error("根据银行卡获取银行名称失败", e);
			return ResponseModel.returnException(e);
		}
	}

}

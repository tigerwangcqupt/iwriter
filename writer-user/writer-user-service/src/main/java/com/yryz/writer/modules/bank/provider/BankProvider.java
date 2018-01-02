package com.yryz.writer.modules.bank.provider;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.bank.BankApi;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.bank.service.BankService;
import com.yryz.writer.modules.bank.vo.BankVo;
import com.yryz.writer.modules.bank.dto.BankDto;
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

}

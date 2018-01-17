package com.yryz.writer.modules.bank;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.bank.dto.BankDto;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.bank.vo.BankNameVo;
import com.yryz.writer.modules.bank.vo.BankVo;
import com.yryz.writer.modules.writer.entity.Writer;

import java.util.Map;


/**
 * 
 * @ClassName: BankApi
 * @Description: BankApi接口
 * @author zhongying
 * @date 2017-12-29 15:38:16
 *
 */
public interface BankApi {

	/**
	*  获取Bank明细
	*  @param  id
	*  @return
	* */
	RpcResponse<Bank> get(Long id);

    /**
    *  获取Bank明细
    *  @param  id
    *  @return
    * */
    RpcResponse<BankVo> detail(Long id);

    /**
    * 获取Bank列表
    * @param bankDto
    * @return
    * */
    RpcResponse<PageList<BankVo>> list(BankDto bankDto);

	/**
	 * 添加银行卡
	 *
	 * @param bank
	 * @return
	 */
	RpcResponse<Bank> insertBank(Bank bank);

	/**
	 * 添加银行卡
	 *
	 * @param bank
	 * @return
	 */
	RpcResponse<Bank> updateBank(Bank bank);


	/**
	 * 查询银行卡信息
	 * @param bankDto
	 * @return
	 */
	RpcResponse<BankVo> selectByParameters(BankDto bankDto);

	/**
	 * 根据银行卡号查询银行名称
	 * @param bankCard
	 * @return
	 */
	RpcResponse<BankNameVo> selectBankNameByCard(String bankCard);

}

package com.yryz.writer.modules.bank;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.bank.dto.BankDto;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.bank.vo.BankVo;


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

}

package com.yryz.writer.modules.bank.service;


import com.yryz.common.service.BaseService;
import com.yryz.writer.modules.bank.dto.BankDto;
import com.yryz.writer.modules.bank.vo.BankVo;
import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: BankService
  * @Description: Bank业务访问接口
  * @author zhongying
  * @date 2017-12-29 15:38:16
  *
 */
public interface BankService extends BaseService {

   PageList<BankVo> selectList(BankDto bankDto);

   BankVo detail(Long bankId);

}
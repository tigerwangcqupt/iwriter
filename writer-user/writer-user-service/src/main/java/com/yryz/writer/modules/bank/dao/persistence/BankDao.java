package com.yryz.writer.modules.bank.dao.persistence;


import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.bank.dto.BankDto;
import com.yryz.writer.modules.bank.entity.Bank;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: BankDao
  * @Description: Bank数据访问接口
  * @author zhongying
  * @date 2017-12-29 15:38:16
  *
 */
@Repository
public interface BankDao extends BaseDao {

    List<Bank> selectList(BankDto bankDto);

    Bank selectByParameters(BankDto bankDto);

}
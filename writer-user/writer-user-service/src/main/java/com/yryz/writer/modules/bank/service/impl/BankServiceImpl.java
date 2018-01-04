package com.yryz.writer.modules.bank.service.impl;

import com.yryz.common.utils.PageUtils;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.bank.dao.persistence.BankDao;
import com.yryz.writer.modules.bank.service.BankService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.bank.vo.BankVo;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.bank.dto.BankDto;
import java.util.ArrayList;
import java.util.List;


@Service
public class BankServiceImpl extends BaseServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;

    protected BaseDao getDao() {
        return bankDao;
    }

    public PageList<BankVo> selectList(BankDto bankDto){
        PageUtils.startPage(bankDto.getCurrentPage(), bankDto.getPageSize());
        List<Bank> list = bankDao.selectList(bankDto);
        List<BankVo> bankVoList = new ArrayList <BankVo>();
        if(list != null && list.size() > 0) {
            for(Bank bank : list){
                BankVo bankVo = new BankVo();
                //Bank to BankVo
                bankVoList.add(bankVo);
            }
        }
        return new PageModel<BankVo>().getPageList(bankVoList);
    }


    public BankVo detail(Long bankId) {
        Bank bank = bankDao.selectByKid(Bank.class,bankId);
        BankVo bankVo = new BankVo();
        if (bank != null) {
            BeanUtils.copyProperties(bank,bankVo);
            return bankVo;
        }
        return null;
    }
 }

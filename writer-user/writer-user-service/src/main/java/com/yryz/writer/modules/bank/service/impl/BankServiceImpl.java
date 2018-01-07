package com.yryz.writer.modules.bank.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.constant.YyrzModuleEnumConstants;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.qstone.entity.base.model.BankCard;
import com.yryz.qstone.modules.base.api.OpenBankCardApi;
import com.yryz.writer.modules.bank.dao.persistence.BankDao;
import com.yryz.writer.modules.bank.service.BankService;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.writer.service.WriterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.bank.vo.BankVo;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.bank.dto.BankDto;
import java.util.ArrayList;
import java.util.List;


@Service
public class BankServiceImpl extends BaseServiceImpl implements BankService {

    private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
    @Autowired
    private BankDao bankDao;

    @Autowired
    private OpenBankCardApi openBankCardApi;

    @Autowired
    private IdAPI idAPI;

    @Autowired
    private WriterService writerService;

    @Value("${appId}")
    private String clientCode;

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

    @Override
    public Bank insertBank(Bank bank) {
        Long kid  = idAPI.getId("yryz_bank");
        bank.setKid(kid);
        bank.setModuleEnum(YyrzModuleEnumConstants.BANK_INFO);
        bankDao.insert(bank);
        try{
            BankCard bankCard=new BankCard();
            //银行卡号
            bankCard.setBankCardNo(bank.getUserBankCart());
            bankCard.setBankCardType((byte)10);

            bankCard.setBankName(bank.getUserAccountOpenBank());
            bankCard.setStatus((byte)1);
            bankCard.setCertNo("身份证");
            bankCard.setOwnerCode(1l);
            RpcContext.getContext().setAttachment("clientCode", clientCode);
            openBankCardApi.add(bankCard);
        }catch(Exception e){
            logger.error("调用资金系统绑定银行卡出现异常:", e);
            throw new YyrzPcException(ExceptionEnum.BindBankException.getCode(),ExceptionEnum.BindBankException.getMsg(),
                    ExceptionEnum.BindBankException.getErrorMsg()
            );
        }

        return bank;
    }

    @Override
    public Bank updateBank(Bank bank) {
        bankDao.update(bank);
        try{
            BankCard bankCard=new BankCard();
            //银行卡号
            bankCard.setBankCardNo(bank.getUserBankCart());
            bankCard.setBankCardType((byte)10);
            bankCard.setBankName(bank.getUserAccountOpenBank());
            bankCard.setStatus((byte)1);
            bankCard.setCertNo("身份证");
            bankCard.setOwnerCode(1l);
            RpcContext.getContext().setAttachment("clientCode", clientCode);
            openBankCardApi.add(bankCard);
        }catch(Exception e){
            logger.error("调用资金系统绑定银行卡出现异常:", e);
            throw new YyrzPcException(ExceptionEnum.BindBankException.getCode(),ExceptionEnum.BindBankException.getMsg(),
                    ExceptionEnum.BindBankException.getErrorMsg()
            );
        }
        return bank;
    }
}

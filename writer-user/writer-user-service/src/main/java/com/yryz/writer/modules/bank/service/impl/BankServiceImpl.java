package com.yryz.writer.modules.bank.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.yryz.qstone.entity.base.model.Owner;
import com.yryz.qstone.modules.base.api.OpenOwnerApi;
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
import com.yryz.writer.modules.bank.constant.BankConstant;
import com.yryz.writer.modules.bank.dao.persistence.BankDao;
import com.yryz.writer.modules.bank.service.BankService;
import com.yryz.writer.modules.city.CityApi;
import com.yryz.writer.modules.city.vo.CityVo;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.province.ProvinceApi;
import com.yryz.writer.modules.province.vo.ProvinceVo;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.service.WriterService;
import com.yryz.writer.modules.writer.vo.WriterCapitalVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.bank.vo.BankVo;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.bank.dto.BankDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class BankServiceImpl extends BaseServiceImpl implements BankService {

    private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
    @Autowired
    private BankDao bankDao;

    @Autowired
    private OpenBankCardApi openBankCardApi;

    @Autowired
    private OpenOwnerApi openOwnerApi;

    @Autowired
    private IdAPI idAPI;

    @Autowired
    private ProvinceApi provinceApi;

    @Autowired
    private CityApi cityApi;


    @Autowired
    private WriterService writerService;

    @Value("${clientCode}")
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
            ProvinceVo provinceVo = provinceApi.selectProvinces(bank.getProvice());
            CityVo cityVo = cityApi.selectCity(bank.getCity());
            bankVo.setProviceName(provinceVo.getProvinceName());
            bankVo.setCityName(cityVo.getCityName());
            return bankVo;
        }
        return null;
    }

    /**
     * 根据写手信息查询资金主体
     * @param bank
     * @return
     */
    public Long findOwnerByWriter(Bank bank){
        //根据写手id,查询写手信息
        WriterDto writerDto = new WriterDto();
        //写手id
        writerDto.setKid(Long.valueOf(bank.getCreateUserId()));
        WriterCapitalVo writerModelVo = writerService.selectWriterByParameters(writerDto);
        Owner owner = new Owner();
        owner.setOwnerFcode(Long.valueOf(writerModelVo.getOwnerFcode()));
        owner = openOwnerApi.detail(owner);
        if(null == owner){
            logger.error("根据银行卡拥有者查不到资金主体账号");
            throw new YyrzPcException(ExceptionEnum.FINDMODELFAIL_EXCEPTION.getCode(),ExceptionEnum.FINDMODELFAIL_EXCEPTION.getMsg(),
                    ExceptionEnum.FINDMODELFAIL_EXCEPTION.getErrorMsg());
        }
        return owner.getOwnerCode();
    }

    @Override
    public Bank insertBank(Bank bank) {
        try{
            Long kid  = idAPI.getId(BankConstant.BANKTABLE);
            bank.setKid(kid);
            bank.setModuleEnum(YyrzModuleEnumConstants.BANK_INFO);
            bankDao.insert(bank);

            BankCard bankCard=new BankCard();
            //银行卡号
            bankCard.setBankCardNo(bank.getUserBankCart());
            //个人银行卡
            bankCard.setBankCardType(BankConstant.BANKCARDTYPE.byteValue());
            //银行名称
            bankCard.setBankName(bank.getUserAccountOpenBank());
            //状态生效
            bankCard.setStatus(BankConstant.BANKCARDSTATUS.byteValue());
            //身份证
            bankCard.setCertNo(bank.getUserCart());
            //资金主体编码
            bankCard.setOwnerCode(findOwnerByWriter(bank));
            RpcContext.getContext().setAttachment("clientCode", clientCode);
            openBankCardApi.add(bankCard);;
        }catch(Exception e){
            logger.error("调用资金系统绑定银行卡出现异常:", e);
            throw new YyrzPcException(ExceptionEnum.BIND_BANK_EXCEPTION.getCode(),ExceptionEnum.BIND_BANK_EXCEPTION.getMsg(),
                    ExceptionEnum.BIND_BANK_EXCEPTION.getErrorMsg()
            );
        }
        return bank;
    }

    @Override
    public Bank updateBank(Bank bank) {
        try{
            bankDao.update(bank);
            BankCard bankCard=new BankCard();
            //银行卡号
            bankCard.setBankCardNo(bank.getUserBankCart());
            //个人银行卡
            bankCard.setBankCardType(BankConstant.BANKCARDTYPE.byteValue());
            //银行名称
            bankCard.setBankName(bank.getUserAccountOpenBank());
            //状态生效
            bankCard.setStatus(BankConstant.BANKCARDSTATUS.byteValue());
            //身份证
            bankCard.setCertNo(bank.getUserCart());
            //资金主体外码
            bankCard.setOwnerCode(findOwnerByWriter(bank));
            RpcContext.getContext().setAttachment("clientCode", clientCode);
            openBankCardApi.updateBankCard(bankCard);
        }catch(Exception e){
            logger.error("调用资金系统绑定银行卡出现异常:", e);
            throw new YyrzPcException(ExceptionEnum.BIND_BANK_EXCEPTION.getCode(),ExceptionEnum.BIND_BANK_EXCEPTION.getMsg(),
                    ExceptionEnum.BIND_BANK_EXCEPTION.getErrorMsg()
            );
        }
        return bank;
    }

    @Override
    public BankVo selectByParameters(BankDto bankDto) {
        BankVo bankVo = new BankVo();
        Bank bank =  bankDao.selectByParameters(bankDto);
        if (bank != null) {
            BeanUtils.copyProperties(bank,bankVo);
            ProvinceVo provinceVo = provinceApi.selectProvinces(bank.getProvice());
            CityVo cityVo = cityApi.selectCity(bank.getCity());
            bankVo.setProviceName(provinceVo.getProvinceName());
            bankVo.setCityName(cityVo.getCityName());
            return bankVo;
        }
        return null;
    }

    @Override
    public List<Bank> selectListByWriterIds(BankDto bankDto) {
        return bankDao.selectList(bankDto);
    }
}

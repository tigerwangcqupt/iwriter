package com.yryz.writer.modules.bank.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.yryz.qstone.entity.base.constants.AccountConstants;
import com.yryz.qstone.entity.base.dto.BankCardDto;
import com.yryz.qstone.entity.base.model.Owner;
import com.yryz.qstone.modules.base.api.OpenAccountApi;
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
import com.yryz.writer.modules.bank.constant.BankUtil;
import com.yryz.writer.modules.bank.constant.IDCardValidate;
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
import org.apache.commons.lang3.StringUtils;
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
        if(null != writerModelVo && StringUtils.isNotEmpty(writerModelVo.getOwnerFcode())){
            return Long.valueOf(writerModelVo.getOwnerFcode());
        }
        return null;
    }

    @Override
    public Bank insertBank(Bank bank) {
        //验证bankCard是否真实
        boolean checkBankCard = BankUtil.matchLuhn(bank.getUserBankCart());
        if(!checkBankCard){
            logger.error("银行卡卡号不存在");
            throw new YyrzPcException(ExceptionEnum.NOT_FOUNTD_BANKCARD_EXCEPTION.getCode(),ExceptionEnum.NOT_FOUNTD_BANKCARD_EXCEPTION.getMsg(),
                    ExceptionEnum.NOT_FOUNTD_BANKCARD_EXCEPTION.getErrorMsg());
        }
        //验证身份证是否真实
        boolean checkUserCard = IDCardValidate.validate(bank.getUserCart());
        if(!checkUserCard){
            logger.error("身份证不正确");
            throw new YyrzPcException(ExceptionEnum.NOT_FOUNTD_USERCARD_EXCEPTION.getCode(),ExceptionEnum.NOT_FOUNTD_USERCARD_EXCEPTION.getMsg(),
                    ExceptionEnum.NOT_FOUNTD_USERCARD_EXCEPTION.getErrorMsg());
        }
        //资金主体外码
        Long ownerFcode = findOwnerByWriter(bank);
        if(null == ownerFcode){
            logger.error("查询资金主体外码失败");
            throw new YyrzPcException(ExceptionEnum.FINDMODELFAIL_EXCEPTION.getCode(),ExceptionEnum.FINDMODELFAIL_EXCEPTION.getMsg(),
                    ExceptionEnum.FINDMODELFAIL_EXCEPTION.getErrorMsg());
        }
        try{
            BankCardDto bankCardDto=new BankCardDto();
            //银行卡号
            bankCardDto.setBankCardNo(bank.getUserBankCart());
                //个人银行卡
            bankCardDto.setBankCardType(BankConstant.BANKCARDTYPE.byteValue());
                //银行名称
            bankCardDto.setBankName(bank.getUserAccountOpenBank());
                //状态生效
            bankCardDto.setStatus(BankConstant.BANKCARDSTATUS.byteValue());
                //身份证
            bankCardDto.setCertNo(bank.getUserCart());
                //资金主体编码
            bankCardDto.setOwnerFcode(ownerFcode);
            //账户类型
            bankCardDto.setAccountTypeCode(AccountConstants.Type.USER_PROFIT);
            RpcContext.getContext().setAttachment("clientCode", clientCode);
            BankCard bankCard = openBankCardApi.createAndBind(bankCardDto);

            //增加银行卡外码
            Long kid  = idAPI.getId(BankConstant.BANKTABLE);
            bank.setKid(kid);
            bank.setBankcardFcode(bankCard.getBankCardFcode());
            bankDao.insert(bank);

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
        //验证bankCard是否真实
        boolean checkBankCard = BankUtil.matchLuhn(bank.getUserBankCart());
        if(!checkBankCard){
            logger.error("银行卡卡号不存在");
            throw new YyrzPcException(ExceptionEnum.NOT_FOUNTD_BANKCARD_EXCEPTION.getCode(),ExceptionEnum.NOT_FOUNTD_BANKCARD_EXCEPTION.getMsg(),
                    ExceptionEnum.NOT_FOUNTD_BANKCARD_EXCEPTION.getErrorMsg());
        }
        //验证身份证是否真实
        boolean checkUserCard = IDCardValidate.validate(bank.getUserCart());
        if(!checkUserCard){
            logger.error("身份证不正确");
            throw new YyrzPcException(ExceptionEnum.NOT_FOUNTD_USERCARD_EXCEPTION.getCode(),ExceptionEnum.NOT_FOUNTD_USERCARD_EXCEPTION.getMsg(),
                    ExceptionEnum.NOT_FOUNTD_USERCARD_EXCEPTION.getErrorMsg());
        }
        //资金主体外码
        Long ownerFcode = findOwnerByWriter(bank);
        if(null == ownerFcode){
            logger.error("查询资金主体外码失败");
            throw new YyrzPcException(ExceptionEnum.FINDMODELFAIL_EXCEPTION.getCode(),ExceptionEnum.FINDMODELFAIL_EXCEPTION.getMsg(),
                    ExceptionEnum.FINDMODELFAIL_EXCEPTION.getErrorMsg());
        }
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
            bankCard.setBankCardFcode(bank.getBankcardFcode());
            RpcContext.getContext().setAttachment("clientCode", clientCode);
            openBankCardApi.updateByFcode(bankCard);
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

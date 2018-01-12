package com.yryz.writer.modules.profit.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.yryz.qstone.entity.transaction.dto.TransactionFlowRecord;
import com.yryz.qstone.modules.transaction.api.OpenTransactionApi;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.constant.YyrzModuleEnumConstants;
import com.yryz.writer.common.distributed.lock.DistributedLockUtils;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.utils.DateUtil;
import com.yryz.writer.common.utils.MoneyUtils;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.qstone.entity.base.model.Account;
import com.yryz.qstone.entity.base.model.Owner;
import com.yryz.qstone.modules.base.api.OpenAccountApi;
import com.yryz.qstone.modules.base.api.OpenOwnerApi;
import com.yryz.writer.modules.bank.dto.BankDto;
import com.yryz.writer.modules.bank.entity.Bank;
import com.yryz.writer.modules.bank.service.BankService;
import com.yryz.writer.modules.city.CityApi;
import com.yryz.writer.modules.city.vo.CityVo;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.vo.NoticeReceiveWriter;
import com.yryz.writer.modules.message.vo.WriterNoticeMessageVo;
import com.yryz.writer.modules.profit.constant.ProfitConstants;
import com.yryz.writer.modules.profit.constant.ProfitEnum;
import com.yryz.writer.modules.profit.util.CommonUtils;
import com.yryz.writer.modules.profit.vo.ProfitAdminVo;
import com.yryz.writer.modules.profit.vo.ProfitDetailVo;
import com.yryz.writer.modules.profit.vo.ProfitStaticsVo;
import com.yryz.writer.modules.province.ProvinceApi;
import com.yryz.writer.modules.province.vo.ProvinceVo;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.service.WriterService;
import com.yryz.writer.modules.writer.vo.WriterAdminRefProfit;
import com.yryz.writer.modules.writer.vo.WriterCapitalVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.profit.vo.ProfitVo;
import com.yryz.writer.modules.profit.entity.Profit;
import com.yryz.writer.modules.profit.dto.ProfitDto;
import com.yryz.writer.modules.profit.dao.persistence.ProfitDao;
import com.yryz.writer.modules.profit.service.ProfitService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ProfitServiceImpl extends BaseServiceImpl implements ProfitService
{
    private static final Logger logger = LoggerFactory.getLogger(ProfitServiceImpl.class);
    private static final String LOCK_PROFIT_ADD = "PROFIT_ADD";
    private static final String LOCK_PROFIT_UPDATE = "LOCK_PROFIT_UPDATE";
    @Autowired
    private ProfitDao profitDao;

    @Autowired
    private WriterService writerService;

    @Autowired
    private BankService bankService;

    @Autowired
    private IdAPI idAPI;

    @Autowired
    private OpenOwnerApi openOwnerApi;

    @Autowired
    private OpenAccountApi openAccountApi;

    @Autowired
    private OpenTransactionApi openTransactionApi;

    @Autowired
    private MessageApi messageApi;

    @Autowired
    private ProvinceApi provinceApi;

    @Autowired
    private CityApi cityApi;

    @Value("${clientCode}")
    private String clientCode;

    //平台资金主体外码
    @Value("${ownerFCode}")
    private Long ownerFCode;

    //业务外码提现
    @Value("${busiFCode}")
    private Long busiFCode;

    //业务外码稿费
    @Value("${busiRoyalFCode}")
    private Long busiRoyalFCode;

    @Value("${currencyCode}")
    private Long  currencyCode;

    protected BaseDao getDao() {
        return profitDao;
    }

    public PageList<ProfitVo> selectList(ProfitDto profitDto){
        PageUtils.startPage(profitDto.getCurrentPage(), profitDto.getPageSize());
        List<Profit> list = profitDao.selectList(profitDto);
        List<ProfitVo> profitVoList = new ArrayList <ProfitVo>();
        if(list != null && list.size() > 0) {
            for(Profit profit : list){
                ProfitVo profitVo = new ProfitVo();
                profitVoList.add(profitVo);
            }
        }
        return new PageModel<ProfitVo>().getPageList(profitVoList);
    }


    public ProfitVo detail(Long profitId) {
        Profit profit = profitDao.selectByKid(Profit.class,profitId);
        ProfitVo profitVo = new ProfitVo();
        if (profitVo != null) {
        }
        return profitVo;
    }

    @Override
    public ProfitVo detailProfit(Long userId) {
        ProfitVo profitVo = new ProfitVo();
        List<ProfitDetailVo> detailList = new ArrayList<>();
        ProfitDto profitDto = new ProfitDto();
        profitDto.setCreateUserId(userId);
        List<Profit> list = profitDao.selectList(profitDto);
        if(CollectionUtils.isNotEmpty(list)){
            //剩余可提现金额
            BigDecimal availableAmount =  list.get(0).getSurplusAmount();
            //累计提现金额
            BigDecimal accumulativeAmount = new BigDecimal(0);
            //最近提现金额
            BigDecimal currentAmount = new BigDecimal(0);
            int i=0;
            for(Profit profit : list){
                ProfitDetailVo profitDetailVo = new ProfitDetailVo();
                //提现
                if(profit.getSettlementType() == ProfitEnum.WITHDRAWALS_FEE.getCode()){
                    //最近提现金额
                    if(i==0){
                        currentAmount = currentAmount.add(profit.getSettlementAmount());
                        //最近提现日期
                        profitVo.setSettlementDate(DateUtil.getNYRString(profit.getSettlementDate()));
                    }
                    accumulativeAmount = accumulativeAmount.add(profit.getSettlementAmount());
                    i++;
                }
                BeanUtils.copyProperties(profit,profitDetailVo);
                profitDetailVo.setSettlementDate(DateUtil.getNYRString(profit.getSettlementDate()));
                profitDetailVo.setSettlementType(ProfitEnum.profitEnumMap.get(profit.getSettlementType()).getMsg());
                detailList.add(profitDetailVo);
            }
            //剩余可提现金额
            profitVo.setAvailableAmount(availableAmount);
            //累计提现金额
            profitVo.setAccumulativeAmount(accumulativeAmount);
            //最近提现金额
            profitVo.setCurrentAmount(currentAmount);
            //detailList
            profitVo.setList(detailList);
        }
        return profitVo;
    }


    /**
     * 提现|审核文章
     * @param profit
     * @return
     */
    @Override
    public Profit insertProfit(Profit profit) {
        String lockKey = null;
        try {
            //当前提现金额
            BigDecimal settlementAmount = profit.getSettlementAmount();
            //提现金额不是正整数
            if(!CommonUtils.checkIntNumber(settlementAmount.toString())){
                logger.error("提现金额不是正整数");
                throw new YyrzPcException(ExceptionEnum.TX_NOTINT_EXCEPTION.getCode(),ExceptionEnum.TX_NOTINT_EXCEPTION.getMsg(),
                        ExceptionEnum.TX_NOTINT_EXCEPTION.getErrorMsg());
            }
            //提现日期
            Date settlementDate = new Date();
            //分布式锁控制用户频繁操作
            lockKey = DistributedLockUtils.lock(LOCK_PROFIT_ADD, profit.getCreateUserId());
            WriterDto writerDto = new WriterDto();
            writerDto.setKid(profit.getWriterId());
            WriterCapitalVo writerModelVo =writerService.selectWriterByParameters(writerDto);
            //剩余可提现金额
            BigDecimal withdrawAmount = writerModelVo.getWithdrawAmount();
            //稿费的时候不去判断
            if(profit.getSettlementType() != ProfitEnum.ROYALTIES_FEE.getCode()){
                //当剩余金额小于当前提现金额时
                if(null != withdrawAmount && withdrawAmount.compareTo(settlementAmount)==-1){
                    logger.error("当前提现金额大于剩余可提现金额");
                    throw new YyrzPcException(ExceptionEnum.TX_MORETHANSURPLUS_EXCEPTION.getCode(),ExceptionEnum.TX_MORETHANSURPLUS_EXCEPTION.getMsg(),
                            ExceptionEnum.TX_MORETHANSURPLUS_EXCEPTION.getErrorMsg());
                }
            }
            //更新写手信息
            Writer writer = new Writer();
            writer.setSettlementType(ProfitEnum.WITHDRAWALS_FEE.getCode());
            writer.setKid(profit.getWriterId());
            writer.setWithdrawDate(settlementDate);
            //同步插入到profit流水表
            Long kid  = idAPI.getId(ProfitConstants.PROFITTABLE);
            profit.setKid(kid);
            profit.setModuleEnum(YyrzModuleEnumConstants.PROFIT_INFO);
            profit.setSettlementDate(settlementDate);
            //当前提现金额扩大一万倍
            profit.setSettlementAmount(MoneyUtils.setBigDecimal(profit.getSettlementAmount()));
            //如果是提现
            if(profit.getSettlementType() == ProfitEnum.WITHDRAWALS_FEE.getCode()){
                writer.setLatelyWithdrawAmount(MoneyUtils.setBigDecimal(profit.getSettlementAmount()));
                //设置流水号
                String profitSn = String.valueOf(idAPI.getSnowflakeId());
                profit.setProfitSn(profitSn);
                //手续费扩大一万倍
                profit.setChargeFee(MoneyUtils.setBigDecimal(new BigDecimal(ProfitConstants.CHARGEFEE)));
                //剩余提现金额扩大一万倍
                profit.setSurplusAmount(MoneyUtils.setBigDecimal(withdrawAmount.subtract(settlementAmount)));
                //提现消息
                profit.setSettlementMsg(ProfitEnum.WITHDRAWALS_FEE.getMsg());
                insert(profit);
                //最后修改信息
                writerService.updateWriterProfit(writer);
            }
            //如果是稿费
            else if(profit.getSettlementType() == ProfitEnum.ROYALTIES_FEE.getCode()){
                //手续费扩大一万倍
                profit.setChargeFee(new BigDecimal(0));
                //剩余提现金额扩大一万倍
                profit.setSurplusAmount(MoneyUtils.setBigDecimal(withdrawAmount.add(settlementAmount)));
                //提现消息
                profit.setSettlementMsg(ProfitEnum.ROYALTIES_FEE.getMsg());
                insertByPrimaryKeySelective(profit);
                writer.setWithdrawAmount(withdrawAmount.add(MoneyUtils.setBigDecimal(settlementAmount)));
                //最后修改信息
                writerService.update(writer);
                //增加流水到资金主体
                addRoyalFlow(profit);
            }
            return profit;
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            if (null != lockKey) {
                DistributedLockUtils.unlock(LOCK_PROFIT_ADD, lockKey);
            }
        }
    }


    /**
     * 提现成功|提现失败
     * 提现成功:调用资金平账,插入流水
     * 提现失败:插入流水
     * @param profit
     * @return
     */
    @Override
    public Profit updateProfit(Profit profit) {
        String lockKey = null;
        try {
            //分布式锁控制用户频繁操作
            lockKey = DistributedLockUtils.lock(LOCK_PROFIT_UPDATE, profit.getCreateUserId());
            ProfitDto profitDto = new ProfitDto();
            profitDto.setWriterId(profit.getWriterId());
            profitDto.setProfitSn(profit.getProfitSn());
            profitDto.setSettlementType(profit.getSettlementType());
            List<ProfitDetailVo> existFlows = profitDao.selectFlowList(profitDto);
            if(CollectionUtils.isNotEmpty(existFlows)){
                logger.error("已经存在该流水:"+ JSON.toJSONString(profit));
                throw new YyrzPcException(ExceptionEnum.EXSITS_TXFLOW_EXCEPTION.getCode(),ExceptionEnum.EXSITS_TXFLOW_EXCEPTION.getMsg(),
                        ExceptionEnum.EXSITS_TXFLOW_EXCEPTION.getErrorMsg()
                );
            }
            Profit profitBase = new Profit();
            Profit profitData = profitDao.selectByKid(Profit.class, profit.getKid());
            BeanUtils.copyProperties(profitData, profitBase);
            Long oldKid = profitData.getKid();

            //提现日期
            Date settlementDate = new Date();
            Long kid = idAPI.getId(ProfitConstants.PROFITTABLE);
            profitData.setKid(kid);
            profitData.setSettlementDate(settlementDate);

            WriterDto writerDto = new WriterDto();
            writerDto.setKid(profit.getWriterId());
            WriterCapitalVo writerModelVo = writerService.selectWriterByParameters(writerDto);
            //剩余可提现金额
            BigDecimal withdrawAmount = writerModelVo.getWithdrawAmount();
            //当前提现金额
            BigDecimal settlementAmount = profit.getSettlementAmount();
            //提现成功,只更新写手表的累计提现金额
            if (profit.getSettlementType() == ProfitEnum.WITHDRAWALS_SUCCESS.getCode()) {
                //当剩余金额小于当前提现金额时
                if (null != withdrawAmount && withdrawAmount.compareTo(settlementAmount) == -1) {
                    logger.error("当前提现金额大于剩余可提现金额");
                    throw new YyrzPcException(ExceptionEnum.TX_MORETHANSURPLUS_EXCEPTION.getCode(), ExceptionEnum.TX_MORETHANSURPLUS_EXCEPTION.getMsg(),
                            ExceptionEnum.TX_MORETHANSURPLUS_EXCEPTION.getErrorMsg());
                }
                //更新流水,只改变状态
                profitData.setSettlementType(profit.getSettlementType());
                profitData.setSettlementMsg(ProfitEnum.WITHDRAWALS_SUCCESS.getMsg());
                BigDecimal surplusAmount = profitData.getSurplusAmount().subtract(MoneyUtils.setBigDecimal(profit.getSettlementAmount()));
                profitData.setSurplusAmount(surplusAmount);
                insert(profitData);
                //更新写手信息,只改累计提现金额
                Writer writer = new Writer();
                writer.setSettlementType(ProfitEnum.WITHDRAWALS_SUCCESS.getCode());
                writer.setKid(profit.getWriterId());
                writer.setWithdrawDate(settlementDate);
                writer.setSumWithdrawAmount(MoneyUtils.setBigDecimal(writerModelVo.getSumWithdrawAmount().add(profit.getSettlementAmount())));
                writerService.update(writer);

                //修改记录
                profitBase.setSettlementType(ProfitEnum.WITHDRAWALS_FEE.getCode());
                profitBase.setLastUpdateUserId(profit.getLastUpdateUserId());
                profitBase.setKid(oldKid);
                profitDao.update(profitBase);
                //提现流水
                addTxFlow(profitData);
            }
            //提现失败
            else if (profit.getSettlementType() == ProfitEnum.WITHDRAWALS_FAIL.getCode()) {
                //调用消息发送接口
                WriterNoticeMessageVo writerNoticeMessageVo = new WriterNoticeMessageVo();
                writerNoticeMessageVo.setContent(profit.getSettlementMsg());
                writerNoticeMessageVo.setTriggerType(3);
                writerNoticeMessageVo.setSendUserId(Long.valueOf(profit.getLastUpdateUserId()));
                List<NoticeReceiveWriter> receiveWriters = new ArrayList<>();
                NoticeReceiveWriter noticeReceiveWriter = new NoticeReceiveWriter();
                noticeReceiveWriter.setKid(profit.getWriterId());
                receiveWriters.add(noticeReceiveWriter);
                writerNoticeMessageVo.setReceiveWriter(receiveWriters);
                messageApi.saveWriterNoticeMessage(writerNoticeMessageVo);

                //更新流水,把钱加回去,加入剩余可提现金额
                profitData.setSettlementType(profit.getSettlementType());
                BigDecimal surplusAmount = profitData.getSurplusAmount().add(MoneyUtils.setBigDecimal(profit.getSettlementAmount()));
                profitData.setSettlementMsg(profit.getSettlementMsg());
                profitData.setSurplusAmount(surplusAmount);
                insert(profitData);

                //修改记录
                profitBase.setSettlementType(ProfitEnum.WITHDRAWALS_FEE.getCode());
                profitBase.setLastUpdateUserId(profit.getLastUpdateUserId());
                profitBase.setKid(oldKid);
                profitDao.update(profitBase);

                //更新写手信息,把钱加回去,加入剩余可提现金额
                Writer writer = new Writer();
                writer.setSettlementType(ProfitEnum.WITHDRAWALS_FAIL.getCode());
                writer.setKid(profit.getWriterId());
                writer.setWithdrawDate(settlementDate);
                writer.setWithdrawAmount(writerModelVo.getWithdrawAmount().add(MoneyUtils.setBigDecimal(profit.getSettlementAmount())));
                writerService.update(writer);
            }
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            if (null != lockKey) {
                DistributedLockUtils.unlock(LOCK_PROFIT_UPDATE, lockKey);
            }
        }
        return profit;
    }

    /**
     * 写手绑定资金主体(返回资金主体)
     * @param writer
     * @return
     */
    @Override
    public Writer bindCapital(Writer writer) {
        Owner data=new Owner();
        try{
            Owner owner=new Owner();
            RpcContext.getContext().setAttachment("clientCode", clientCode);
            //资金主体名称
            owner.setOwnerName(writer.getUserName());
            //个人
            owner.setOwnerType(ProfitConstants.OWNERTYPE);
            openOwnerApi.add(owner);
            data.setOwnerName(writer.getUserName());
            RpcContext.getContext().setAttachment("clientCode", clientCode);
            data = openOwnerApi.detail(data);
        }catch(Exception e){
            logger.error("调用资金系统插入资金主体表出现异常:", e);
            throw new YyrzPcException(ExceptionEnum.ADD_OWNER_EXCEPTION.getCode(),ExceptionEnum.ADD_OWNER_EXCEPTION.getMsg(),
                    ExceptionEnum.ADD_OWNER_EXCEPTION.getErrorMsg()
                    );
        }
        try{
            Account account=new Account();
            account.setAccountName(writer.getUserName());
            account.setAccountTypeCode(ProfitConstants.USERACCOUNTTYPECODE.byteValue());
            account.setCurrencyCode(currencyCode);
            account.setStatus(ProfitConstants.ACCOUNTSTATUS);
            account.setOwnerCode(data.getOwnerCode());
            RpcContext.getContext().setAttachment("clientCode", clientCode);
            openAccountApi.add(account);
        }catch(Exception e){
            logger.error("调用资金系统插入账户出现异常:" , e);
            throw new YyrzPcException(ExceptionEnum.ADD_ACCOUNT_EXCEPTION.getCode(),ExceptionEnum.ADD_ACCOUNT_EXCEPTION.getMsg(),
                    ExceptionEnum.ADD_ACCOUNT_EXCEPTION.getErrorMsg()
            );
        }
        Writer writer1 = new Writer();
        writer1.setKid(writer.getKid());
        writer1.setOwnerFcode(data.getOwnerFcode()+"");
        writerService.update(writer1);
        return writer;
    }

    @Override
    public PageList<ProfitDetailVo> selectFlowList(ProfitDto profitDto) {
        PageUtils.startPage(profitDto.getCurrentPage(), profitDto.getPageSize());
        List<ProfitDetailVo> list = profitDao.selectFlowList(profitDto);
        return new PageModel<ProfitDetailVo>().getPageList(list);
    }


    /**
     * 填充数据
     * @param list
     * @return
     */
    public List<ProfitAdminVo> fillProfitData(List<ProfitDetailVo> list){
        List<ProfitAdminVo> profitAdminVoList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)){
            List<String> profitSnList = new ArrayList<>();
            //把流水号拿到反差用户表的数据
            for(ProfitDetailVo profitDetailVo : list){
                ProfitAdminVo profitAdminVo = new ProfitAdminVo();
                BeanUtils.copyProperties(profitDetailVo,profitAdminVo);
                profitSnList.add(profitDetailVo.getProfitSn());
                profitAdminVoList.add(profitAdminVo);
            }
            List<String> writerIdList = new ArrayList<>();
            WriterDto writerDto = new WriterDto();
            writerDto.setProfitSnList(profitSnList);
            List<WriterAdminRefProfit> writerRefProfitList = writerService.selectAllAdminProfitList(writerDto);
            if(CollectionUtils.isNotEmpty(writerRefProfitList)){
                for(WriterAdminRefProfit writerAdminRefProfit : writerRefProfitList){
                    for(ProfitAdminVo profitAdminVo : profitAdminVoList){
                        if(writerAdminRefProfit.getProfitSn().equals(profitAdminVo.getProfitSn())){
                            //把用户相关数据拷贝到返回实体
                            BeanUtils.copyProperties(writerAdminRefProfit,profitAdminVo);
                            profitAdminVo.setWriterId(writerAdminRefProfit.getKid());
                            writerIdList.add(writerAdminRefProfit.getKid()+"");
                        }
                    }
                }
            }
            BankDto bankDto = new BankDto();
            bankDto.setWriterIdList(writerIdList);
            List<Bank> bankList = bankService.selectListByWriterIds(bankDto);
            if(CollectionUtils.isNotEmpty(bankList)){
                for(Bank bank : bankList){
                    for(ProfitAdminVo profitAdminVo : profitAdminVoList){
                        if(bank.getCreateUserId().equals(profitAdminVo.getWriterId())){
                            String location = "";
                            String userName = profitAdminVo.getUserName();
                            BeanUtils.copyProperties(bank,profitAdminVo);
                            ProvinceVo provinceVo = provinceApi.selectProvinces(bank.getProvice());
                            CityVo cityVo = cityApi.selectCity(bank.getCity());
                            if(null != provinceVo && StringUtils.isNotEmpty(provinceVo.getProvinceName())){
                                location = provinceVo.getProvinceName();
                            }
                            if(null != cityVo && StringUtils.isNotEmpty(cityVo.getCityName())){
                                location =location+" "+cityVo.getCityName();
                            }
                            profitAdminVo.setLocation(location);
                            profitAdminVo.setUserRefBankName(bank.getUserName());
                            profitAdminVo.setUserName(userName);
                        }
                    }
                }
            }
        }
        return profitAdminVoList;
    }

    @Override
    public PageList<ProfitAdminVo> selectProfitAdminVoList(ProfitDto profitDto) {
        //按照排序规则找出所有的
        PageUtils.startPage(profitDto.getCurrentPage(), profitDto.getPageSize());
        List<ProfitDetailVo> list = profitDao.selectFlowList(profitDto);
        List<ProfitAdminVo> profitAdminVoList = fillProfitData(list);
        return new PageModel<ProfitAdminVo>().getPageList(profitAdminVoList);
    }

    @Override
    public List<ProfitAdminVo> selectAllProfitAdminVoList(ProfitDto profitDto) {
        List<ProfitDetailVo> list = profitDao.selectFlowList(profitDto);
        List<ProfitAdminVo> profitAdminVoList = fillProfitData(list);
        return profitAdminVoList;
    }

    @Override
    public ProfitStaticsVo staticsProfitVo(Long userId) {
        ProfitStaticsVo profitStaticsVo = new ProfitStaticsVo();
        WriterDto writerDto = new WriterDto();
        writerDto.setKid(userId);
        WriterCapitalVo writerModelVo =  writerService.selectWriterByParameters(writerDto);
        BeanUtils.copyProperties(writerModelVo,profitStaticsVo);
        profitStaticsVo.setLatelyWithdrawAmount(MoneyUtils.getMoney(profitStaticsVo.getLatelyWithdrawAmount()));
        profitStaticsVo.setSumWithdrawAmount(MoneyUtils.getMoney(profitStaticsVo.getSumWithdrawAmount()));
        profitStaticsVo.setWithdrawAmount(MoneyUtils.getMoney(profitStaticsVo.getWithdrawAmount()));
        return profitStaticsVo;
    }

    /**
     * 稿费流水
     * @param profit
     */
    public void addRoyalFlow(Profit profit){
        try{
            //得到写手的个人基本信息
            WriterDto writerDto = new WriterDto();
            writerDto.setKid(profit.getWriterId());
            WriterCapitalVo writerModelVo =writerService.selectWriterByParameters(writerDto);
            Long userFcode = Long.valueOf(writerModelVo.getOwnerFcode());

            BigDecimal amount = profit.getSettlementAmount();
            TransactionFlowRecord record = new TransactionFlowRecord();
            //订单号
            String orderId = String.valueOf(idAPI.getSnowflakeId());
            record.setOrderId(ProfitConstants.OREDERPREFIX+orderId);
            //支付单号
            String paySn = String.valueOf(idAPI.getSnowflakeId());
            record.setPaySn(ProfitConstants.OREDERPREFIX+paySn);
            ////业务编码（外码）稿费
            record.setBusiFCode(busiRoyalFCode);
            //总金额扩大10000倍
            record.setTotalAmount(amount);
            //总条数
            record.setTotalCount(ProfitConstants.ROYALTIESFLOWNUM);
            //流水记录集合
            List<TransactionFlowRecord.Flow> flowList = new ArrayList<TransactionFlowRecord.Flow>();
            //平台收益
            TransactionFlowRecord.Flow flow = new TransactionFlowRecord.Flow();
            flow.setOwnerFCode(ownerFCode);
            //账户类型编码(1 平台现金 2 平台暂存 3 平台收益 4 用户收益)
            flow.setAccountTypeCode(ProfitConstants.PLATACCOUNTTYPECODE);
            //发生额(元*10000)
            flow.setAmount(amount);
            //币种编码
            flow.setCurrencyCode(currencyCode);
            //记账标识(10入账，20出帐)
            flow.setAccountingFlag(ProfitConstants.ACCOUNTINGSUBFLAG);
            //现金标识(10现金，20非现金)
            flow.setCashFlag(ProfitConstants.NOTCASHFLAG);
            //核算标识(10核算，20不核算)
            flow.setCheckFlag(ProfitConstants.CHECKFLAG);
            flowList.add(flow);

            //用户收益
            TransactionFlowRecord.Flow flow2 = new TransactionFlowRecord.Flow();
            flow2.setOwnerFCode(userFcode);
            //账户类型编码(1 平台现金 2 平台暂存 3 平台收益 4 用户收益)
            flow2.setAccountTypeCode(ProfitConstants.USERACCOUNTTYPECODE);
            //发生额(元*10000)
            flow2.setAmount(amount);
            //币种编码
            flow2.setCurrencyCode(currencyCode);
            //记账标识(10入账)
            flow2.setAccountingFlag(ProfitConstants.ACCOUNTINGFLAG);
            //现金标识(10现金，20非现金)
            flow2.setCashFlag(ProfitConstants.NOTCASHFLAG);
            //核算标识(10核算，20不核算)
            flow2.setCheckFlag(ProfitConstants.CHECKFLAG);
            flowList.add(flow2);
            record.setFlowList(flowList);
            openTransactionApi.add(record);
        }catch (Exception e){
            logger.error("稿费流水插入资金系统失败",e);
            throw new YyrzPcException(ExceptionEnum.ADD_ROYALFLOWFAIL_EXCEPTION.getCode(),ExceptionEnum.ADD_ROYALFLOWFAIL_EXCEPTION.getMsg(),
                    ExceptionEnum.ADD_ROYALFLOWFAIL_EXCEPTION.getErrorMsg()
            );
        }
    }

    /**
     * 提现流水
     * @param profit
     */
    public void addTxFlow(Profit profit){
        try{
            //得到写手的个人基本信息
            WriterDto writerDto = new WriterDto();
            writerDto.setKid(profit.getWriterId());
            WriterCapitalVo writerModelVo =writerService.selectWriterByParameters(writerDto);
            Long userFcode = Long.valueOf(writerModelVo.getOwnerFcode());

            BigDecimal amount = profit.getSettlementAmount();
            TransactionFlowRecord record = new TransactionFlowRecord();
            //订单号
            String orderId = String.valueOf(idAPI.getSnowflakeId());
            record.setOrderId(ProfitConstants.OREDERPREFIX+orderId);
            //支付单号
            String paySn = String.valueOf(idAPI.getSnowflakeId());
            record.setPaySn(ProfitConstants.OREDERPREFIX+paySn);
            ////业务编码（外码）稿费
            record.setBusiFCode(busiRoyalFCode);
            //总金额扩大10000倍
            record.setTotalAmount(amount);
            //总条数
            record.setTotalCount(ProfitConstants.TXFLOWNUM);
            //流水记录集合
            List<TransactionFlowRecord.Flow> flowList = new ArrayList<TransactionFlowRecord.Flow>();

            //平台收益减去提现金额
            TransactionFlowRecord.Flow flow0 = new TransactionFlowRecord.Flow();
            flow0.setOwnerFCode(ownerFCode);
            //账户类型编码(平台现金)
            flow0.setAccountTypeCode(ProfitConstants.PLATCASHTYPECODE);
            //发生额(元*10000)
            flow0.setAmount(amount);
            //币种编码
            flow0.setCurrencyCode(currencyCode);
            //记账标识(10入账，20出帐)
            flow0.setAccountingFlag(ProfitConstants.ACCOUNTINGSUBFLAG);
            //现金标识(10现金，20非现金)
            flow0.setCashFlag(ProfitConstants.CASHFLAG);
            //核算标识(10核算，20不核算)
            flow0.setCheckFlag(ProfitConstants.CHECKFLAG);
            flowList.add(flow0);

            //平台收益:手续费
            TransactionFlowRecord.Flow flow = new TransactionFlowRecord.Flow();
            flow.setOwnerFCode(ownerFCode);
            //账户类型编码(1 平台现金 2 平台暂存 3 平台收益 4 用户收益)
            flow.setAccountTypeCode(ProfitConstants.PLATACCOUNTTYPECODE);
            //发生额(元*10000),手续费
            flow.setAmount(profit.getChargeFee());
            //币种编码
            flow.setCurrencyCode(currencyCode);
            //记账标识(10入账，20出帐)
            flow.setAccountingFlag(ProfitConstants.ACCOUNTINGFLAG);
            //现金标识(10现金，20非现金)
            flow.setCashFlag(ProfitConstants.NOTCASHFLAG);
            //核算标识(10核算，20不核算)
            flow.setCheckFlag(ProfitConstants.CHECKFLAG);
            flowList.add(flow);

            //用户收益
            TransactionFlowRecord.Flow flow2 = new TransactionFlowRecord.Flow();
            flow2.setOwnerFCode(userFcode);
            //账户类型编码(1 平台现金 2 平台暂存 3 平台收益 4 用户收益)
            flow2.setAccountTypeCode(ProfitConstants.USERACCOUNTTYPECODE);
            //发生额(元*10000),实际得款
            flow2.setAmount(amount.subtract(profit.getChargeFee()));
            //币种编码
            flow.setCurrencyCode(currencyCode);
            //记账标识(10入账，20出帐)
            flow2.setAccountingFlag(ProfitConstants.ACCOUNTINGFLAG);
            //现金标识(10现金，20非现金)
            flow2.setCashFlag(ProfitConstants.CASHFLAG);
            //核算标识(10核算，20不核算)
            flow2.setCheckFlag(ProfitConstants.CHECKFLAG);
            flowList.add(flow2);
            record.setFlowList(flowList);
            openTransactionApi.add(record);
        }catch (Exception e){
            logger.error("提现流水插入资金系统失败",e);
            throw new YyrzPcException(ExceptionEnum.ADD_TXFLOWFAIL_EXCEPTION.getCode(),ExceptionEnum.ADD_TXFLOWFAIL_EXCEPTION.getMsg(),
                    ExceptionEnum.ADD_TXFLOWFAIL_EXCEPTION.getErrorMsg()
            );
        }
    }
}

package com.yryz.writer.modules.profit.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
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
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.profit.constant.ProfitConstants;
import com.yryz.writer.modules.profit.constant.ProfitEnum;
import com.yryz.writer.modules.profit.vo.ProfitAdminVo;
import com.yryz.writer.modules.profit.vo.ProfitDetailVo;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.service.WriterService;
import com.yryz.writer.modules.writer.vo.WriterAdminRefProfit;
import com.yryz.writer.modules.writer.vo.WriterModelVo;
import org.apache.commons.collections.CollectionUtils;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ProfitServiceImpl extends BaseServiceImpl implements ProfitService
{
    private static final Logger logger = LoggerFactory.getLogger(ProfitServiceImpl.class);
    private static final String LOCK_PROFIT_ADD = "PROFIT_ADD";
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

    @Value("${clientCode}")
    private String clientCode;

    //平台资金主体外码
    @Value("${ownerFCode}")
    private Long ownerFCode;

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
                //Profit to ProfitVo
                profitVoList.add(profitVo);
            }
        }
        return new PageModel<ProfitVo>().getPageList(profitVoList);
    }


    public ProfitVo detail(Long profitId) {
        Profit profit = profitDao.selectByKid(Profit.class,profitId);
        ProfitVo profitVo = new ProfitVo();
        if (profitVo != null) {
            //Profit to ProfitVo
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
            //提现日期
            Date settlementDate = new Date();
            String profitSn = String.valueOf(idAPI.getSnowflakeId());
            profit.setProfitSn(profitSn);
            //分布式锁控制用户频繁操作
            lockKey = DistributedLockUtils.lock(LOCK_PROFIT_ADD, profit.getCreateUserId());
            WriterDto writerDto = new WriterDto();
            writerDto.setKid(profit.getWriterId());
            WriterModelVo writerModelVo =writerService.selectWriterByParameters(writerDto);
            //剩余可提现金额
            BigDecimal withdrawAmount = writerModelVo.getWithdrawAmount();
            //当前提现金额
            BigDecimal settlementAmount = profit.getSettlementAmount();
            //当剩余金额小于当前提现金额时
            if(null != withdrawAmount && withdrawAmount.compareTo(settlementAmount)==-1){
                logger.error("当前提现金额大于剩余可提现金额");
                throw new YyrzPcException(ExceptionEnum.TxMoreThanSurplusException.getCode(),ExceptionEnum.TxMoreThanSurplusException.getMsg(),
                        ExceptionEnum.TxMoreThanSurplusException.getErrorMsg());
            }
            //更新写手信息
            Writer writer = new Writer();
            writer.setSettlementType(ProfitEnum.WITHDRAWALS_FEE.getCode());
            writer.setKid(profit.getWriterId());
            writer.setWithdrawDate(settlementDate);
            writer.setLatelyWithdrawAmount(MoneyUtils.setBigDecimal(profit.getSettlementAmount()));
            writer.setWithdrawAmount(MoneyUtils.setBigDecimal(withdrawAmount.subtract(settlementAmount)));
            writerService.update(writer);
            //同步插入到profit流水表
            Long kid  = idAPI.getId(ProfitConstants.PROFITTABLE);
            profit.setKid(kid);
            profit.setModuleEnum(YyrzModuleEnumConstants.PROFIT_INFO);
            profit.setSettlementDate(settlementDate);
            profit.setSettlementType(ProfitEnum.WITHDRAWALS_FEE.getCode());
            //手续费扩大一万倍
            profit.setChargeFee(MoneyUtils.setBigDecimal(new BigDecimal(ProfitConstants.CHARGEFEE)));
            //剩余提现金额扩大一万倍
            profit.setSurplusAmount(MoneyUtils.setBigDecimal(withdrawAmount.subtract(settlementAmount)));
            //当前提现金额扩大一万倍
            profit.setSettlementAmount(MoneyUtils.setBigDecimal(profit.getSettlementAmount()));
            //提现消息
            profit.setSettlementMsg(ProfitEnum.WITHDRAWALS_FEE.getMsg());
            insert(profit);
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
        //提现日期
        Date settlementDate = new Date();
        Profit profitData = profitDao.selectByKid(Profit.class,profit.getKid());
        Long kid  = idAPI.getId(ProfitConstants.PROFITTABLE);
        profit.setKid(kid);
        profitData.setSettlementDate(settlementDate);

        WriterDto writerDto = new WriterDto();
        writerDto.setKid(profit.getWriterId());
        WriterModelVo writerModelVo =writerService.selectWriterByParameters(writerDto);
        //剩余可提现金额
        BigDecimal withdrawAmount = writerModelVo.getWithdrawAmount();
        //当前提现金额
        BigDecimal settlementAmount = profit.getSettlementAmount();
        //提现成功,只更新写手表的累计提现金额
         if(profit.getSettlementType() == ProfitEnum.WITHDRAWALS_SUCCESS.getCode()){
             //当剩余金额小于当前提现金额时
             if(null != withdrawAmount && withdrawAmount.compareTo(settlementAmount)==-1){
                 logger.error("当前提现金额大于剩余可提现金额");
                 throw new YyrzPcException(ExceptionEnum.TxMoreThanSurplusException.getCode(),ExceptionEnum.TxMoreThanSurplusException.getMsg(),
                         ExceptionEnum.TxMoreThanSurplusException.getErrorMsg());
             }
             //更新流水,只改变状态
             profitData.setSettlementType(profit.getSettlementType());
             profitData.setSettlementMsg(ProfitEnum.WITHDRAWALS_SUCCESS.getMsg());
             insert(profitData);
             //更新写手信息,只改累计提现金额
             Writer writer = new Writer();
             writer.setSettlementType(ProfitEnum.WITHDRAWALS_SUCCESS.getCode());
             writer.setKid(profit.getWriterId());
             writer.setWithdrawDate(settlementDate);
             writer.setSumWithdrawAmount(MoneyUtils.setBigDecimal(writerModelVo.getSumWithdrawAmount().add(profit.getSettlementAmount())));
             writerService.update(writer);
         }
         //提现失败
         else if(profit.getSettlementType() == ProfitEnum.WITHDRAWALS_FAIL.getCode()){
             //更新流水,把钱加回去,加入剩余可提现金额
             profitData.setSettlementType(profit.getSettlementType());
             BigDecimal surplusAmount = profitData.getSurplusAmount().add(profit.getSettlementAmount());
             profitData.setSurplusAmount(MoneyUtils.setBigDecimal(surplusAmount));
             insert(profitData);
             //更新写手信息,把钱加回去,加入剩余可提现金额
             Writer writer = new Writer();
             writer.setSettlementType(ProfitEnum.WITHDRAWALS_FAIL.getCode());
             writer.setKid(profit.getWriterId());
             writer.setWithdrawDate(settlementDate);
             writer.setWithdrawAmount(MoneyUtils.setBigDecimal(writerModelVo.getWithdrawAmount().add(profit.getSettlementAmount())));
             writerService.update(writer);
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
            throw new YyrzPcException(ExceptionEnum.AddOwnerException.getCode(),ExceptionEnum.AddOwnerException.getMsg(),
                    ExceptionEnum.AddOwnerException.getErrorMsg()
                    );
        }
        try{
            Account account=new Account();
            account.setAccountName(writer.getUserName());
            account.setAccountTypeCode(ProfitConstants.ACCOUNTTYPECODE);
            account.setCurrencyCode(currencyCode);
            account.setStatus(ProfitConstants.ACCOUNTSTATUS);
            account.setOwnerCode(data.getOwnerCode());
            RpcContext.getContext().setAttachment("clientCode", clientCode);
            openAccountApi.add(account);
        }catch(Exception e){
            logger.error("调用资金系统插入账户出现异常:" , e);
            throw new YyrzPcException(ExceptionEnum.AddAccountException.getCode(),ExceptionEnum.AddAccountException.getMsg(),
                    ExceptionEnum.AddAccountException.getErrorMsg()
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
                            profitAdminVo.setWriterId(writerAdminRefProfit.getKid().toString());
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
                            String userName = profitAdminVo.getUserName();
                            BeanUtils.copyProperties(bank,profitAdminVo);
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

    /**
     *
     * @param writerId
     */
    public void ff(Long writerId,BigDecimal amount){
        //得到写手的个人基本信息
        WriterDto writerDto = new WriterDto();
        writerDto.setKid(writerId);
        WriterModelVo writerModelVo =writerService.selectWriterByParameters(writerDto);


        TransactionFlowRecord record = new TransactionFlowRecord();
        //订单号
        String orderId = String.valueOf(idAPI.getSnowflakeId());
        record.setOrderId(ProfitConstants.OREDERPREFIX+orderId);
        //支付单号
        String paySn = String.valueOf(idAPI.getSnowflakeId());
        record.setPaySn(ProfitConstants.OREDERPREFIX+paySn);


        ////业务编码（外码）稿费
        record.setBusiFCode(12137L);
        //总金额扩大10000倍
        record.setTotalAmount(MoneyUtils.setBigDecimal(amount));
        //总条数
        record.setTotalCount(2);
        //流水记录集合
        List<TransactionFlowRecord.Flow> flowList = new ArrayList<TransactionFlowRecord.Flow>();
        //收益
        TransactionFlowRecord.Flow flow = new TransactionFlowRecord.Flow();
        flow.setOwnerFCode(ownerFCode);
        //账户类型编码(1 平台现金 2 平台暂存 3 平台收益 4 用户收益)
        flow.setAccountTypeCode(3);
        //发生额(元*10000)
        flow.setAmount(MoneyUtils.setBigDecimal(amount));
        //币种编码
        flow.setCurrencyCode(156l);
        //记账标识(10入账，20出帐)
        flow.setAccountingFlag(10);
        //现金标识(10现金，20非现金)
        flow.setCashFlag(10);
        //核算标识(10核算，20不核算)
        flow.setCheckFlag(10);
        flowList.add(flow);

        //收益
        TransactionFlowRecord.Flow flow2 = new TransactionFlowRecord.Flow();

        flow2.setOwnerFCode(19052l);
        //账户类型编码(1 平台现金 2 平台暂存 3 平台收益 4 用户收益)
        flow2.setAccountTypeCode(3);
        //发生额(元*10000)
        flow2.setAmount(new BigDecimal(10000*10000));
        //币种编码
        flow2.setCurrencyCode(156l);
        //记账标识(10入账，20出帐)
        flow2.setAccountingFlag(10);
        //现金标识(10现金，20非现金)
        flow2.setCashFlag(20);
        //核算标识(10核算，20不核算)
        flow2.setCheckFlag(10);
        flowList.add(flow2);
        record.setFlowList(flowList);
        openTransactionApi.add(record);
    }
}

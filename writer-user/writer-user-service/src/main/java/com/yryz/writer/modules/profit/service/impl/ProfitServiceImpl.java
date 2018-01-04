package com.yryz.writer.modules.profit.service.impl;

import com.yryz.common.constant.YyrzModuleEnumConstants;
import com.yryz.common.distributed.lock.DistributedLockUtils;
import com.yryz.common.utils.DateUtil;
import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.profit.constant.ProfitEnum;
import com.yryz.writer.modules.profit.vo.ProfitDetailVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IdAPI idAPI;

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

    @Override
    public Profit insertProfit(Profit profit) {
        String lockKey = null;
        try {
            //分布式锁控制用户频繁操作
            lockKey = DistributedLockUtils.lock(LOCK_PROFIT_ADD, profit.getCreateUserId());
            Long kid  = idAPI.getId("yryz_bank");
            profit.setKid(kid);
            profit.setModuleEnum(YyrzModuleEnumConstants.PROFIT_INFO);
            profit.setSettlementDate(new Date());
            profit.setChargeFee(new BigDecimal(2));
            profit.setSettlementType(ProfitEnum.WITHDRAWALS_FEE.getCode());
            profit.setSurplusAmount(new BigDecimal(500));
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
}

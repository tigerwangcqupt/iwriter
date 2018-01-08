package com.yryz.writer.modules.profit.entity;

import com.yryz.writer.common.entity.GenericEntity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
  * @ClassName: Profit
  * @Description: 收益记录表实体类
  * @author zhongying
  * @date 2017-12-29 15:36:15
  *
 */
public class Profit extends GenericEntity{
	

	/**
	 * 0正常，1已删除
	 */	 
    private  Integer delFlag;
    

	/**
	 * 结算日期
	 */	 
    private  Date settlementDate;
    

	/**
	 * 结算金额
	 */	 
    private BigDecimal settlementAmount;
    

	/**
	 * 1提现，1稿费
	 */	 
    private  Integer settlementType;
    

	/**
	 * 剩余可提现金额
	 */	 
    private  BigDecimal surplusAmount;
    

	/**
	 * 手续费
	 */	 
    private  BigDecimal chargeFee;
    

	/**
	 * 提现银行卡
	 */	 
    private  String bankCard;
    

	/**
	 * 提现银行
	 */	 
    private  String bankCash;
    

	/**
	 * 提现消息
	 */	 
    private  String settlementMsg;

	/**
	 * 流水号
	 */
	private  String profitSn;

	/**
	 * 写手id
	 */
	private  Long writerId;
    

	public Integer getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
		
	public Date getSettlementDate() {
		return this.settlementDate;
	}
	
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
		
	public BigDecimal getSettlementAmount() {
		return this.settlementAmount;
	}
	
	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
		
	public Integer getSettlementType() {
		return this.settlementType;
	}
	
	public void setSettlementType(Integer settlementType) {
		this.settlementType = settlementType;
	}
		
	public BigDecimal getSurplusAmount() {
		return this.surplusAmount;
	}
	
	public void setSurplusAmount(BigDecimal surplusAmount) {
		this.surplusAmount = surplusAmount;
	}
		
	public BigDecimal getChargeFee() {
		return this.chargeFee;
	}
	
	public void setChargeFee(BigDecimal chargeFee) {
		this.chargeFee = chargeFee;
	}
		
	public String getBankCard() {
		return this.bankCard;
	}
	
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
		
	public String getBankCash() {
		return this.bankCash;
	}
	
	public void setBankCash(String bankCash) {
		this.bankCash = bankCash;
	}
		
	public String getSettlementMsg() {
		return this.settlementMsg;
	}
	
	public void setSettlementMsg(String settlementMsg) {
		this.settlementMsg = settlementMsg;
	}

	public String getProfitSn() {
		return profitSn;
	}

	public void setProfitSn(String profitSn) {
		this.profitSn = profitSn;
	}

	public Long getWriterId() {
		return writerId;
	}

	public void setWriterId(Long writerId) {
		this.writerId = writerId;
	}
}
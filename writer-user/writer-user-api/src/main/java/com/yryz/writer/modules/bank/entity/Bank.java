package com.yryz.writer.modules.bank.entity;

import com.yryz.writer.common.entity.GenericEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 
  * @ClassName: Bank
  * @Description: 银行表实体类
  * @author zhongying
  * @date 2017-12-29 15:38:16
  *
 */
public class Bank extends GenericEntity{
	

	/**
	 * 0正常，1已删除
	 */	 
    private  Integer delFlag;
    

	/**
	 * 提现用户名
	 */
	@NotEmpty
    private  String userName;
    

	/**
	 * 身份证
	 */
	@NotEmpty
    private  String userCart;
    

	/**
	 * 银行卡号
	 */
	@NotEmpty
    private  String userBankCart;
    

	/**
	 * 交易银行
	 */
	@NotEmpty
    private  String userTradeBankCart;
    

	/**
	 * 省
	 */
	@NotEmpty
    private  String provice;
    

	/**
	 * 城市
	 */
	@NotEmpty
    private  String city;
    

	/**
	 * 开户支行
	 */
	@NotEmpty
    private  String userAccountOpenBank;
    

	public Integer getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
		
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
		
	public String getUserCart() {
		return this.userCart;
	}
	
	public void setUserCart(String userCart) {
		this.userCart = userCart;
	}
		
	public String getUserBankCart() {
		return this.userBankCart;
	}
	
	public void setUserBankCart(String userBankCart) {
		this.userBankCart = userBankCart;
	}
		
	public String getUserTradeBankCart() {
		return this.userTradeBankCart;
	}
	
	public void setUserTradeBankCart(String userTradeBankCart) {
		this.userTradeBankCart = userTradeBankCart;
	}
		
	public String getProvice() {
		return this.provice;
	}
	
	public void setProvice(String provice) {
		this.provice = provice;
	}
		
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
		
	public String getUserAccountOpenBank() {
		return this.userAccountOpenBank;
	}
	
	public void setUserAccountOpenBank(String userAccountOpenBank) {
		this.userAccountOpenBank = userAccountOpenBank;
	}
		
}
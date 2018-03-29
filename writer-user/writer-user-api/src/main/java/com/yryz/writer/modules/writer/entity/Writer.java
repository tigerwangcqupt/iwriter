package com.yryz.writer.modules.writer.entity;

import com.yryz.writer.common.entity.GenericEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
  * @ClassName: Writer
  * @Description: 写手信息表实体类
  * @author liuyanjun
  * @date 2018-01-03 10:39:54
  *
 */
public class Writer extends GenericEntity{
	
	/**
	 * 账号
	 */	 
    private  String account;
    
	/**
	 * 密码
	 */	 
    private  String pwd;
    
	/**
	 * 昵称
	 */	 
    private  String nickName;
    
	/**
	 * 头像
	 */	 
    private  String headImg;
    
	/**
	 * 手机号
	 */	 
    private  String phone;
    
	/**
	 * 姓名
	 */	 
    private  String userName;
    
	/**
	 * 身份证
	 */	 
    private  String identityCard;
    
	/**
	 * 身份证照片
	 */	 
    private  String identityCardPhoto;
    
	/**
	 * 省
	 */	 
    private  String provice;
    
	/**
	 * 市
	 */	 
    private  String city;
    
	/**
	 * 联系电话
	 */	 
    private  String tel;
    
	/**
	 * 邮箱
	 */	 
    private  String email;
    
	/**
	 * 状态(0:未提交审核，1：待审核，2：审核通过，3：审核不通过)
	 */	 
    private  Integer userStatus;
    
	/**
	 * 设备id号
	 */	 
    private  String deviceId;
    
	/**
	 * 极光唯一注册ID
	 */	 
    private  String jpushRegistrationId;

	/**
	 * profit流水号
	 */
	private String profitSn;
	/**
	 *  可提现金额
	 */
	private BigDecimal withdrawAmount;

	/**
	 * 累计提现金额
	 */
	private BigDecimal sumWithdrawAmount;

	/**
	 *最近提现金额
	 */
	private BigDecimal latelyWithdrawAmount;

	/**
	 *最近提现日期
	 */
	private Date withdrawDate;

	/**
	 *资金主体外码
	 */
    private String ownerFcode;
    
    private String ownerCode;
     
    private String remark;
     
    private Integer settlementType;

	/**
	 * 银行卡号
	 */
	private String userBankCart;

	/**
	 * 是否实名认证
	 */
	private Integer certificationFlag;

	public Integer getCertificationFlag() {
		return certificationFlag;
	}

	public void setCertificationFlag(Integer certificationFlag) {
		this.certificationFlag = certificationFlag;
	}

	public String getProfitSn() {
		return profitSn;
	}

	public void setProfitSn(String profitSn) {
		this.profitSn = profitSn;
	}

	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public BigDecimal getSumWithdrawAmount() {
		return sumWithdrawAmount;
	}

	public void setSumWithdrawAmount(BigDecimal sumWithdrawAmount) {
		this.sumWithdrawAmount = sumWithdrawAmount;
	}

	public BigDecimal getLatelyWithdrawAmount() {
		return latelyWithdrawAmount;
	}

	public void setLatelyWithdrawAmount(BigDecimal latelyWithdrawAmount) {
		this.latelyWithdrawAmount = latelyWithdrawAmount;
	}

	public Date getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	public String getOwnerFcode() {
		return ownerFcode;
	}

	public void setOwnerFcode(String ownerFcode) {
		this.ownerFcode = ownerFcode;
	}

	public String getAccount() {
		return this.account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
		
	public String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
		
	public String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
		
	public String getHeadImg() {
		return this.headImg;
	}
	
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
		
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
		
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
		
	public String getIdentityCard() {
		return this.identityCard;
	}
	
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
		
	public String getIdentityCardPhoto() {
		return this.identityCardPhoto;
	}
	
	public void setIdentityCardPhoto(String identityCardPhoto) {
		this.identityCardPhoto = identityCardPhoto;
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
		
	public String getTel() {
		return this.tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
		
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
		
	public Integer getUserStatus() {
		return this.userStatus;
	}
	
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
		
	public String getDeviceId() {
		return this.deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
		
	public String getJpushRegistrationId() {
		return this.jpushRegistrationId;
	}
	
	public void setJpushRegistrationId(String jpushRegistrationId) {
		this.jpushRegistrationId = jpushRegistrationId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(Integer settlementType) {
		this.settlementType = settlementType;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getUserBankCart() {
		return userBankCart;
	}

	public void setUserBankCart(String userBankCart) {
		this.userBankCart = userBankCart;
	}
}
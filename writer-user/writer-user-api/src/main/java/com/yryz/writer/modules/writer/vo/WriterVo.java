package com.yryz.writer.modules.writer.vo;

import java.io.Serializable;
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
public class WriterVo implements Serializable{
	
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
		
}
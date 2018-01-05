package com.yryz.writer.modules.writer.vo;
import java.io.Serializable;
import java.util.Date;

import com.yryz.writer.modules.writer.entity.WriterAudit;

/**
 * @ClassName: WriterAuditVo
 * @Description: WriterAuditVo
 * @author liuyanjun
 * @date 2018-01-04 09:51:21
 *
 */
public class WriterAuditVo implements Serializable {
	
	private Long kid;

	/**
	 * 写手表kid
	 */	 
    private  Long writerKid;
    

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
    
    private  String proviceName;
    

    private  String cityName;
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
	 * 审核状态(1：待审核，2：审核通过，3：审核不通过)
	 */	 
    private  Integer auditStatus;
    

	/**
	 * 备注
	 */	 
    private  String reamrk;
    

	/**
	 * 申请时间
	 */	 
    private Date applyDate;
    
    private String applyDateStr;
    

	/**
	 * 审核时间
	 */	 
    private Date auditDate;
    
    private String auditDateStr;
    
    private Date registerDate;
    
    private String registerDateStr;
    
    private String phone;
    
	public Long getKid() {
		return kid;
	}


	public void setKid(Long kid) {
		this.kid = kid;
	}


	public Long getWriterKid() {
		return writerKid;
	}


	public void setWriterKid(Long writerKid) {
		this.writerKid = writerKid;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getIdentityCard() {
		return identityCard;
	}


	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}


	public String getIdentityCardPhoto() {
		return identityCardPhoto;
	}


	public void setIdentityCardPhoto(String identityCardPhoto) {
		this.identityCardPhoto = identityCardPhoto;
	}


	public String getProvice() {
		return provice;
	}


	public void setProvice(String provice) {
		this.provice = provice;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getAuditStatus() {
		return auditStatus;
	}


	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}


	public String getReamrk() {
		return reamrk;
	}


	public void setReamrk(String reamrk) {
		this.reamrk = reamrk;
	}


	public Date getApplyDate() {
		return applyDate;
	}


	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}


	public String getApplyDateStr() {
		return applyDateStr;
	}


	public void setApplyDateStr(String applyDateStr) {
		this.applyDateStr = applyDateStr;
	}


	public Date getAuditDate() {
		return auditDate;
	}


	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}


	public String getAuditDateStr() {
		return auditDateStr;
	}


	public void setAuditDateStr(String auditDateStr) {
		this.auditDateStr = auditDateStr;
	}


	public String getProviceName() {
		return proviceName;
	}


	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public Date getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}


	public String getRegisterDateStr() {
		return registerDateStr;
	}


	public void setRegisterDateStr(String registerDateStr) {
		this.registerDateStr = registerDateStr;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	

}

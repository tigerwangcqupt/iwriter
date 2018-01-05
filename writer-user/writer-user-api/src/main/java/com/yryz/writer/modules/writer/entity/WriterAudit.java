package com.yryz.writer.modules.writer.entity;

import com.yryz.common.entity.GenericEntity;
import java.util.Date;
/**
 * 
  * @ClassName: WriterAudit
  * @Description: 写手审核历史记录表实体类
  * @author liuyanjun
  * @date 2018-01-04 09:51:21
  *
 */
public class WriterAudit extends GenericEntity{
	
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
    private  Date applyDate;
    

	/**
	 * 审核时间
	 */	 
    private  Date auditDate;
    
    private Date registerDate;
    
    private String phone;
    

    public Long getKid() {
		return kid;
	}

	public void setKid(Long kid) {
		this.kid = kid;
	}
	
	public Long getWriterKid() {
		return this.writerKid;
	}
	
	public void setWriterKid(Long writerKid) {
		this.writerKid = writerKid;
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
		
	public Integer getAuditStatus() {
		return this.auditStatus;
	}
	
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
		
	public String getReamrk() {
		return this.reamrk;
	}
	
	public void setReamrk(String reamrk) {
		this.reamrk = reamrk;
	}
		
	public Date getApplyDate() {
		return this.applyDate;
	}
	
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
		
	public Date getAuditDate() {
		return this.auditDate;
	}
	
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
		
}
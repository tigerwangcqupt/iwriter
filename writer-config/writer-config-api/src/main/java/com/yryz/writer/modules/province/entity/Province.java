package com.yryz.writer.modules.province.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yryz.common.entity.GenericEntity;
/**
 * 
  * @ClassName: Province
  * @Description: 实体类
  * @author wangsenyong
  * @date 2017-09-20 10:48:53
  *
 */
public class Province extends GenericEntity{
	
	/**
	 * 
	 */	 
    private  String provinceCode;
    
	/**
	 * 
	 */	 
    private  String provinceName;
    

	public String getProvinceCode() {
		return this.provinceCode;
	}
	
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
		
	public String getProvinceName() {
		return this.provinceName;
	}
	
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
		
}
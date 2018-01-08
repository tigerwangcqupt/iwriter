package com.yryz.writer.modules.city.entity;

import com.yryz.writer.common.entity.GenericEntity;
/**
 * 
  * @ClassName: City
  * @Description: 实体类
  * @author wangsenyong
  * @date 2017-09-20 10:53:48
  *
 */
public class City extends GenericEntity{
	
	/**
	 * 
	 */	 
    private  String cityCode;
    
	/**
	 * 
	 */	 
    private  String cityName;
    
	/**
	 * 
	 */	 
    private  String parentId;
    

	public String getCityCode() {
		return this.cityCode;
	}
	
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
		
	public String getCityName() {
		return this.cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
		
	public String getParentId() {
		return this.parentId;
	}
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
		
}
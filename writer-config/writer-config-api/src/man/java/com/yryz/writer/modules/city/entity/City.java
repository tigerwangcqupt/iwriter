/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * 
 * Created on 2018年1月2日
 * Id: City.java, 2018年1月2日 上午10:41:24 KF
 */
package com.yryz.writer.modules.city.entity;

import com.yryz.common.entity.GenericEntity;

/**
 * @author liuyanjun
 * @version 1.0
 * @date 2018年1月2日 上午10:41:24
 * @Description TODO (这里用一句话描述这个方法的作用)
 */
public class City extends GenericEntity {
	
	private String cityCode;
	
	private String cityName;
	
	private String parentId;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}

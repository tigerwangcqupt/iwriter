package com.yryz.writer.modules.city.vo;

import java.io.Serializable;

public class CityVo implements Serializable{

	private static final long serialVersionUID = 467074369119844010L;

	private Long id;

    private  String cityCode;
     
    private  String cityName;
     
    private  String parentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

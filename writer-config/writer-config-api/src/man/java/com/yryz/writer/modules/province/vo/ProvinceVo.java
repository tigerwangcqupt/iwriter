package com.yryz.writer.modules.province.vo;

import java.io.Serializable;

public class ProvinceVo implements Serializable{

	private static final long serialVersionUID = 8918193222344358710L;

	private  Long id;
	 
    private  String provinceCode;
     
    private  String provinceName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
    
    
}

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * 
 * Created on 2018年1月2日
 * Id: Province.java, 2018年1月2日 上午10:29:48 KF
 */
package com.yryz.writer.modules.province.entity;

import com.yryz.common.entity.GenericEntity;

/**
 * @author liuyanjun
 * @version 1.0
 * @date 2018年1月2日 上午10:29:48
 * @Description TODO (这里用一句话描述这个方法的作用)
 */
public class Province extends GenericEntity {
		
	private String provinceCode;
	
	private String provinceName;


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

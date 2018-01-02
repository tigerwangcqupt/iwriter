/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * 
 * Created on 2018年1月2日
 * Id: ConfigApi.java, 2018年1月2日 上午10:45:00 KF
 */
package com.yryz.writer.modules.province;

import java.util.List;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.province.dto.ProvinceDto;
import com.yryz.writer.modules.province.entity.Province;
import com.yryz.writer.modules.province.vo.ProvinceVo;

/**
 * @author liuyanjun
 * @version 1.0
 * @date 2018年1月2日 上午10:45:00
 * @Description TODO (这里用一句话描述这个方法的作用)
 */
public interface ProvinceApi {
	
	
	public List<ProvinceVo> queryAllProvinces() throws ServiceException;
	

}

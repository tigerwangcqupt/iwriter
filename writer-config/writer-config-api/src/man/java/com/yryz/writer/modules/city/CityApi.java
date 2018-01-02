/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * 
 * Created on 2018年1月2日
 * Id: CityApi.java, 2018年1月2日 上午11:55:37 KF
 */
package com.yryz.writer.modules.city;

import java.util.List;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.city.dto.CityDto;
import com.yryz.writer.modules.city.entity.City;
import com.yryz.writer.modules.city.vo.CityVo;

/**
 * @author KF
 * @version 1.0
 * @date 2018年1月2日 上午11:55:37
 * @Description TODO (这里用一句话描述这个方法的作用)
 */
public interface CityApi {
	
	
	/**
	 * 根据省id查询市列表
	 * @param provinceCode
	 * @return
	 */
	List<CityVo> selectCitysByPid(String provinceCode);

}

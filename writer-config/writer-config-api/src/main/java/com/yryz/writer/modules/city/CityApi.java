package com.yryz.writer.modules.city;

import java.util.List;

import com.yryz.writer.modules.city.vo.CityVo;



/**
 * 
 * @ClassName: CityApi
 * @Description: CityApi接口
 * @author wangsenyong
 * @date 2017-09-20 10:53:48
 *
 */
public interface CityApi {

	
	/**
	 * 根据省id查询市列表
	 * @param provinceCode
	 * @return
	 */
	List<CityVo> selectCitysByPid(String provinceCode);

}

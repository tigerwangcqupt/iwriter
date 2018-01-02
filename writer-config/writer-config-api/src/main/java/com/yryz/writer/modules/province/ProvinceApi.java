package com.yryz.writer.modules.province;

import java.util.List;

import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.province.vo.ProvinceVo;


/**
 * 
 * @ClassName: ProvinceApi
 * @Description: ProvinceApi接口
 * @author wangsenyong
 * @date 2017-09-20 10:48:53
 *
 */
public interface ProvinceApi {

	/**
	 * 查询Province列表
	 * 
	 * @return
	 */
	public List<ProvinceVo> queryAllProvinces() throws ServiceException;

}

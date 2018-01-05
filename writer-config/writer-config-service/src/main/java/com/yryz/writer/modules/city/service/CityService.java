package com.yryz.writer.modules.city.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.city.dao.persistence.CityDao;
import com.yryz.writer.modules.city.entity.City;
import com.yryz.writer.modules.city.vo.CityVo;


/**
 * 
 * @ClassName: CityProvider
 * @Description: CityProvider业务实现类
 * @author wangsenyong
 * @date 2017-09-20 10:53:48
 *
 */
@Service
public class CityService {
	
	private static final Logger logger = LoggerFactory.getLogger(CityService.class);

	@Autowired
	private CityDao cityDao;


	public List<CityVo> selectCitysByPid(String provinceCode) {
		return cityDao.selectCitysByPid(provinceCode);
	}
	
	public CityVo selectCity(String cityCode){
		City city = cityDao.selectCity(cityCode);
		CityVo cityVo = new CityVo();
		if(city!=null){
			BeanUtils.copyProperties(city, cityVo);
		}
		return cityVo;
	}


}

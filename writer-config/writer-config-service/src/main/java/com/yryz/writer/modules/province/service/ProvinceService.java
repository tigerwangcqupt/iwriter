package com.yryz.writer.modules.province.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.province.dao.persistence.ProvinceDao;
import com.yryz.writer.modules.province.dto.ProvinceDto;
import com.yryz.writer.modules.province.entity.Province;
import com.yryz.writer.modules.province.vo.ProvinceVo;


/**
 * 
 * @ClassName: ProvinceService
 * @Description: ProvinceService业务实现类
 * @author wangsenyong
 * @date 2017-09-20 10:48:53
 *
 */
@Service
public class ProvinceService  {
	
	private static final Logger logger = LoggerFactory.getLogger(ProvinceService.class);

	@Autowired
	private ProvinceDao provinceDao;

	public List<ProvinceVo> queryAllProvinces() throws ServiceException {
		return provinceDao.queryAllProvinces();
	}


}

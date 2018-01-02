package com.yryz.writer.modules.province.provider;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.province.ProvinceApi;
import com.yryz.writer.modules.province.service.ProvinceService;
import com.yryz.writer.modules.province.vo.ProvinceVo;

/**
 * 
 * @ClassName: ProvinceProvider
 * @Description: ProvinceProvider业务实现类
 * @author wangsenyong
 * @date 2017-09-20 10:48:53
 *
 */
@Service
public class ProvinceProvider implements ProvinceApi {
	private static final Logger logger = LoggerFactory.getLogger(ProvinceProvider.class);

	@Autowired
	private ProvinceService provinceService;


	public List<ProvinceVo> queryAllProvinces() throws ServiceException {
		return provinceService.queryAllProvinces();
	}


}

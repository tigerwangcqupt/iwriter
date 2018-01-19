package com.yryz.writer.modules.city.provider;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.city.CityApi;
import com.yryz.writer.modules.city.entity.City;
import com.yryz.writer.modules.city.service.CityService;
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
public class CityProvider implements CityApi {

	@Autowired
	private CityService cityService;

	@Override
	public RpcResponse<List<CityVo>> selectCitysByPid(String provinceCode) {
		return  ResponseModel.returnListSuccess(cityService.selectCitysByPid(provinceCode));
	}

	@Override
	public RpcResponse<CityVo> selectCity(String cityCode) {
		return ResponseModel.returnObjectSuccess(cityService.selectCity(cityCode));
	}

	

}

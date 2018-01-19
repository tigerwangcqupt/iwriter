package com.yryz.openapi.area.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.writer.common.Annotation.NotLogin;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.modules.city.CityApi;
import com.yryz.writer.modules.city.vo.CityVo;
import com.yryz.writer.modules.province.ProvinceApi;
import com.yryz.writer.modules.province.vo.ProvinceVo;



@Controller
@RequestMapping("services/app/v1/area")
public class AreaController extends BaseController {

    @Autowired
    private ProvinceApi provinceApi;
    
    @Autowired
    private CityApi cityApi;
    
   
	@RequestMapping(value="listProvinces",method = RequestMethod.GET)
	@ResponseBody
	@NotLogin
    public RpcResponse<List<ProvinceVo>> listProvinces() {
		return provinceApi.queryAllProvinces();
    }
	

	@RequestMapping(value="listCitys",method = RequestMethod.GET)
	@ResponseBody
	@NotLogin
    public RpcResponse<List<CityVo>> listCitys(String provinceCode) {
		return cityApi.selectCitysByPid(provinceCode);
    }
	
   

}

/*package com.yryz.openapi.area.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yryz.common.Annotation.NotLogin;
import com.yryz.common.web.BaseController;
import com.yryz.writer.modules.city.CityApi;
import com.yryz.writer.modules.city.vo.CityVo;
import com.yryz.writer.modules.province.ProvinceApi;
import com.yryz.writer.modules.province.vo.ProvinceVo;



@Controller
@RequestMapping("services/app/v1/area")
public class AreaController extends BaseController {

    @Autowired
    ProvinceApi provinceApi;
    
    @Autowired
    CityApi cityApi;
    
    *//**
     * 查询省份列表
     *//*
	@RequestMapping(value="listProvinces",method = RequestMethod.GET)
	@ResponseBody
	@NotLogin
    public List<ProvinceVo> listProvinces() {
		return provinceApi.queryAllProvinces();
    }
	
    *//**
     * 查询城市列表
     *//*
	@RequestMapping(value="listCitys",method = RequestMethod.GET)
	@ResponseBody
	@NotLogin
    public List<CityVo> listCitys(String provinceCode) {
		return cityApi.selectCitysByPid(provinceCode);
    }
	
   

}
*/
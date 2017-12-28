package com.yryz.writer.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.demo.api.demo.TestDemo;
import com.yryz.demo.api.demo.entity.Config;

@Controller
@RequestMapping("services/app/v1/demo")
public class DemoController {
	
	@Autowired
	private TestDemo testDemo;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public RpcResponse<List<Config>> queryConfig() {
		return testDemo.findConfigByName("皇冠活动开发商名额");
	}
}

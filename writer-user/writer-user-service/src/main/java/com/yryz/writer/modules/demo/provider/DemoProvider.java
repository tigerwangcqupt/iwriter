package com.yryz.writer.modules.demo.provider;

import java.util.List;


import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.demo.api.demo.TestDemo;
import com.yryz.demo.api.demo.entity.Config;
import com.yryz.writer.modules.demo.service.TestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoProvider implements TestDemo {

	@Autowired
	private TestDemoService testDemoService;
	
	@Override
	public RpcResponse<List<Config>> findConfigByName(String commentName) {
		return ResponseModel.returnListSuccess(testDemoService.findConfigByName(commentName));
	}
}

package com.yryz.demo.api.demo;

import java.util.List;


import com.yryz.component.rpc.RpcResponse;
import com.yryz.demo.api.demo.entity.Config;
import org.springframework.stereotype.Service;



public interface TestDemo {
	public RpcResponse<List<Config>> findConfigByName(String commentName);
}
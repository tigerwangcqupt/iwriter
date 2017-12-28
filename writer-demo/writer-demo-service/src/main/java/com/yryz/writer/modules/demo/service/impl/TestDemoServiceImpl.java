package com.yryz.writer.modules.demo.service.impl;

import java.util.List;


import com.yryz.demo.api.demo.entity.Config;
import com.yryz.writer.modules.demo.dao.persistence.DemoDao;
import com.yryz.writer.modules.demo.service.TestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDemoServiceImpl implements TestDemoService {

	@Autowired
	private DemoDao demoDao;
	
	@Override
	public List<Config> findConfigByName(String commentName) {
		return demoDao.findConfigByName(commentName);
	}
}

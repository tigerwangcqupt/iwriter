package com.yryz.writer.modules.demo.service;

import java.util.List;

import com.yryz.demo.api.demo.entity.Config;

public interface TestDemoService {
   public List<Config> findConfigByName(String commentName);
}

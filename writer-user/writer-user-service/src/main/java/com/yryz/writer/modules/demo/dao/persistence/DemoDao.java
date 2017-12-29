package com.yryz.writer.modules.demo.dao.persistence;

import java.util.List;



import com.yryz.demo.api.demo.entity.Config;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoDao {
	public List<Config> findConfigByName(String commentName);
}

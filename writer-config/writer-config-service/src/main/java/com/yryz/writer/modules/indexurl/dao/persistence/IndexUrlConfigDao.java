package com.yryz.writer.modules.indexurl.dao.persistence;

import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.indexurl.entity.IndexUrlConfig;
import com.yryz.writer.modules.indexurl.dto.IndexUrlConfigDto;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: IndexUrlConfigDao
  * @Description: IndexUrlConfig数据访问接口
  * @author wangsenyong
  * @date 2018-03-29 15:11:09
  *
 */
@Repository
public interface IndexUrlConfigDao extends BaseDao {

    List<IndexUrlConfig> selectList(IndexUrlConfigDto indexUrlConfigDto);

}
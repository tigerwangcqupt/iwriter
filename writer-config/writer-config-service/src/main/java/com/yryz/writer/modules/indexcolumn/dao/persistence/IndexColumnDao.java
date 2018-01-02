package com.yryz.writer.modules.indexcolumn.dao.persistence;

import com.yryz.writer.modules.indexcolumn.entity.IndexColumn;
import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: IndexColumnDao
  * @Description: IndexColumn数据访问接口
  * @author huyangyang
  * @date 2018-01-02 10:04:46
  *
 */
@Repository
public interface IndexColumnDao extends BaseDao {

    /**
     * 查询首页栏目列表
     * @param indexColumnDto
     * @return
     */
    List<IndexColumn> selectList(IndexColumnDto indexColumnDto);

}
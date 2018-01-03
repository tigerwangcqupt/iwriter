package com.yryz.writer.modules.writer.dao;

import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.dto.WriterDto;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: WriterDao
  * @Description: Writer数据访问接口
  * @author liuyanjun
  * @date 2018-01-03 10:45:53
  *
 */
@Repository
public interface WriterDao {

    List<Writer> selectList(WriterDto writerDto);

    Integer deleteByPrimaryKey(Long id);

    Integer insert(Writer writer);
    
 	Integer insertByPrimaryKeySelective(Writer writer);
 
    Writer selectByPrimaryKey(Long id);

    Integer updateByPrimaryKeySelective(Writer writer);
}
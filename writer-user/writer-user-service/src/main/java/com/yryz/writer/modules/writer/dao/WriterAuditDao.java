package com.yryz.writer.modules.writer.dao;

import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: WriterAuditDao
  * @Description: WriterAudit数据访问接口
  * @author liuyanjun
  * @date 2018-01-03 11:25:37
  *
 */
@Repository
public interface WriterAuditDao {

    List<WriterAudit> selectList(WriterAuditDto writerAuditDto);

    Integer deleteByPrimaryKey(Long id);

    Integer insert(WriterAudit writerAudit);
    
 	Integer insertByPrimaryKeySelective(WriterAudit writerAudit);
 
    WriterAudit selectByPrimaryKey(Long id);

    Integer updateByPrimaryKeySelective(WriterAudit writerAudit);
}
package com.yryz.writer.modules.writer.dao.persistence;

import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.common.dao.BaseDao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: WriterAuditDao
  * @Description: WriterAudit数据访问接口
  * @author liuyanjun
  * @date 2018-01-04 09:51:21
  *
 */
@Repository
public interface WriterAuditDao extends BaseDao {

    List<WriterAudit> selectList(WriterAuditDto writerAuditDto);
    
    WriterAudit selectAuditDetail(@Param("writerKid")Long writerKid);
    
    int insertByPrimaryKeySelective(WriterAudit writerAudit);
    

}
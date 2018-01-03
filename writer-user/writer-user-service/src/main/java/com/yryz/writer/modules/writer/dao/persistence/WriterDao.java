package com.yryz.writer.modules.writer.dao.persistence;

import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: WriterDao
  * @Description: Writer数据访问接口
  * @author liuyanjun
  * @date 2018-01-03 15:03:10
  *
 */
@Repository
public interface WriterDao extends BaseDao {

    List<Writer> selectList(WriterDto writerDto);

}
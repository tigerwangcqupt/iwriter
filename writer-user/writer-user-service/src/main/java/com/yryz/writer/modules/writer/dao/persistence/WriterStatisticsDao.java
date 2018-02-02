package com.yryz.writer.modules.writer.dao.persistence;

import com.yryz.writer.modules.writer.entity.WriterStatistics;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.modules.writer.dto.WriterStatisticsDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
  * @ClassName: WriterStatisticsDao
  * @Description: WriterStatistics数据访问接口
  * @author liuyanjun
  * @date 2018-02-02 14:15:21
  *
 */
@Repository
public interface WriterStatisticsDao extends BaseDao {

    List<WriterStatistics> selectList(WriterStatisticsDto writerStatisticsDto);

}
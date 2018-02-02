package com.yryz.writer.modules.writer.service;

import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.writer.dto.WriterStatisticsDto;
import com.yryz.writer.modules.writer.entity.WriterStatistics;
import com.yryz.writer.modules.writer.vo.WriterStatisticsVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: WriterStatisticsService
  * @Description: WriterStatistics业务访问接口
  * @author liuyanjun
  * @date 2018-02-02 14:15:21
  *
 */
@Repository
public interface WriterStatisticsService extends BaseService {

   PageList<WriterStatisticsVo> selectList(WriterStatisticsDto writerStatisticsDto);

   WriterStatisticsVo detail(Long kid);
   
   WriterStatistics insertWriterStatistics(WriterStatistics writerStatistics);
   
   WriterStatistics updateWriterStatistics(WriterStatistics writerStatistics);

}
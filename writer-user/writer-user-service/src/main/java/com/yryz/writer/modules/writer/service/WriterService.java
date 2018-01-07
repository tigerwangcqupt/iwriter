package com.yryz.writer.modules.writer.service;

import com.yryz.writer.common.service.BaseService;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.vo.WriterVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: WriterService
  * @Description: Writer业务访问接口
  * @author liuyanjun
  * @date 2018-01-03 15:03:10
  *
 */
@Repository
public interface WriterService extends BaseService {

   PageList<WriterVo> selectList(WriterDto writerDto);

   WriterVo detail(Long writerId);

}
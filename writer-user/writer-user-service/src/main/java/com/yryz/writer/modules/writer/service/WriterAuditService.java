package com.yryz.writer.modules.writer.service;

import com.yryz.common.service.BaseService;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.vo.WriterAuditVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: WriterAuditService
  * @Description: WriterAudit业务访问接口
  * @author liuyanjun
  * @date 2018-01-04 09:51:21
  *
 */
@Repository
public interface WriterAuditService extends BaseService {

   PageList<WriterAuditVo> selectList(WriterAuditDto writerAuditDto);

   WriterAuditVo detail(Long writerAuditId);

}
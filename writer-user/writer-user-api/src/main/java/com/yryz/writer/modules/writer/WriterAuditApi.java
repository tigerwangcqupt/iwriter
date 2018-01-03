package com.yryz.writer.modules.writer;

import java.math.BigDecimal;

import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.writer.entity.WriterAudit;

/**
*
* @ClassName: WriterAuditService
* @Description: WriterAuditService接口
* @author liuyanjun
* @date 2018-01-03 11:25:37
*
*/
public interface WriterAuditApi {

/**
* WriterAudit列表
*
* @return
*/
//public PageList<WriterAudit> list(WriterAuditDto writerAuditDto) throws ServiceException;

/**
* 删除WriterAudit
*
* @param id
* @return
*/
Integer delete(Long id) throws ServiceException;

/**
* 新增WriterAudit
*
* @param writerAudit
* @return
*/
Integer insert(WriterAudit writerAudit) throws ServiceException;

/**
* 查询WriterAudit信息
*
* @param id
* @return
*/
WriterAudit detail(Long id) throws ServiceException;

/**
* WriterAudit更新
*
* @param writerAudit
* @return
*/
Integer update(WriterAudit writerAudit) throws ServiceException;

}

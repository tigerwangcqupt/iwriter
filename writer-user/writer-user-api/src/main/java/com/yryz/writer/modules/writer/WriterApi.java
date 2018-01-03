package com.yryz.writer.modules.writer;

import java.math.BigDecimal;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;

/**
*
* @ClassName: WriterService
* @Description: WriterService接口
* @author liuyanjun
* @date 2018-01-03 10:45:53
*
*/
public interface WriterApi {

/**
* Writer列表
*
* @return
*/
//public PageList<Writer> list(WriterDto writerDto) throws ServiceException;

/**
* 删除Writer
*
* @param id
* @return
*/
Integer delete(Long id) throws ServiceException;

/**
* 新增Writer
*
* @param writer
* @return
*/
Integer insert(Writer writer) throws ServiceException;

/**
* 查询Writer信息
*
* @param id
* @return
*/
RpcResponse<Writer> detail(Long id) throws ServiceException;

/**
* Writer更新
*
* @param writer
* @return
*/
Integer update(Writer writer) throws ServiceException;

}

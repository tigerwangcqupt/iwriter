package com.yryz.writer.modules.writer.provider;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.writer.WriterApi;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.service.WriterService;


/**
 * 
 * @ClassName: WriterProvider
 * @Description: WriterProvider接口提供者
 * @author liuyanjun
 * @date 2018-01-03 10:45:53
 *
 */
@Service
public class WriterProvider implements WriterApi {
	
	private static final Logger logger = LoggerFactory.getLogger(WriterProvider.class);

	@Autowired
	private WriterService writerService;

//	@Override
//	public PageList<Writer> list(WriterDto writerDto) throws ServiceException {
//		return writerService.list(writerDto);
//	}

	@Override
	public Integer delete(Long id) throws ServiceException {
		return writerService.delete(id);
	}

	@Override
	public Integer insert(Writer writer) throws ServiceException {
		return writerService.insert(writer);
	}

	
	@Override
	public RpcResponse<Writer> detail(Long id) throws ServiceException {
		return ResponseModel.returnObjectSuccess(writerService.detail(id));
	}
	
	@Override
	public Integer update(Writer record) throws ServiceException {
		return writerService.update(record);
	}


}

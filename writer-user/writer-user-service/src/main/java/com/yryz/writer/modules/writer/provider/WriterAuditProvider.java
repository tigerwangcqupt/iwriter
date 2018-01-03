package com.yryz.writer.modules.writer.provider;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.yryz.writer.modules.writer.service.WriterAuditService;
import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.writer.WriterAuditApi;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.writer.entity.WriterAudit;


/**
 * 
 * @ClassName: WriterAuditProvider
 * @Description: WriterAuditProvider接口提供者
 * @author liuyanjun
 * @date 2018-01-03 11:25:37
 *
 */
@Service
public class WriterAuditProvider implements WriterAuditApi {
	
	private static final Logger logger = LoggerFactory.getLogger(WriterAuditProvider.class);

	@Autowired
	private WriterAuditService writerAuditService;

//	@Override
//	public PageList<WriterAudit> list(WriterAuditDto writerAuditDto) throws ServiceException {
//		return writerAuditService.list(writerAuditDto);
//	}

	@Override
	public Integer delete(Long id) throws ServiceException {
		return writerAuditService.delete(id);
	}

	@Override
	public Integer insert(WriterAudit writerAudit) throws ServiceException {
		return writerAuditService.insert(writerAudit);
	}

	
	@Override
	public WriterAudit detail(Long id) throws ServiceException {
		return writerAuditService.detail(id);
	}
	
	@Override
	public Integer update(WriterAudit record) throws ServiceException {
		return writerAuditService.update(record);
	}


}

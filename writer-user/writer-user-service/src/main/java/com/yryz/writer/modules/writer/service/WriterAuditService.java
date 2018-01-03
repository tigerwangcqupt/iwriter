package com.yryz.writer.modules.writer.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.writer.dao.WriterAuditDao;

/**
 * 
 * @ClassName: WriterAuditService
 * @Description: WriterAuditService业务实现类
 * @author liuyanjun
 * @date 2018-01-03 11:25:37
 *
 */
@Service
public class WriterAuditService{
	
	private static final Logger logger = LoggerFactory.getLogger(WriterAuditService.class);

	@Autowired
	private WriterAuditDao writerAuditDao;

	
//	public PageList<WriterAudit> list(WriterAuditDto writerAuditDto) throws ServiceException {
//		PageHelper.startPage(writerAuditDto.getPageNo(), writerAuditDto.getPageSize());
//		List<WriterAudit> writerAudit = null;
//		try {
//			writerAudit = writerAuditDao.selectList(writerAuditDto);
//		} catch (Exception e) {
//			logger.error("查询WriterAudit列表信息失败！,writerAuditDto:", e);
//			throw ServiceException.busiError("查询WriterAudit列表信息失败！");
//		}
//		PageList<WriterAudit> pageEntity = new PageList<WriterAudit>(writerAudit);
//		return pageEntity;
//	}

	public Integer delete(Long id) throws ServiceException {
		if(id==null){
			logger.error("WriterAuditID不能为空！,id:"+id);
			throw ServiceException.paramsError("WriterAuditID不能为空！",String.valueOf(id));
		}
		
		WriterAudit writerAudit=null;
		writerAudit=writerAuditDao.selectByPrimaryKey(id);
//		try {
//		} catch (Exception e) {
//			logger.error("查询WriterAudit详情失败！,id:"+id + e);
//			throw ServiceException.busiError("查询WriterAudit详情失败！",String.valueOf(id));
//		}
		
		if(writerAudit==null){
			logger.error("WriterAudit不存在！");
			throw ServiceException.paramsError("WriterAudit不存在", String.valueOf(id));
		}
		return writerAuditDao.updateByPrimaryKeySelective(writerAudit);
//		try {
//		} catch (Exception e) {
//			logger.error("删除WriterAudit失败！,id:" + id, e);
//			throw ServiceException.busiError("删除WriterAudit失败！",String.valueOf(id));
//		}
	}

	public Integer insert(WriterAudit writerAudit) throws ServiceException {
		return writerAuditDao.insertByPrimaryKeySelective(writerAudit);
//		try {
//		} catch (Exception e) {
//			logger.error("新增WriterAudit失败！,writerAudit:" + e);
//			throw ServiceException.busiError("新增WriterAudit失败！");
//		}
	}

	public WriterAudit detail(Long id) throws ServiceException {
		if(id==null){
			logger.error("WriterAuditID不能为空！,id:"+id);
			throw ServiceException.paramsError("WriterAuditID不能为空！",String.valueOf(id));
		}
		
		WriterAudit writerAudit=null;
		writerAudit=writerAuditDao.selectByPrimaryKey(id);
//		try {
//		} catch (Exception e) {
//			logger.error("查询WriterAudit详情失败！,id:"+id + e);
//			throw ServiceException.busiError("查询WriterAudit详情失败！",String.valueOf(id));
//		}
		return writerAudit;
	}
	
	public Integer update(WriterAudit record) throws ServiceException {
		if(record==null || record.getId()==null){
			logger.error("不存在该WriterAudit！,id:"+record.getId());
			throw ServiceException.paramsError("不存在该WriterAudit！",String.valueOf(record.getId()));
		}
		
		WriterAudit writerAudit=null;
		writerAudit=writerAuditDao.selectByPrimaryKey(record.getId());
//		try {
//		} catch (Exception e) {
//			logger.error("查询WriterAudit详情失败！,id:"+record.getId() + e);
//			throw ServiceException.busiError("查询WriterAudit详情失败！",String.valueOf(record.getId()));
//		}
		if(writerAudit==null){
			logger.error("WriterAudit不存在！");
			throw ServiceException.paramsError("WriterAudit不存在", String.valueOf(record.getId()));
		}
		
		
		return writerAuditDao.updateByPrimaryKeySelective(record);
//		try {
//		} catch (Exception e) {
//			logger.error("更新WriterAudit详情失败！,writerAudit:" + e);
//			throw ServiceException.busiError("更新WriterAudit详情失败！",String.valueOf(record.getId()));
//		}
	}

}

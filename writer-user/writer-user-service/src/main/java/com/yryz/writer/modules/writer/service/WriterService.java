package com.yryz.writer.modules.writer.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.service.api.api.exception.ServiceException;
import com.yryz.writer.modules.writer.dao.WriterDao;

/**
 * 
 * @ClassName: WriterService
 * @Description: WriterService业务实现类
 * @author liuyanjun
 * @date 2018-01-03 10:45:53
 *
 */
@Service
public class WriterService{
	private static final Logger logger = LoggerFactory.getLogger(WriterService.class);

	@Autowired
	private WriterDao writerDao;

	
//	public PageList<Writer> list(WriterDto writerDto) throws ServiceException {
//		PageHelper.startPage(writerDto.getPageNo(), writerDto.getPageSize());
//		List<Writer> writer = null;
//		try {
//			writer = writerDao.selectList(writerDto);
//		} catch (Exception e) {
//			logger.error("查询Writer列表信息失败！,writerDto:", e);
//			throw ServiceException.busiError("查询Writer列表信息失败！");
//		}
//		PageList<Writer> pageEntity = new PageList<Writer>(writer);
//		return pageEntity;
//	}

	public Integer delete(Long id) throws ServiceException {
		if(id==null){
			logger.error("WriterID不能为空！,id:"+id);
			throw ServiceException.paramsError("WriterID不能为空！",String.valueOf(id));
		}
		
		Writer writer=null;
		try {
			writer=writerDao.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("查询Writer详情失败！,id:"+id + e);
//			throw ServiceException.busiError("查询Writer详情失败！",String.valueOf(id));
		}
		
		if(writer==null){
			logger.error("Writer不存在！");
			throw ServiceException.paramsError("Writer不存在", String.valueOf(id));
		}
		return writerDao.updateByPrimaryKeySelective(writer);
//		try {
//		} catch (Exception e) {
//			logger.error("删除Writer失败！,id:" + id, e);
//			throw ServiceException.busiError("删除Writer失败！",String.valueOf(id));
//		}
	}

	public Integer insert(Writer writer) throws ServiceException{
		return writerDao.insertByPrimaryKeySelective(writer);
//		try {
//		} catch (Exception e) {
//			logger.error("新增Writer失败！,writer:" + e);
//			throw ServiceException.busiError("新增Writer失败！");
//		}
	}

	public Writer detail(Long id) throws ServiceException {
		if(id==null){
			logger.error("WriterID不能为空！,id:"+id);
			throw ServiceException.paramsError("WriterID不能为空！",String.valueOf(id));
		}
		
		Writer writer=null;
		writer=writerDao.selectByPrimaryKey(id);
//		try {
//		} catch (Exception e) {
//			logger.error("查询Writer详情失败！,id:"+id + e);
//			throw ServiceException.busiError("查询Writer详情失败！",String.valueOf(id));
//		}
		return writer;
	}
	
	public Integer update(Writer record) throws ServiceException {
		if(record==null || record.getId()==null){
			logger.error("不存在该Writer！,id:"+record.getId());
			throw ServiceException.paramsError("不存在该Writer！",String.valueOf(record.getId()));
		}
		
		Writer writer=null;
		writer=writerDao.selectByPrimaryKey(record.getId());
//		try {
//		} catch (Exception e) {
//			logger.error("查询Writer详情失败！,id:"+record.getId() + e);
//			throw ServiceException.busiError("查询Writer详情失败！",String.valueOf(record.getId()));
//		}
		if(writer==null){
			logger.error("Writer不存在！");
			throw ServiceException.paramsError("Writer不存在", String.valueOf(record.getId()));
		}
		
		
		return writerDao.updateByPrimaryKeySelective(record);
//		try {
//		} catch (Exception e) {
//			logger.error("更新Writer详情失败！,writer:" + e);
//			throw ServiceException.busiError("更新Writer详情失败！",String.valueOf(record.getId()));
//		}
	}

}

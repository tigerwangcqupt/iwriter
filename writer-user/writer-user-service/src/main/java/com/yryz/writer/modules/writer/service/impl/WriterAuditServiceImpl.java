package com.yryz.writer.modules.writer.service.impl;

import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yryz.writer.modules.writer.vo.WriterAuditVo;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.writer.dao.persistence.WriterAuditDao;
import com.yryz.writer.modules.writer.dao.persistence.WriterDao;
import com.yryz.writer.modules.writer.service.WriterAuditService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class WriterAuditServiceImpl extends BaseServiceImpl implements WriterAuditService {

    @Autowired
    private WriterAuditDao writerAuditDao;
    
    @Autowired
    private WriterDao writerDao;

    protected BaseDao getDao() {
        return writerAuditDao;
    }

    public PageList<WriterAuditVo> selectList(WriterAuditDto writerAuditDto){
        PageUtils.startPage(writerAuditDto.getCurrentPage(), writerAuditDto.getPageSize());
        List<WriterAudit> list = writerAuditDao.selectList(writerAuditDto);
        List<WriterAuditVo> writerAuditVoList = new ArrayList <WriterAuditVo>();
        if(CollectionUtils.isNotEmpty(list)){
            for(WriterAudit writerAudit : list){
                WriterAuditVo writerAuditVo = new WriterAuditVo();
                BeanUtils.copyProperties(writerAudit, writerAuditVo);
                writerAuditVoList.add(writerAuditVo);
            }
        }
        return new PageModel<WriterAuditVo>().getPageList(writerAuditVoList);
    }
    
    public List<WriterAuditVo> exportList(WriterAuditDto writerAuditDto){
        List<WriterAudit> list = writerAuditDao.selectList(writerAuditDto);
        List<WriterAuditVo> writerAuditVoList = new ArrayList <WriterAuditVo>();
        if(CollectionUtils.isNotEmpty(list)){
            for(WriterAudit writerAudit : list){
                WriterAuditVo writerAuditVo = new WriterAuditVo();
                BeanUtils.copyProperties(writerAudit, writerAuditVo);
                writerAuditVoList.add(writerAuditVo);
            }
        }
        return writerAuditVoList;
    }

    public WriterAuditVo detail(Long kid) {
        WriterAudit writerAudit = writerAuditDao.selectByKid(WriterAudit.class,kid);
        
        WriterAuditVo writerAuditVo = new WriterAuditVo();
        if (writerAudit != null) {
            BeanUtils.copyProperties(writerAudit, writerAuditVo);
        }
        return writerAuditVo;
    }

	@Override
	@Transactional
	public int audit(WriterAuditVo writerAuditVo) {
		WriterAudit writerAudit = new WriterAudit();
		BeanUtils.copyProperties(writerAuditVo, writerAudit);
		if(writerAudit!=null){
			//更新写手审核表
			writerAudit.setAuditDate(new Date());
			writerAudit.setLastUpdateDate(new Date());
			writerAuditDao.update(writerAudit);
			//更新写手表状态
			Writer writer = new Writer();
			writer.setKid(writerAudit.getWriterKid());
			writer.setUserStatus(writerAudit.getAuditStatus());
			writer.setLastUpdateDate(new Date());
			writer.setLastUpdateUserId(writerAuditVo.getLastUpdateUserId());
			return writerDao.update(writer);
		}
		return 0;
	}
    
    
    
    
 }

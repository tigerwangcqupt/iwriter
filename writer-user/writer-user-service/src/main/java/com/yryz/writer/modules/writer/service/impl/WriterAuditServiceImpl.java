package com.yryz.writer.modules.writer.service.impl;

import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.writer.vo.WriterAuditVo;
import com.yryz.writer.modules.writer.entity.WriterAudit;
import com.yryz.writer.modules.writer.dto.WriterAuditDto;
import com.yryz.writer.modules.writer.dao.persistence.WriterAuditDao;
import com.yryz.writer.modules.writer.service.WriterAuditService;
import java.util.ArrayList;
import java.util.List;


@Service
public class WriterAuditServiceImpl extends BaseServiceImpl implements WriterAuditService {

    @Autowired
    private WriterAuditDao writerAuditDao;

    protected BaseDao getDao() {
        return writerAuditDao;
    }

    public PageList<WriterAuditVo> selectList(WriterAuditDto writerAuditDto){
        PageUtils.startPage(writerAuditDto.getCurrentPage(), writerAuditDto.getPageSize());
        List<WriterAudit> list = writerAuditDao.selectList(writerAuditDto);
        List<WriterAuditVo> writerAuditVoList = new ArrayList <WriterAuditVo>();
        if(list != null && list.size() > 0) {
            for(WriterAudit writerAudit : list){
                WriterAuditVo writerAuditVo = new WriterAuditVo();
                //WriterAudit to WriterAuditVo
                writerAuditVoList.add(writerAuditVo);
            }
        }
        return new PageModel<WriterAuditVo>().getPageList(writerAuditVoList);
    }


    public WriterAuditVo detail(Long writerAuditId) {
        WriterAudit writerAudit = writerAuditDao.selectByKid(WriterAudit.class,writerAuditId);
        WriterAuditVo writerAuditVo = new WriterAuditVo();
        if (writerAuditVo != null) {
            //WriterAudit to WriterAuditVo
        }
        return writerAuditVo;
    }
 }

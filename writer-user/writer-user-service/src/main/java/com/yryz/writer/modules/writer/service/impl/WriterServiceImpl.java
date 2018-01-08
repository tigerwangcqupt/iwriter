package com.yryz.writer.modules.writer.service.impl;

import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.writer.vo.WriterAdminVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.writer.vo.WriterVo;
import com.yryz.writer.modules.writer.entity.Writer;
import com.yryz.writer.modules.writer.dao.persistence.WriterDao;
import com.yryz.writer.modules.writer.dto.WriterDto;
import com.yryz.writer.modules.writer.service.WriterService;
import java.util.ArrayList;
import java.util.List;


@Service
public class WriterServiceImpl extends BaseServiceImpl implements WriterService {

    @Autowired
    private WriterDao writerDao;

    protected BaseDao getDao() {
        return writerDao;
    }

    public PageList<WriterVo> selectList(WriterDto writerDto){
        PageUtils.startPage(writerDto.getCurrentPage(), writerDto.getPageSize());
        List<Writer> list = writerDao.selectList(writerDto);
        List<WriterVo> writerVoList = new ArrayList <WriterVo>();
        if(list != null && list.size() > 0) {
            for(Writer writer : list){
                WriterVo writerVo = new WriterVo();
                //Writer to WriterVo
                writerVoList.add(writerVo);
            }
        }
        return new PageModel<WriterVo>().getPageList(writerVoList);
    }


    public WriterVo detail(Long writerId) {
        Writer writer = writerDao.selectByKid(Writer.class,writerId);
        WriterVo writerVo = new WriterVo();
        if (writerVo != null) {
        	BeanUtils.copyProperties(writer, writerVo);
        }
        return writerVo;
    }

    @Override
    public PageList<WriterAdminVo> selectListAdmin(WriterDto writerDto) {
        PageUtils.startPage(writerDto.getCurrentPage(), writerDto.getPageSize());
        List<Writer> list = writerDao.selectAdminList(writerDto);
        List<WriterAdminVo> writerVoList = new ArrayList <WriterAdminVo>();
        if(list != null && list.size() > 0) {
            for(Writer writer : list){
                WriterAdminVo writerVo = new WriterAdminVo();
                //Writer to WriterVo
                BeanUtils.copyProperties(writer,writerVo);
                writerVoList.add(writerVo);
            }
        }
        return new PageModel<WriterAdminVo>().getPageList(writerVoList);
    }

    public Writer selectByPhone(String phone) {
        Writer writer = writerDao.selectByPhone(phone);
        return writer;
    }
}

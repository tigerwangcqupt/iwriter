package com.yryz.writer.modules.writer.service.impl;

import com.github.pagehelper.PageInfo;
import com.yryz.component.rpc.dto.PageList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.writer.vo.WriterStatisticsVo;
import com.yryz.writer.modules.writer.entity.WriterStatistics;
import com.yryz.writer.modules.writer.dto.WriterStatisticsDto;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.bank.constant.BankConstant;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.writer.dao.persistence.WriterStatisticsDao;
import com.yryz.writer.modules.writer.service.WriterStatisticsService;
import java.util.ArrayList;
import java.util.List;


@Service
public class WriterStatisticsServiceImpl extends BaseServiceImpl implements WriterStatisticsService {

    @Autowired
    private WriterStatisticsDao writerStatisticsDao;

    @Autowired
    private IdAPI idAPI;
    
    
    protected BaseDao getDao() {
        return writerStatisticsDao;
    }

    public PageList<WriterStatisticsVo> selectList(WriterStatisticsDto writerStatisticsDto){
        PageUtils.startPage(writerStatisticsDto.getCurrentPage(), writerStatisticsDto.getPageSize());
        List<WriterStatistics> list = writerStatisticsDao.selectList(writerStatisticsDto);
        List<WriterStatisticsVo> writerStatisticsVoList = new ArrayList <WriterStatisticsVo>();
        if(list != null && list.size() > 0) {
            for(WriterStatistics writerStatistics : list){
                WriterStatisticsVo writerStatisticsVo = new WriterStatisticsVo();
                BeanUtils.copyProperties(writerStatistics, writerStatisticsVo);
                writerStatisticsVoList.add(writerStatisticsVo);
            }
        }
        return new PageModel<WriterStatisticsVo>().getPageList(writerStatisticsVoList);
    }


    public WriterStatisticsVo detail(Long kid) {
        WriterStatistics writerStatistics = writerStatisticsDao.selectByKid(WriterStatistics.class,kid);
        WriterStatisticsVo writerStatisticsVo = new WriterStatisticsVo();
        if (writerStatistics != null) {
        	BeanUtils.copyProperties(writerStatistics, writerStatisticsVo);
        }
        return writerStatisticsVo;
    }

	@Override
	public WriterStatistics insertWriterStatistics(WriterStatistics writerStatistics) {
		Long kid  = idAPI.getId("yryz_writer_statistics");
		writerStatistics.setKid(kid);
		writerStatisticsDao.insertByPrimaryKeySelective(writerStatistics);
		return writerStatistics;
	}

	@Override
	public WriterStatistics updateWriterStatistics(WriterStatistics writerStatistics) {
		writerStatisticsDao.update(writerStatistics);
		return writerStatistics;
	}
	
	
 }

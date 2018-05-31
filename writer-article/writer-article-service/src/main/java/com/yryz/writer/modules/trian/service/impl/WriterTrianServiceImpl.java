package com.yryz.writer.modules.trian.service.impl;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.trian.dao.persistence.WriterTrianDao;
import com.yryz.writer.modules.trian.dto.WriterTrianDto;
import com.yryz.writer.modules.trian.service.WriterTrianService;
import com.yryz.writer.modules.trian.vo.WriterTrianVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.trian.service.impl
 * @Desc:
 * @Date: 2018/5/29.
 */
@Service
public class WriterTrianServiceImpl extends BaseServiceImpl implements WriterTrianService {

    @Autowired
    private WriterTrianDao writerTrianDao;

    @Override
    protected WriterTrianDao getDao() {
        return writerTrianDao;
    }

    @Override
    public PageList<WriterTrianVo> getList(WriterTrianDto writerTrianDto) {
        PageUtils.startPage(writerTrianDto.getCurrentPage(), writerTrianDto.getPageSize());
        List<WriterTrianVo> list = writerTrianDao.getList(writerTrianDto);
        return new PageModel<WriterTrianVo>().getPageList(list);
    }

    @Override
    public int getCount(WriterTrianDto writerTrianDto) {
        return writerTrianDao.getCount(writerTrianDto);
    }

}

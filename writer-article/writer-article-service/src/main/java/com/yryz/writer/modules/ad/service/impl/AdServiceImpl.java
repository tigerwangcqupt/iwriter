package com.yryz.writer.modules.ad.service.impl;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.ad.dao.persistence.AdDao;
import com.yryz.writer.modules.ad.dto.AdDto;
import com.yryz.writer.modules.ad.entity.Ad;
import com.yryz.writer.modules.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.ad.service.impl
 * @Desc:
 * @Date: 2018/6/1.
 */
@Service
public class AdServiceImpl extends BaseServiceImpl implements AdService {

    @Autowired
    private AdDao adDao;

    @Override
    protected AdDao getDao() {
        return adDao;
    }

    @Override
    public PageList<Ad> getList(AdDto adDto) {
        PageUtils.startPage(adDto.getCurrentPage(), adDto.getPageSize());
        List<Ad> list = adDao.getList(adDto);
        return new PageModel<Ad>().getPageList(list);
    }

    @Override
    public Integer getMaxSort() {
        return adDao.getMaxSort();
    }

}

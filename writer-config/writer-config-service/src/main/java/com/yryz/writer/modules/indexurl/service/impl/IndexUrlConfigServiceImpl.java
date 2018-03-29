package com.yryz.writer.modules.indexurl.service.impl;


import com.github.pagehelper.PageInfo;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.DateUtil;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.id.api.IdAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.indexurl.vo.IndexUrlConfigVo;
import com.yryz.writer.modules.indexurl.entity.IndexUrlConfig;
import com.yryz.writer.modules.indexurl.dto.IndexUrlConfigDto;
import com.yryz.writer.modules.indexurl.dao.persistence.IndexUrlConfigDao;
import com.yryz.writer.modules.indexurl.service.IndexUrlConfigService;
import java.util.ArrayList;
import java.util.List;


@Service
public class IndexUrlConfigServiceImpl extends BaseServiceImpl implements IndexUrlConfigService {
    @Autowired
    private IdAPI idAPI;
    @Autowired
    private IndexUrlConfigDao indexUrlConfigDao;

    protected BaseDao getDao() {
        return indexUrlConfigDao;
    }

    public PageList<IndexUrlConfigVo> selectList(IndexUrlConfigDto indexUrlConfigDto){
        PageUtils.startPage(indexUrlConfigDto.getCurrentPage(), indexUrlConfigDto.getPageSize());
        List<IndexUrlConfig> list = indexUrlConfigDao.selectList(indexUrlConfigDto);
        List<IndexUrlConfigVo> indexUrlConfigVoList = new ArrayList <IndexUrlConfigVo>();
        if(list != null && list.size() > 0) {
            for(IndexUrlConfig indexUrlConfig : list){
                IndexUrlConfigVo indexUrlConfigVo = new IndexUrlConfigVo();
                BeanUtils.copyProperties(indexUrlConfig,indexUrlConfigVo);
                indexUrlConfigVo.setStartDate(DateUtil.getString(indexUrlConfig.getBeginTime()));
                indexUrlConfigVo.setEndDate(DateUtil.getString(indexUrlConfig.getEndTime()));
                indexUrlConfigVoList.add(indexUrlConfigVo);
            }
        }
        return new PageModel<IndexUrlConfigVo>().getPageList(indexUrlConfigVoList);
    }

    @Override
    public PageList<IndexUrlConfigVo> selectAdminList(IndexUrlConfigDto indexUrlConfigDto) {
        PageUtils.startPage(indexUrlConfigDto.getCurrentPage(), indexUrlConfigDto.getPageSize());
        List<IndexUrlConfig> list = indexUrlConfigDao.selectList(indexUrlConfigDto);
        List<IndexUrlConfigVo> indexUrlConfigVoList = new ArrayList <IndexUrlConfigVo>();
        if(list != null && list.size() > 0) {
            for(IndexUrlConfig indexUrlConfig : list){
                IndexUrlConfigVo indexUrlConfigVo = new IndexUrlConfigVo();
                BeanUtils.copyProperties(indexUrlConfig,indexUrlConfigVo);
                indexUrlConfigVo.setStartDate(DateUtil.getString(indexUrlConfig.getBeginTime()));
                indexUrlConfigVo.setEndDate(DateUtil.getString(indexUrlConfig.getEndTime()));
                indexUrlConfigVoList.add(indexUrlConfigVo);
            }
        }
        return new PageModel<IndexUrlConfigVo>().getPageList(indexUrlConfigVoList);
    }


    public IndexUrlConfigVo detail(Long indexUrlConfigId) {
        IndexUrlConfig indexUrlConfig = indexUrlConfigDao.selectByKid(IndexUrlConfig.class,indexUrlConfigId);
        IndexUrlConfigVo indexUrlConfigVo = new IndexUrlConfigVo();
        if (indexUrlConfigVo != null) {
            //IndexUrlConfig to IndexUrlConfigVo
        }
        return indexUrlConfigVo;
    }

    @Override
    public IndexUrlConfig saveIndexConfig(IndexUrlConfig indexUrlConfig) {
        Long kid = idAPI.getId("yryz_index_url_config");
        indexUrlConfig.setKid(kid);
        indexUrlConfigDao.insertByPrimaryKeySelective(indexUrlConfig);
        return indexUrlConfig;
    }

    @Override
    public IndexUrlConfig updateIndexConfig(IndexUrlConfig indexUrlConfig) {
        indexUrlConfigDao.update(indexUrlConfig);
        return indexUrlConfig;
    }
 }

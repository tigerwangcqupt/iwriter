package com.yryz.writer.modules.indexcolumn.service.impl;

import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.indexcolumn.vo.IndexColumnVo;
import com.yryz.writer.modules.indexcolumn.entity.IndexColumn;
import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.writer.modules.indexcolumn.dao.persistence.IndexColumnDao;
import com.yryz.writer.modules.indexcolumn.service.IndexColumnService;
import java.util.ArrayList;
import java.util.List;


@Service
public class IndexColumnServiceImpl extends BaseServiceImpl implements IndexColumnService {

    @Autowired
    private IndexColumnDao indexColumnDao;

    protected BaseDao getDao() {
        return indexColumnDao;
    }

    public PageList<IndexColumnVo> selectList(IndexColumnDto indexColumnDto){
        PageUtils.startPage(indexColumnDto.getCurrentPage(), indexColumnDto.getPageSize());
        List<IndexColumn> list = indexColumnDao.selectList(indexColumnDto);
        List<IndexColumnVo> indexColumnVoList = new ArrayList <IndexColumnVo>();
        if(list != null && list.size() > 0) {
            for(IndexColumn indexColumn : list){
                IndexColumnVo indexColumnVo = new IndexColumnVo();
                //IndexColumn to IndexColumnVo
                indexColumnVoList.add(indexColumnVo);
            }
        }
        return new PageModel<IndexColumnVo>().getPageList(indexColumnVoList);
    }


    public IndexColumnVo detail(Long indexColumnId) {
        IndexColumn indexColumn = indexColumnDao.selectByKid(IndexColumn.class,indexColumnId);
        IndexColumnVo indexColumnVo = new IndexColumnVo();
        if (indexColumnVo != null) {
            //IndexColumn to IndexColumnVo
        }
        return indexColumnVo;
    }
 }

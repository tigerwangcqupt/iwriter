package com.yryz.writer.modules.indexcolumn.service.impl;

import com.yryz.common.utils.PageUtils;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(IndexColumnServiceImpl.class);

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
        IndexColumn indexColumn = indexColumnDao.selectByKid(IndexColumn.class, indexColumnId);
        IndexColumnVo indexColumnVo = new IndexColumnVo();
        if (indexColumnVo != null) {
            //IndexColumn to IndexColumnVo
        }
        return indexColumnVo;
    }

    /**
     *
     * @return
     */
    public List<IndexColumnVo> selectAll(IndexColumnDto indexColumnDto){
        List<IndexColumnVo> voList = new ArrayList<IndexColumnVo>();
        try {
//            IndexColumnDto indexColumnDto = new IndexColumnDto();
            List<IndexColumn> list = indexColumnDao.selectList(indexColumnDto);
            if(list != null && list.size() > 0) {
                for(IndexColumn indexColumn : list){
                    if (indexColumn == null) continue;
                    IndexColumnVo indexColumnVo = new IndexColumnVo();
                    indexColumnVo.setColumnName(indexColumn.getItemName());
                    indexColumnVo.setColumnUrl(indexColumn.getUrl());
                    indexColumnVo.setTipsNum("0");
                    //IndexColumn to IndexColumnVo
                    //需要设置气泡数
                    voList.add(indexColumnVo);
                }
            }
        } catch (Exception e) {
            logger.error("查询操作失败", e);
            //抛出异常回滚数据
//            throw new QsourceException(ExceptionEnum.SysException.getCode(),
//                    ExceptionEnum.SysException.getMsg(),
//                    ExceptionEnum.SysException.getErrorMsg());
        }
        return voList;
    }

 }

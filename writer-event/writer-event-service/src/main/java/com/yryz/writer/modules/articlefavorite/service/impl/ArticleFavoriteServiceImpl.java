package com.yryz.writer.modules.articlefavorite.service.impl;

import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.articlefavorite.vo.ArticleFavoriteVo;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.writer.modules.articlefavorite.dao.persistence.ArticleFavoriteDao;
import com.yryz.writer.modules.articlefavorite.service.ArticleFavoriteService;
import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleFavoriteServiceImpl extends BaseServiceImpl implements ArticleFavoriteService {

    @Autowired
    private ArticleFavoriteDao articleFavoriteDao;

    protected BaseDao getDao() {
        return articleFavoriteDao;
    }

    public PageList<ArticleFavoriteVo> selectList(ArticleFavoriteDto articleFavoriteDto){
        PageUtils.startPage(articleFavoriteDto.getCurrentPage(), articleFavoriteDto.getPageSize());
        List<ArticleFavorite> list = articleFavoriteDao.selectList(articleFavoriteDto);
        List<ArticleFavoriteVo> articleFavoriteVoList = new ArrayList <ArticleFavoriteVo>();
        if(list != null && list.size() > 0) {
            for(ArticleFavorite articleFavorite : list){
                ArticleFavoriteVo articleFavoriteVo = new ArticleFavoriteVo();
                //ArticleFavorite to ArticleFavoriteVo
                articleFavoriteVoList.add(articleFavoriteVo);
            }
        }
        return new PageModel<ArticleFavoriteVo>().getPageList(articleFavoriteVoList);
    }


    public ArticleFavoriteVo detail(Long articleFavoriteId) {
        ArticleFavorite articleFavorite = articleFavoriteDao.selectByKid(ArticleFavorite.class,articleFavoriteId);
        ArticleFavoriteVo articleFavoriteVo = new ArticleFavoriteVo();
        if (articleFavoriteVo != null) {
            //ArticleFavorite to ArticleFavoriteVo
        }
        return articleFavoriteVo;
    }
 }

package com.yryz.writer.modules.articlefavorite.service.impl;

import com.yryz.common.redis.utils.JedisUtils;
import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articlefavorite.constant.FavoriteConstant;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import com.yryz.writer.modules.message.constant.ModuleEnumConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ArticleFavoriteServiceImpl.class);

    @Autowired
    private ArticleFavoriteDao articleFavoriteDao;

    @Autowired
    private IdAPI idApi;

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

    @Override
    public PageList<ArticleFavoriteVo> selectListByWriter(ArticleFavoriteDto articleFavoriteDto) {
        PageUtils.startPage(articleFavoriteDto.getCurrentPage(), articleFavoriteDto.getPageSize());
        List<ArticleFavorite> list = articleFavoriteDao.selectListByWriter(articleFavoriteDto);
        List<ArticleFavoriteVo> articleFavoriteVoList = new ArrayList <ArticleFavoriteVo>();
        if(list != null && list.size() > 0) {
            for(ArticleFavorite articleFavorite : list){
                ArticleFavoriteVo articleFavoriteVo = new ArticleFavoriteVo();
                //ArticleFavorite to ArticleFavoriteVo
                articleFavoriteVoList.add(articleFavoriteVo);
            }
        }
//        JedisUtils.mapSetnx(LikeConstant.getHashKey(like),
//                EventReportConstant.LIKE_STATUS+userId, likeFlag.equals(1) ? "1" : "2");
        return new PageModel<ArticleFavoriteVo>().getPageList(articleFavoriteVoList);
    }

    @Override
    public Long saveFavorite(ArticleFavorite articleFavorite) {
        Long kid = idApi.getId("yryz_article_favorite");
        articleFavorite.setKid(kid);
        String key = FavoriteConstant.getHashKey(articleFavorite);
        String field = FavoriteConstant.getHashField();
        try {
            //保存气泡数
            if (JedisUtils.mapExists(key, field)){
                //写手的收藏数增加1
                Long status = JedisUtils.mapIncrby(key, field, 1);
                if (status == null){
                    //如果缓存没有设置成功 保存数据库
                }
            }else {
                //写手的收藏数设置1
                //是否设置成功
                boolean success = JedisUtils.mapSetnx(key, field, "1");
                if (!success){
                    //如果缓存没有设置成功 保存数据库
                }
            }
            articleFavoriteDao.insert(articleFavorite);

        }catch (Exception e) {
            logger.error("保存ArticleFavorite明细失败", e);
            e.printStackTrace();
        }
        return 1L;
    }
}

package com.yryz.writer.modules.articlefavorite.service.impl;

import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.articlefavorite.vo.ArticleFavoriteVo;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.writer.modules.articlefavorite.dao.persistence.ArticleFavoriteDao;
import com.yryz.writer.modules.articlefavorite.service.ArticleFavoriteService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleFavoriteServiceImpl extends BaseServiceImpl implements ArticleFavoriteService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleFavoriteServiceImpl.class);

    @Autowired
    private ArticleFavoriteDao articleFavoriteDao;

    @Autowired
    private IdAPI idApi;

    @Autowired
    private MessageApi messageApi;


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
        return new PageModel<ArticleFavoriteVo>().getPageList(list, articleFavoriteVoList);
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
        try {
            if (list != null && list.size() > 0) {
                list.stream().forEach(articleFavorite -> {
                    if (articleFavorite != null){
                        ArticleFavoriteVo articleFavoriteVo = new ArticleFavoriteVo();
                        //ArticleComment to ArticleCommentVo
                        BeanUtils.copyProperties(articleFavorite, articleFavoriteVo);
                        articleFavoriteVoList.add(articleFavoriteVo);
                    }
                });
            }
            messageApi.cleanMessageTips(ModuleEnum.FAVORITE, articleFavoriteDto.getCustId());
        }catch (Exception e) {
            logger.error("保存ArticleFavorite明细失败", e);
            e.printStackTrace();
        }

        return new PageModel<ArticleFavoriteVo>().getPageList(list, articleFavoriteVoList);
    }

    @Override
    @Transactional
    public Long saveFavorite(ArticleFavorite articleFavorite) {
        Assert.notNull(articleFavorite, "文章收藏参数不能为空");
        Assert.notNull(articleFavorite.getWriterId(), "文章作者不能为空");
        Long kid = idApi.getId("yryz_article_favorite");
        articleFavorite.setKid(kid);
        try {
            //保存写手的被收藏数
            articleFavoriteDao.insert(articleFavorite);
            messageApi.saveMessageTips(ModuleEnum.FAVORITE, articleFavorite.getWriterId() == null ? 0 : articleFavorite.getWriterId());

        }catch (Exception e) {
            logger.error("保存ArticleFavorite明细失败", e);
            return 0L;
        }
        return 1L;
    }
}

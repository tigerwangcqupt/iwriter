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
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import com.yryz.writer.modules.message.constant.ModuleEnumConstants;
import com.yryz.writer.modules.message.service.MessageService;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private MessageService messageService;

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
        try {
            if(list != null && list.size() > 0) {
                for(ArticleFavorite articleFavorite : list){
                    ArticleFavoriteVo articleFavoriteVo = new ArticleFavoriteVo();
                    BeanUtils.copyProperties(articleFavorite, articleFavoriteVo);
                    //ArticleFavorite to ArticleFavoriteVo
                    articleFavoriteVoList.add(articleFavoriteVo);
                }
            }
            messageApi.cleanMessageTips(ModuleEnum.FAVORITE, Long.valueOf(articleFavoriteDto.getCustId()));
        }catch (Exception e) {
            logger.error("保存ArticleFavorite明细失败", e);
            e.printStackTrace();
        }

        return new PageModel<ArticleFavoriteVo>().getPageList(articleFavoriteVoList);
    }

    @Override
    public Long saveFavorite(ArticleFavorite articleFavorite) {
        Assert.notNull(articleFavorite, "文章收藏参数不能为空");
        Assert.notNull(articleFavorite.getWriterId(), "文章作者不能为空");
        Long kid = idApi.getId("yryz_article_favorite");
        articleFavorite.setKid(kid);
        try {
            //保存写手的被收藏数
            messageService.saveMessageTips(ModuleEnum.FAVORITE, articleFavorite.getWriterId() == null ? 0 : articleFavorite.getWriterId());
            articleFavoriteDao.insert(articleFavorite);

        }catch (Exception e) {
            logger.error("保存ArticleFavorite明细失败", e);
            e.printStackTrace();
        }
        return 1L;
    }
}

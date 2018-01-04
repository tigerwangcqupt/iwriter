package com.yryz.writer.modules.articleshare.service.impl;

import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.id.api.IdAPI;
import com.yryz.writer.modules.message.MessageApi;
import com.yryz.writer.modules.message.constant.ModuleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.articleshare.vo.ArticleShareVo;
import com.yryz.writer.modules.articleshare.entity.ArticleShare;
import com.yryz.writer.modules.articleshare.dto.ArticleShareDto;
import com.yryz.writer.modules.articleshare.dao.persistence.ArticleShareDao;
import com.yryz.writer.modules.articleshare.service.ArticleShareService;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleShareServiceImpl extends BaseServiceImpl implements ArticleShareService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleShareServiceImpl.class);

    @Autowired
    private ArticleShareDao articleShareDao;

    @Autowired
    private IdAPI idApi;

    @Autowired
    private MessageApi messageApi;

    protected BaseDao getDao() {
        return articleShareDao;
    }

    public PageList<ArticleShareVo> selectList(ArticleShareDto articleShareDto){
        PageUtils.startPage(articleShareDto.getCurrentPage(), articleShareDto.getPageSize());
        List<ArticleShare> list = articleShareDao.selectList(articleShareDto);
        List<ArticleShareVo> articleShareVoList = new ArrayList <ArticleShareVo>();
        if(list != null && list.size() > 0) {
            for(ArticleShare articleShare : list){
                ArticleShareVo articleShareVo = new ArticleShareVo();
                //ArticleShare to ArticleShareVo
                articleShareVoList.add(articleShareVo);
            }
        }
        return new PageModel<ArticleShareVo>().getPageList(articleShareVoList);
    }


    public ArticleShareVo detail(Long articleShareId) {
        ArticleShare articleShare = articleShareDao.selectByKid(ArticleShare.class,articleShareId);
        ArticleShareVo articleShareVo = new ArticleShareVo();
        if (articleShareVo != null) {
            //ArticleShare to ArticleShareVo
        }
        return articleShareVo;
    }


    public PageList<ArticleShareVo> selectListByWriter(ArticleShareDto articleShareDto) {
        PageUtils.startPage(articleShareDto.getCurrentPage(), articleShareDto.getPageSize());
        List<ArticleShare> list = articleShareDao.selectListByWriter(articleShareDto);
        List<ArticleShareVo> articleShareVoList = new ArrayList <ArticleShareVo>();
        try {
            if(list != null && list.size() > 0) {
                for(ArticleShare articleShare : list){
                    ArticleShareVo articleShareVo = new ArticleShareVo();
                    BeanUtils.copyProperties(articleShare, articleShareVo);
                    //ArticleFavorite to ArticleFavoriteVo
                    articleShareVoList.add(articleShareVo);
                }
            }
            messageApi.cleanMessageTips(ModuleEnum.SHARE, Long.valueOf(articleShareDto.getCustId()));
        }catch (Exception e) {
            logger.error("保存ArticleFavorite明细失败", e);
            e.printStackTrace();
        }

        return new PageModel<ArticleShareVo>().getPageList(articleShareVoList);
    }

    @Override
    public Long saveArticleShare(ArticleShare articleShare) {
        Assert.notNull(articleShare.getWriterId(), "文章作者不能为空");
        Long kid = idApi.getId("yryz_article_share");
        articleShare.setKid(kid);
        try {
            //保存写手的被收藏数
            messageApi.saveMessageTips(ModuleEnum.SHARE, articleShare.getWriterId() == null ? 0 : articleShare.getWriterId());
            articleShareDao.insert(articleShare);

        } catch (Exception e) {
            logger.error("保存ArticleFavorite明细失败", e);
            return 0L;
        }
        return 1L;
    }
}

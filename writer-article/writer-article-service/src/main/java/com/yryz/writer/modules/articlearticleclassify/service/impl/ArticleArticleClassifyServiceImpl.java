package com.yryz.writer.modules.articlearticleclassify.service.impl;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.constant.ExceptionEnum;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.exception.YyrzPcException;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import com.yryz.writer.modules.article.Article;
import com.yryz.writer.modules.articleclassify.service.ArticleClassifyService;
import com.yryz.writer.modules.articleclassify.vo.ArticleClassifyVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.articlearticleclassify.vo.ArticleArticleClassifyVo;
import com.yryz.writer.modules.articlearticleclassify.entity.ArticleArticleClassify;
import com.yryz.writer.modules.articlearticleclassify.dto.ArticleArticleClassifyDto;
import com.yryz.writer.modules.articlearticleclassify.dao.persistence.ArticleArticleClassifyDao;
import com.yryz.writer.modules.articlearticleclassify.service.ArticleArticleClassifyService;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleArticleClassifyServiceImpl extends BaseServiceImpl implements ArticleArticleClassifyService {

    @Autowired
    private ArticleArticleClassifyDao articleArticleClassifyDao;

    protected BaseDao getDao() {
        return articleArticleClassifyDao;
    }

    public PageList<ArticleArticleClassifyVo> selectList(ArticleArticleClassifyDto articleArticleClassifyDto){
        PageUtils.startPage(articleArticleClassifyDto.getCurrentPage(), articleArticleClassifyDto.getPageSize());
        List<ArticleArticleClassify> list = articleArticleClassifyDao.selectList(articleArticleClassifyDto);
        List<ArticleArticleClassifyVo> articleArticleClassifyVoList = new ArrayList <ArticleArticleClassifyVo>();
        if(list != null && list.size() > 0) {
            for(ArticleArticleClassify articleArticleClassify : list){
                ArticleArticleClassifyVo articleArticleClassifyVo = new ArticleArticleClassifyVo();
                //ArticleArticleClassify to ArticleArticleClassifyVo
                articleArticleClassifyVoList.add(articleArticleClassifyVo);
            }
        }
        return new PageModel<ArticleArticleClassifyVo>().getPageList(articleArticleClassifyVoList);
    }


    public ArticleArticleClassifyVo detail(Long articleArticleClassifyId) {
        ArticleArticleClassify articleArticleClassify = articleArticleClassifyDao.selectByKid(ArticleArticleClassify.class,articleArticleClassifyId);
        ArticleArticleClassifyVo articleArticleClassifyVo = new ArticleArticleClassifyVo();
        if (articleArticleClassifyVo != null) {
            //ArticleArticleClassify to ArticleArticleClassifyVo
        }
        return articleArticleClassifyVo;
    }

    @Override
    public PageList<ArticleArticleClassifyVo> queryByClassifyId(ArticleArticleClassifyDto articleArticleClassifyDto) {
        PageUtils.startPage(articleArticleClassifyDto.getCurrentPage(), articleArticleClassifyDto.getPageSize());
        List<ArticleArticleClassify> list = articleArticleClassifyDao.queryByClassifyId(articleArticleClassifyDto.getArticleClassifyId());
        List<ArticleArticleClassifyVo> articleArticleClassifyVoList = new ArrayList <ArticleArticleClassifyVo>();
        if(list != null && list.size() > 0) {
            for(ArticleArticleClassify articleArticleClassify : list){
                ArticleArticleClassifyVo articleArticleClassifyVo = new ArticleArticleClassifyVo();
                //ArticleArticleClassify to ArticleArticleClassifyVo
                articleArticleClassifyVo.setTitle(articleArticleClassify.getTitle());
                articleArticleClassifyVoList.add(articleArticleClassifyVo);
            }
        }
        return new PageModel<ArticleArticleClassifyVo>().getPageList(list, articleArticleClassifyVoList);
    }

    @Override
    public List<Article> getArticleByStageClassifyId(Long classifyId, Integer systemType, Integer pageNo, Integer pageSize) {
        Assert.notNull(classifyId, "分类id不能为空");
        Assert.notNull(systemType, "设备id不能为空");
        pageNo = pageNo<1?0:pageNo;
        pageSize = pageSize<1?10:pageSize;
        List<Article> list = articleArticleClassifyDao.getArticleByClassifyId(classifyId,systemType,(pageNo-1)*pageSize,pageSize);
        return list;
    }


}

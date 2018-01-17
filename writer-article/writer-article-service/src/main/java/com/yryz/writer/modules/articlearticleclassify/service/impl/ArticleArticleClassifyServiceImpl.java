package com.yryz.writer.modules.articlearticleclassify.service.impl;

import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.dao.BaseDao;
import com.yryz.writer.common.service.BaseServiceImpl;
import com.yryz.writer.common.utils.PageUtils;
import com.yryz.writer.common.web.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.articlearticleclassify.vo.ArticleArticleClassifyVo;
import com.yryz.writer.modules.articlearticleclassify.entity.ArticleArticleClassify;
import com.yryz.writer.modules.articlearticleclassify.dto.ArticleArticleClassifyDto;
import com.yryz.writer.modules.articlearticleclassify.dao.persistence.ArticleArticleClassifyDao;
import com.yryz.writer.modules.articlearticleclassify.service.ArticleArticleClassifyService;
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
}

package com.yryz.writer.modules.articlecomment.service.impl;

import com.yryz.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import com.yryz.common.dao.BaseDao;
import com.yryz.common.service.BaseServiceImpl;
import com.yryz.common.web.PageModel;
import com.yryz.component.rpc.dto.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.articlecomment.vo.ArticleCommentVo;
import com.yryz.writer.modules.articlecomment.entity.ArticleComment;
import com.yryz.writer.modules.articlecomment.dto.ArticleCommentDto;
import com.yryz.writer.modules.articlecomment.dao.persistence.ArticleCommentDao;
import com.yryz.writer.modules.articlecomment.service.ArticleCommentService;
import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleCommentServiceImpl extends BaseServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentDao articleCommentDao;

    protected BaseDao getDao() {
        return articleCommentDao;
    }

    public PageList<ArticleCommentVo> selectList(ArticleCommentDto articleCommentDto){
        PageUtils.startPage(articleCommentDto.getCurrentPage(), articleCommentDto.getPageSize());
        List<ArticleComment> list = articleCommentDao.selectList(articleCommentDto);
        List<ArticleCommentVo> articleCommentVoList = new ArrayList <ArticleCommentVo>();
        if(list != null && list.size() > 0) {
            for(ArticleComment articleComment : list){
                ArticleCommentVo articleCommentVo = new ArticleCommentVo();
                //ArticleComment to ArticleCommentVo
                articleCommentVoList.add(articleCommentVo);
            }
        }
        return new PageModel<ArticleCommentVo>().getPageList(articleCommentVoList);
    }


    public ArticleCommentVo detail(Long articleCommentId) {
        ArticleComment articleComment = articleCommentDao.selectByKid(ArticleComment.class,articleCommentId);
        ArticleCommentVo articleCommentVo = new ArticleCommentVo();
        if (articleCommentVo != null) {
            //ArticleComment to ArticleCommentVo
        }
        return articleCommentVo;
    }

    public PageList<ArticleCommentVo> selectListByWriter(ArticleCommentDto articleCommentDto) {
        PageUtils.startPage(articleCommentDto.getCurrentPage(), articleCommentDto.getPageSize());
        List<ArticleComment> list = articleCommentDao.selectListByWriter(articleCommentDto);
        List<ArticleCommentVo> articleCommentVoList = new ArrayList<ArticleCommentVo>();
        if (list != null && list.size() > 0) {
            for (ArticleComment articleComment : list) {
                ArticleCommentVo articleCommentVo = new ArticleCommentVo();
                //ArticleComment to ArticleCommentVo
                articleCommentVoList.add(articleCommentVo);
            }
        }
        return new PageModel<ArticleCommentVo>().getPageList(articleCommentVoList);
    }
 }

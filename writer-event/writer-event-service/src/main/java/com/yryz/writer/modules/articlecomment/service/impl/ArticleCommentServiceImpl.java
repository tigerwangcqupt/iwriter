package com.yryz.writer.modules.articlecomment.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.writer.modules.articlecomment.vo.ArticleCommentVo;
import com.yryz.writer.modules.articlecomment.entity.ArticleComment;
import com.yryz.writer.modules.articlecomment.dto.ArticleCommentDto;
import com.yryz.writer.modules.articlecomment.dao.persistence.ArticleCommentDao;
import com.yryz.writer.modules.articlecomment.service.ArticleCommentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleCommentServiceImpl extends BaseServiceImpl implements ArticleCommentService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleCommentServiceImpl.class);

    @Autowired
    private ArticleCommentDao articleCommentDao;

    @Autowired
    private IdAPI idApi;

    @Autowired
    private MessageApi messageApi;

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
        return new PageModel<ArticleCommentVo>().getPageList(list, articleCommentVoList);
    }

    public ArticleCommentVo detail(Long articleCommentId) {
        ArticleComment articleComment = articleCommentDao.selectByKid(ArticleComment.class,articleCommentId);
        ArticleCommentVo articleCommentVo = new ArticleCommentVo();
        if (articleComment != null) {
            articleCommentVo.setCommentId(articleComment.getKid());
            articleCommentVo.setArticleId(articleComment.getArticleId());
            articleCommentVo.setArticleTitle(articleComment.getArticleTitle() == null ? "": articleComment.getArticleTitle());
            articleCommentVo.setCommentUserNickname(articleComment.getCommentUserNickname() == null ? "": articleComment.getCommentUserNickname());
            articleCommentVo.setUserId(articleComment.getCommentUserId());
            articleCommentVo.setContent(articleComment.getContent() == null ? "": articleComment.getContent());
            articleCommentVo.setCreateDate(articleComment.getCreateDate());
            articleCommentVo.setUserHeadImg(articleComment.getUserHeadImg());
        }
        return articleCommentVo;
    }

    public PageList<ArticleCommentVo> selectListByWriter(ArticleCommentDto articleCommentDto) {
        PageUtils.startPage(articleCommentDto.getCurrentPage(), articleCommentDto.getPageSize());
        List<ArticleComment> list = articleCommentDao.selectListByWriter(articleCommentDto);
        List<ArticleCommentVo> articleCommentVoList = new ArrayList<ArticleCommentVo>();
        if (list != null && list.size() > 0) {
            list.stream().forEach(articleComment -> {
                if (articleComment != null){
                    ArticleCommentVo articleCommentVo = new ArticleCommentVo();
                    //ArticleComment to ArticleCommentVo
                    articleCommentVo.setCommentUserNickname(articleComment.getCommentUserNickname() == null ? "": articleComment.getCommentUserNickname());
                    articleCommentVo.setContent(articleComment.getContent() == null ? "": articleComment.getContent());
                    articleCommentVo.setArticleTitle(articleComment.getArticleTitle() == null ? "": articleComment.getArticleTitle());
                    articleCommentVo.setCreateDate(articleComment.getCreateDate());
                    articleCommentVoList.add(articleCommentVo);
                }
            });
        }
        return new PageModel<ArticleCommentVo>().getPageList(list, articleCommentVoList);
    }

    @Override
    @Transactional
    public Boolean saveArticleComment(ArticleComment articleComment) {
        Assert.notNull(articleComment, "评论参数不能为空");
        Assert.notNull(articleComment.getWriterId(), "文章作者不能为空");
        try {
            Long kid = idApi.getId("yryz_article_comment");
            articleComment.setKid(kid);
            //保存写手的被收藏数
            articleCommentDao.insert(articleComment);
            messageApi.saveMessageTips(ModuleEnum.COMMENT, articleComment.getWriterId() == null ? 0 : articleComment.getWriterId());
        }catch (Exception e) {
            logger.error("保存ArticleComment明细失败", e);
            return false;
        }
        return true;
    }
}

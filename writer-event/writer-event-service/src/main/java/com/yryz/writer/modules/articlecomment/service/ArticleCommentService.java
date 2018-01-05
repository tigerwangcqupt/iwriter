package com.yryz.writer.modules.articlecomment.service;

import com.yryz.common.service.BaseService;
import com.yryz.writer.modules.articlecomment.dto.ArticleCommentDto;
import com.yryz.writer.modules.articlecomment.entity.ArticleComment;
import com.yryz.writer.modules.articlecomment.vo.ArticleCommentVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

/**
 * 
  * @ClassName: ArticleCommentService
  * @Description: ArticleComment业务访问接口
  * @author huyangyang
  * @date 2018-01-03 11:43:39
  *
 */
@Repository
public interface ArticleCommentService extends BaseService {

   PageList<ArticleCommentVo> selectList(ArticleCommentDto articleCommentDto);

   ArticleCommentVo detail(Long articleCommentId);

   /**
    * 查询写手的全部文章的用户评论分页
    * @param articleCommentDto
    * @return
    */
   PageList<ArticleCommentVo> selectListByWriter(ArticleCommentDto articleCommentDto);

   /**
    * 保存文章评论
    * @param articleComment
    * @return
    */
   public Boolean saveArticleComment(ArticleComment articleComment);
}
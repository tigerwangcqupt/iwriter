package com.yryz.openapi.comment.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.modules.articlecomment.ArticleCommentApi;
import com.yryz.writer.modules.articlecomment.dto.ArticleCommentDto;
import com.yryz.writer.modules.articlecomment.entity.ArticleComment;
import com.yryz.writer.modules.articlecomment.vo.ArticleCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("services/app/v1/articleComment")
public class ArticleCommentController extends BaseController {
   @Autowired
   private ArticleCommentApi articleCommentApi;

   @ResponseBody
   @RequestMapping(value="/single", method = RequestMethod.GET)
   public RpcResponse<ArticleCommentVo> detail(Long articleCommentId) {
      String userId = request.getHeader("userId");
      Assert.notNull(userId, "用户id不能为空");
      Assert.notNull(articleCommentId, "评论id不能为空");
      return articleCommentApi.detail(articleCommentId);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<PageList<ArticleCommentVo>> list(ArticleCommentDto articleCommentDto) {
      String userId = request.getHeader("userId");
      Assert.notNull(userId, "用户id不能为空");
      Assert.notNull(articleCommentDto.getCustId(), "写手id不能为空");
      return articleCommentApi.listByWriter(articleCommentDto);
   }

   @ResponseBody
   @RequestMapping(value="/saveWriterComment", method = RequestMethod.POST)
   public RpcResponse<Boolean> saveWriterComment(@RequestBody ArticleComment articleComment) {
      String userId = request.getHeader("userId");
      Assert.notNull(userId, "用户id不能为空");
      Assert.notNull(articleComment.getTargetId(), "被回复评论不能为空");
      Assert.notNull(articleComment.getWriterId(), "写手不能为空");
      Assert.notNull(articleComment.getCommentUserId(), "评论用户不能为空");
      Assert.hasText(articleComment.getCommentUserNickname(), "收藏者昵称不为空");
      Assert.notNull(articleComment.getArticleId(), "文章不能为空");
      Assert.notNull(articleComment.getArticleTitle(), "文章标题不能为空");
      Assert.hasText(articleComment.getContent(), "评论内容不能为空");
      if (articleComment.getContent().length() > 200){
         throw new IllegalArgumentException("评论长度不能超过200");
      }
      articleComment.setCommentType(1);
      articleComment.setCommentWriterId(articleComment.getWriterId());
      articleComment.setCreateUserId(articleComment.getWriterId().toString());
      return articleCommentApi.saveArticleComment(articleComment);
   }

}

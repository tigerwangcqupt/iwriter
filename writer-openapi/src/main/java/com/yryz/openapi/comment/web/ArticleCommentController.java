package com.yryz.openapi.comment.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
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
public class ArticleCommentController {
   @Autowired
   private ArticleCommentApi articleCommentApi;

   @ResponseBody
   @RequestMapping(value="/single", method = RequestMethod.GET)
   public RpcResponse<ArticleCommentVo> detail(Long articleFavoriteId) {
       return articleCommentApi.detail(articleFavoriteId);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<PageList<ArticleCommentVo>> list(ArticleCommentDto articleCommentDto) {
      Assert.notNull(articleCommentDto.getCustId(), "写手id不能为空");
      return articleCommentApi.listByWriter(articleCommentDto);
   }

   @ResponseBody
   @RequestMapping(value="/save", method = RequestMethod.POST)
   public RpcResponse<Boolean> save(@RequestBody ArticleComment articleComment) {
      Assert.notNull(articleComment, "文章收藏参数不能为空");
      Assert.notNull(articleComment.getWriterId(), "文章作者不能为空");
      Assert.notNull(articleComment.getCommentUserNickname(), "收藏者昵称不为空");
      Assert.notNull(articleComment.getArticleTitle(), "文章标题不能为空");
      return articleCommentApi.saveArticleComment(articleComment);
   }

}

package com.yryz.openapi.share.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.web.BaseController;
import com.yryz.writer.modules.articleshare.ArticleShareApi;
import com.yryz.writer.modules.articleshare.dto.ArticleShareDto;
import com.yryz.writer.modules.articleshare.entity.ArticleShare;
import com.yryz.writer.modules.articleshare.vo.ArticleShareVo;
import com.yryz.writer.modules.indexcolumn.IndexColumnApi;
import com.yryz.writer.modules.indexcolumn.dto.IndexColumnDto;
import com.yryz.writer.modules.indexcolumn.vo.IndexColumnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("services/app/v1/articleShare")
public class ArticleShareController extends BaseController {
   @Autowired
   private ArticleShareApi articleShareApi;

   @ResponseBody
   @RequestMapping(value="/single", method = RequestMethod.GET)
   public RpcResponse<ArticleShareVo> detail(Long articleShareId) {
      String userId = request.getHeader("userId");
      Assert.notNull(userId, "用户id不能为空");
      Assert.notNull(articleShareId, "文章分享id不能为空");
      return articleShareApi.detail(articleShareId);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<PageList<ArticleShareVo>> list(ArticleShareDto articleShareDto) {
      String userId = request.getHeader("userId");
      Assert.notNull(userId, "用户id不能为空");
      Assert.notNull(articleShareDto.getCustId(), "写手id不能为空");
      return articleShareApi.listByWriter(articleShareDto);
   }

   @ResponseBody
   @RequestMapping(value="/save", method = RequestMethod.POST)
   public RpcResponse<Boolean> save(@RequestBody ArticleShare articleShare) {
      String userId = request.getHeader("userId");
      Assert.notNull(userId, "用户id不能为空");
      Assert.notNull(articleShare.getWriterId(), "写手id不能为空");
      Assert.notNull(articleShare.getArticleId(), "文章id不能为空");
      articleShare.setCreateUserId(userId);
      return articleShareApi.saveArticleShare(articleShare);
   }

}

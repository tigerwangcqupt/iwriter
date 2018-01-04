package com.yryz.openapi.favorite.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.articlefavorite.vo.ArticleFavoriteVo;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.articlefavorite.ArticleFavoriteApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("services/app/v1/articleFavorite")
public class ArticleFavoriteController {
   @Autowired
   private ArticleFavoriteApi articleFavoriteApi;

   @ResponseBody
   @RequestMapping(value="/single", method = RequestMethod.GET)
   public RpcResponse<ArticleFavoriteVo> detail(Long articleFavoriteId) {
       return articleFavoriteApi.detail(articleFavoriteId);
   }

   @ResponseBody
   @RequestMapping(value="/list", method = RequestMethod.GET)
   public RpcResponse<PageList<ArticleFavoriteVo>> list(ArticleFavoriteDto articleFavoriteDto) {
      Assert.notNull(articleFavoriteDto.getCustId(), "写手id不能为空");
      return articleFavoriteApi.listByWriter(articleFavoriteDto);
   }

   @ResponseBody
   @RequestMapping(value="/save", method = RequestMethod.POST)
   public RpcResponse<ArticleFavoriteVo> save(@RequestBody ArticleFavorite articleFavorite) {
      Assert.notNull(articleFavorite, "文章收藏参数不能为空");
      Assert.notNull(articleFavorite.getWriterId(), "文章作者不能为空");
      Assert.notNull(articleFavorite.getCreateUserNickname(), "收藏者昵称不为空");
      Assert.notNull(articleFavorite.getArticleTitle(), "文章标题不能为空");
      return articleFavoriteApi.saveFavorite(articleFavorite);
   }

}

package com.yryz.openapi.favorite.web;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.articlefavorite.vo.ArticleFavoriteVo;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.articlefavorite.ArticleFavoriteApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return articleFavoriteApi.list(articleFavoriteDto);
   }

}

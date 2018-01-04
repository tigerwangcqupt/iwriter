package com.yryz.writer.modules.articlefavorite.service;

import com.yryz.common.service.BaseService;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.articlefavorite.vo.ArticleFavoriteVo;
import org.springframework.stereotype.Repository;

import com.yryz.component.rpc.dto.PageList;

import java.util.List;

/**
 * 
  * @ClassName: ArticleFavoriteService
  * @Description: ArticleFavorite业务访问接口
  * @author huyangyang
  * @date 2018-01-02 20:52:42
  *
 */
@Repository
public interface ArticleFavoriteService extends BaseService {

   PageList<ArticleFavoriteVo> selectList(ArticleFavoriteDto articleFavoriteDto);

   ArticleFavoriteVo detail(Long articleFavoriteId);

   /**
    * 查询写手文章的全部收藏分页
    * @param articleFavoriteDto
    * @return
    */
   PageList<ArticleFavoriteVo> selectListByWriter(ArticleFavoriteDto articleFavoriteDto);

   /**
    * 保存
    * @param articleFavorite
    * @return
    */
   Long saveFavorite(ArticleFavorite articleFavorite);
}
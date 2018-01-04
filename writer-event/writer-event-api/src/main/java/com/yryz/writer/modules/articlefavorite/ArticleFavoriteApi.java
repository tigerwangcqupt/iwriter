package com.yryz.writer.modules.articlefavorite;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articlefavorite.vo.ArticleFavoriteVo;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;

/**
 * 
 * @ClassName: ArticleFavoriteApi
 * @Description: ArticleFavoriteApi接口
 * @author huyangyang
 * @date 2018-01-02 20:52:42
 *
 */
public interface ArticleFavoriteApi {

	/**
	*  获取ArticleFavorite明细
	*  @param  id
	*  @return
	* */
	RpcResponse<ArticleFavorite> get(Long id);

    /**
    *  获取ArticleFavorite明细
    *  @param  id
    *  @return
    * */
    RpcResponse<ArticleFavoriteVo> detail(Long id);

    /**
    * 获取ArticleFavorite列表
    * @param articleFavoriteDto
    * @return
    * */
    RpcResponse<PageList<ArticleFavoriteVo>> list(ArticleFavoriteDto articleFavoriteDto);

	/**
	 * 查询写手文章的全部收藏列表
	 * @param articleFavoriteDto
	 * @return
	 * */
	RpcResponse<PageList<ArticleFavoriteVo>> listByWriter(ArticleFavoriteDto articleFavoriteDto);

	/**
	 * 保存收藏记录
	 * @return
	 * */
	RpcResponse<ArticleFavoriteVo> saveFavorite(ArticleFavorite articleFavorite);
}

package com.yryz.writer.modules.articlearticleclassify;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articlearticleclassify.vo.ArticleArticleClassifyVo;
import com.yryz.writer.modules.articlearticleclassify.dto.ArticleArticleClassifyDto;
import com.yryz.writer.modules.articlearticleclassify.entity.ArticleArticleClassify;

/**
 * 
 * @ClassName: ArticleArticleClassifyApi
 * @Description: ArticleArticleClassifyApi接口
 * @author huyangyang
 * @date 2018-01-15 10:34:38
 *
 */
public interface ArticleArticleClassifyApi {

	/**
	*  获取ArticleArticleClassify明细
	*  @param  id
	*  @return
	* */
	RpcResponse<ArticleArticleClassify> get(Long id);

    /**
    *  获取ArticleArticleClassify明细
    *  @param  id
    *  @return
    * */
    RpcResponse<ArticleArticleClassifyVo> detail(Long id);

    /**
    * 获取ArticleArticleClassify列表
    * @param articleArticleClassifyDto
    * @return
    * */
    RpcResponse<PageList<ArticleArticleClassifyVo>> list(ArticleArticleClassifyDto articleArticleClassifyDto);

	/**
	 * 根据文章分类查询文章
	 * @param articleArticleClassifyDto
	 * @return
	 */
	RpcResponse<PageList<ArticleArticleClassifyVo>> queryByClassifyId(ArticleArticleClassifyDto articleArticleClassifyDto);

}

package com.yryz.writer.modules.articleClassify;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articleClassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleClassify.entity.ArticleClassify;
import com.yryz.writer.modules.articleClassify.vo.ArticleClassifyVo;

/**
 * 
 * @ClassName: ArticleClassifyApi
 * @Description: ArticleClassifyApi接口
 * @author huyangyang
 * @date 2018-01-09 11:03:59
 *
 */
public interface ArticleClassifyApi {

	/**
	*  获取ArticleClassify明细
	*  @param  id
	*  @return
	* */
	RpcResponse<ArticleClassify> get(Long id);

    /**
    *  获取ArticleClassify明细
    *  @param  id
    *  @return
    * */
    RpcResponse<ArticleClassifyVo> detail(Long id);

    /**
    * 获取ArticleClassify列表
    * @param articleClassifyDto
    * @return
    * */
    RpcResponse<PageList<ArticleClassifyVo>> list(ArticleClassifyDto articleClassifyDto);

	/**
	 *  获取ArticleClassify明细
	 *  @param  articleClassify
	 *  @return
	 * */
	RpcResponse<Boolean> insert(ArticleClassify articleClassify);
}

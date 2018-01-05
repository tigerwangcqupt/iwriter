package com.yryz.writer.modules.articleshare;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articleshare.vo.ArticleShareVo;
import com.yryz.writer.modules.articleshare.dto.ArticleShareDto;
import com.yryz.writer.modules.articleshare.entity.ArticleShare;

/**
 * 
 * @ClassName: ArticleShareApi
 * @Description: ArticleShareApi接口
 * @author huyangyang
 * @date 2018-01-02 20:24:27
 *
 */
public interface ArticleShareApi {

	/**
	*  获取ArticleShare明细
	*  @param  id
	*  @return
	* */
	RpcResponse<ArticleShare> get(Long id);

    /**
    *  获取ArticleShare明细
    *  @param  id
    *  @return
    * */
    RpcResponse<ArticleShareVo> detail(Long id);

    /**
    * 获取ArticleShare列表
    * @param articleShareDto
    * @return
    * */
    RpcResponse<PageList<ArticleShareVo>> list(ArticleShareDto articleShareDto);

	/**
	 * 查询写手文章的全部分享列表
	 * @param articleShareDto
	 * @return
	 * */
	RpcResponse<PageList<ArticleShareVo>> listByWriter(ArticleShareDto articleShareDto);

	/**
	 * 保存写手的文章分享
	 * @param articleShare
	 * @return
	 */
	RpcResponse<Boolean> saveArticleShare(ArticleShare articleShare);
}

package com.yryz.writer.modules.articlecomment;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articlecomment.vo.ArticleCommentVo;
import com.yryz.writer.modules.articlecomment.dto.ArticleCommentDto;
import com.yryz.writer.modules.articlecomment.entity.ArticleComment;

/**
 * 
 * @ClassName: ArticleCommentApi
 * @Description: 文章评论Api接口
 * @author huyangyang
 * @date 2018-01-03 11:43:39
 *
 */
public interface ArticleCommentApi {

	/**
	*  获取ArticleComment明细
	*  @param  id
	*  @return
	* */
	RpcResponse<ArticleComment> get(Long id);

    /**
    *  获取ArticleComment明细
    *  @param  id
    *  @return
    * */
    RpcResponse<ArticleCommentVo> detail(Long id);

    /**
    * 获取ArticleComment列表
    * @param articleCommentDto
    * @return
    * */
    RpcResponse<PageList<ArticleCommentVo>> list(ArticleCommentDto articleCommentDto);

	/**
	 * 获取写手的文章评论列表
	 * @param articleCommentDto
	 * @return
	 * */
	RpcResponse<PageList<ArticleCommentVo>> listByWriter(ArticleCommentDto articleCommentDto);

	/**
	 * 保存评论
	 * @return
	 * */
	RpcResponse<Boolean> saveArticleComment(ArticleComment articleComment);
}

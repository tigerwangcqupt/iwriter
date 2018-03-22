package com.yryz.writer.modules.articleclassify;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articleclassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleclassify.entity.ArticleClassify;
import com.yryz.writer.modules.articleclassify.vo.ArticleClassifyVo;

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
	 *  保存ArticleClassify明细
	 *  @param  articleClassify
	 *  @return
	 * */
	RpcResponse<Boolean> insert(ArticleClassify articleClassify);

	/**
	 *  更新ArticleClassify明细
	 *  @param  articleClassify
	 *  @return
	 * */
	RpcResponse<Boolean> update(ArticleClassify articleClassify);

	/**
	 * 上架分类
	 * @param articleClassifyId
	 * @return
	 */
	RpcResponse<Boolean> shelveOn(Long articleClassifyId);

	/**
	 * 下架分类
	 * @param articleClassifyId
	 * @return
	 */
	RpcResponse<Boolean> shelveOff(Long articleClassifyId);

	/**
	 * 删除分类
	 * @param articleClassifyId
	 * @return
	 */
	RpcResponse<Boolean> delete(Long articleClassifyId, String lastUpdateUserId);

	/**
	 * 恢复分类
	 * @param articleClassifyId
	 * @return
	 */
	RpcResponse<Boolean> recover(Long articleClassifyId, String lastUpdateUserId);

	/**
	 * 检查该分类是否可以作为父类
	 * 即该分类下是否包含文章
	 * 若有文章就是末级分类 不能以它做父类
	 * @param kid
	 * @return
	 */
	RpcResponse<Boolean> checkArticleClassify(Long kid);

	/**
	 * 交换权重
	 * @param id
	 * @param tid
	 *
	 * @return
	 */
	RpcResponse<Boolean> setSort(Long id, Long tid);

	/**
	 * 设置推荐/取消推荐
	 * @param id
	 * @return
	 */
	RpcResponse<Boolean> setRecommend(Long id,Integer flag);

	/**
	 * 推荐列表
	 * @param articleClassifyDto
	 * @return
	 */
	RpcResponse<PageList<ArticleClassifyVo>> recommendlist(ArticleClassifyDto articleClassifyDto);

	/**
	 * 校验推荐
	 * @param classifyId
	 * @return
	 */
	RpcResponse<Long> getUpOrDownRecommend(Long classifyId,Integer flag);
}

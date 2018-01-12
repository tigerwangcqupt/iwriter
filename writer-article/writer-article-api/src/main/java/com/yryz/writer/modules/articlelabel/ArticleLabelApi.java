package com.yryz.writer.modules.articlelabel;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.modules.articlelabel.vo.ArticleLabelVo;
import com.yryz.writer.modules.articlelabel.dto.ArticleLabelDto;
import com.yryz.writer.modules.articlelabel.entity.ArticleLabel;

/**
 * 
 * @ClassName: ArticleLabelApi
 * @Description: ArticleLabelApi接口
 * @author huyangyang
 * @date 2018-01-11 19:29:39
 *
 */
public interface ArticleLabelApi {

	/**
	*  获取ArticleLabel明细
	*  @param  id
	*  @return
	* */
	RpcResponse<ArticleLabel> get(Long id);

    /**
    *  获取ArticleLabel明细
    *  @param  id
    *  @return
    * */
    RpcResponse<ArticleLabelVo> detail(Long id);

    /**
    * 获取ArticleLabel列表
    * @param articleLabelDto
    * @return
    * */
    RpcResponse<PageList<ArticleLabelVo>> list(ArticleLabelDto articleLabelDto);

	/**
	 *  保存ArticleClassify明细
	 *  @param  articleLabel
	 *  @return
	 * */
	RpcResponse<Boolean> insert(ArticleLabel articleLabel);

	/**
	 *  更新ArticleClassify明细
	 *  @param  articleLabel
	 *  @return
	 * */
	RpcResponse<Boolean> update(ArticleLabel articleLabel);

	/**
	 * 上架分类
	 * @param articleLabelId
	 * @return
	 */
	RpcResponse<Boolean> shelveOn(Long articleLabelId);

	/**
	 * 下架分类
	 * @param articleLabelId
	 * @return
	 */
	RpcResponse<Boolean> shelveOff(Long articleLabelId);

	/**
	 * 删除分类
	 * @param articleLabelId
	 * @return
	 */
	RpcResponse<Boolean> delete(Long articleLabelId, String lastUpdateUserId);

	/**
	 * 恢复分类
	 * @param articleLabelId
	 * @return
	 */
	RpcResponse<Boolean> recover(Long articleLabelId, String lastUpdateUserId);

	/**
	 * 校验标签
	 * @param kid
	 * @return
	 */
	RpcResponse<Boolean> validateLabel(Long kid);
}

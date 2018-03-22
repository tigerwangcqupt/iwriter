package com.yryz.writer.modules.articlelabel.provider;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.article.Article;
import com.yryz.writer.modules.articlelabel.AppAritcleLableApi;
import com.yryz.writer.modules.articlelabel.ArticleLabelApi;
import com.yryz.writer.modules.articlelabel.entity.ArticleLabel;
import com.yryz.writer.modules.articlelabel.vo.ArticleLabelVo;
import com.yryz.writer.modules.articlelabel.dto.ArticleLabelDto;
import com.yryz.writer.modules.articlelabel.service.ArticleLabelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleLabelProvider implements ArticleLabelApi,AppAritcleLableApi {

	private static final Logger logger = LoggerFactory.getLogger(ArticleLabelProvider.class);

	@Autowired
	private ArticleLabelService articleLabelService;

	/**
	*  获取ArticleLabel明细
	*  @param  articleLabelId
	*  @return
	* */
	public RpcResponse<ArticleLabel> get(Long articleLabelId) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.get(ArticleLabel.class, articleLabelId));
		} catch (Exception e) {
			logger.error("获取ArticleLabel明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取ArticleLabel明细
	*  @param  articleLabelId
	*  @return
	* */
	public RpcResponse<ArticleLabelVo> detail(Long articleLabelId) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.detail(articleLabelId));
		} catch (Exception e) {
			logger.error("获取ArticleLabel明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

	/**
    * 获取ArticleLabel列表
    * @param articleLabelDto
    * @return
    *
	*/
    public RpcResponse<PageList<ArticleLabelVo>> list(ArticleLabelDto articleLabelDto) {
        try {
			 return ResponseModel.returnListSuccess(articleLabelService.selectList(articleLabelDto));
        } catch (Exception e) {
        	logger.error("获取ArticleLabel列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<Boolean> insert(ArticleLabel articleLabel) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.insert(articleLabel));
		} catch (Exception e) {
			logger.error("保存ArticleLabel失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> update(ArticleLabel articleLabel) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.update(articleLabel));
		} catch (Exception e) {
			logger.error("更新ArticleLabel失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> shelveOn(Long articleLabelId) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.shelveOn(articleLabelId));
		} catch (Exception e) {
			logger.error("上架ArticleLabel失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> shelveOff(Long articleLabelId) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.shelveOff(articleLabelId));
		} catch (Exception e) {
			logger.error("下架ArticleLabel失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> delete(Long articleLabelId, String lastUpdateUserId) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.deleteArticleLabel(articleLabelId, lastUpdateUserId));
		} catch (Exception e) {
			logger.error("删除ArticleLabel失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> recover(Long articleLabelId, String lastUpdateUserId) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.recoverArticleLabel(articleLabelId, lastUpdateUserId));
		} catch (Exception e) {
			logger.error("恢复ArticleLabel失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> validateLabel(Long kid) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.validateLabel(kid));
		} catch (Exception e) {
			logger.error("校验ArticleLabel失败", e);
			return ResponseModel.returnException(e);
		}
	}
	/**
	 *  交换权重
	 *
	 *  @return
	 * */
	@Override
	public RpcResponse<Boolean> setSort(Long id, Long tid) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.setSort(id,tid));
		} catch (Exception e) {
			logger.error("校验ArticleLabel失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> setRecommend(Long id,Integer flag) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.setRecommend(id,flag));
		} catch (Exception e) {
			logger.error("设置推进失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<PageList<ArticleLabelVo>> recommendlist(ArticleLabelDto articleLabelDto) {
		try {
			return ResponseModel.returnListSuccess(articleLabelService.recommendlist(articleLabelDto));
		} catch (Exception e) {
			logger.error("recommendlist error", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Long> getUpOrDownRecommend(Long lableId, Integer flag) {
		try {
			return ResponseModel.returnListSuccess(articleLabelService.getUpOrDownRecommend(lableId,flag));
		} catch (Exception e) {
			logger.error("articleLabel error by getUpOrDownRecommend", e);
			return ResponseModel.returnException(e);
		}
	}

	/**
	 *  查询热门标签
	 *
	 *  @return
	 * */
	@Override
	public RpcResponse<List<ArticleLabelVo>> getHotArticleLabel() {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.getHotArticleLabel());
		} catch (Exception e) {
			return ResponseModel.returnException(e);
		}
	}

	/**
	 *  根据标签查询文章
	 *  @param  labelId
	 *  @return
	 * */
	@Override
	public RpcResponse<List<Article>> getArticleByArticleLabelId(Long labelId,Integer systemType,Integer pageNo,Integer pageSize) {
		try {
			return ResponseModel.returnObjectSuccess(articleLabelService.getArticleByArticleLabelId(labelId,systemType,pageNo,pageSize));
		} catch (Exception e) {
			logger.error("getArticleByArticleLabelId error", e);
			return ResponseModel.returnException(e);
		}
	}
}

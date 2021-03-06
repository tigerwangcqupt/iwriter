package com.yryz.writer.modules.articleclassify.provider;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;
import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.articleclassify.AppArticleClassifyApi;
import com.yryz.writer.modules.articleclassify.ArticleClassifyApi;
import com.yryz.writer.modules.articleclassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleclassify.entity.ArticleClassify;
import com.yryz.writer.modules.articleclassify.service.ArticleClassifyService;
import com.yryz.writer.modules.articleclassify.vo.ArticleClassifyVo;

@Service
public class ArticleClassifyProvider implements ArticleClassifyApi ,AppArticleClassifyApi {

	private static final Logger logger = LoggerFactory.getLogger(ArticleClassifyProvider.class);

	@Autowired
	private ArticleClassifyService articleClassifyService;

	/**
	*  获取ArticleClassify明细
	*  @param  articleClassifyId
	*  @return
	* */
	public RpcResponse<ArticleClassify> get(Long articleClassifyId) {
		try {
			return ResponseModel.returnObjectSuccess(articleClassifyService.get(ArticleClassify.class, articleClassifyId));
		} catch (Exception e) {
			logger.error("获取ArticleClassify明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取ArticleClassify明细
	*  @param  articleClassifyId
	*  @return
	* */
	public RpcResponse<ArticleClassifyVo> detail(Long articleClassifyId) {
		try {
			return ResponseModel.returnObjectSuccess(articleClassifyService.detail(articleClassifyId));
		} catch (Exception e) {
			logger.error("获取ArticleClassify明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取ArticleClassify列表
    * @param articleClassifyDto
    * @return
    *
	*/
    public RpcResponse<PageList<ArticleClassifyVo>> list(ArticleClassifyDto articleClassifyDto) {
        try {
			 return ResponseModel.returnListSuccess(articleClassifyService.selectList(articleClassifyDto));
        } catch (Exception e) {
        	logger.error("获取ArticleClassify列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<Boolean> insert(ArticleClassify articleClassify) {
		try {
			return ResponseModel.returnObjectSuccess(articleClassifyService.insert(articleClassify));
		} catch (Exception e) {
			logger.error("保存ArticleClassify失败", e);
			return ResponseModel.returnException(e);
		}
	}


	public RpcResponse<Boolean> update(ArticleClassify articleClassify) {
		try {
			return ResponseModel.returnObjectSuccess(articleClassifyService.update(articleClassify));
		} catch (Exception e) {
			logger.error("保存ArticleClassify失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> shelveOn(Long articleClassifyId) {
		try {
			return ResponseModel.returnObjectSuccess(articleClassifyService.shelveOn(articleClassifyId));
		} catch (Exception e) {
			logger.error("上架ArticleClassify失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> shelveOff(Long articleClassifyId) {
		try {
			return ResponseModel.returnObjectSuccess(articleClassifyService.shelveOff(articleClassifyId));
		} catch (Exception e) {
			logger.error("下架ArticleClassify失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> delete(Long articleClassifyId, String lastUpdateUserId) {
		try {
			return ResponseModel.returnObjectSuccess(articleClassifyService.deleteArticleClassify(articleClassifyId, lastUpdateUserId));
		} catch (Exception e) {
			logger.error("删除ArticleClassify失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> recover(Long articleClassifyId, String lastUpdateUserId) {
		try {
			return ResponseModel.returnObjectSuccess(articleClassifyService.recoverArticleClassify(articleClassifyId, lastUpdateUserId));
		} catch (Exception e) {
			logger.error("删除ArticleClassify失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> checkArticleClassify(Long kid) {
		try {
			return ResponseModel.returnObjectSuccess(articleClassifyService.checkArticleClassify(kid));
		} catch (Exception e) {
			logger.error("删除ArticleClassify失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> setSort(Long id, Long tid) {
		try {
			return ResponseModel.returnListSuccess(articleClassifyService.setSort(id,tid));
		} catch (Exception e) {
			logger.error("获取ArticleClassifyVo列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> setRecommend(Long id, Integer flag) {
		try {
			return ResponseModel.returnListSuccess(articleClassifyService.setRecommend(id,flag));
		} catch (Exception e) {
			logger.error("获取ArticleClassifyVo列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<PageList<ArticleClassifyVo>> recommendlist(ArticleClassifyDto articleClassifyDto) {
		try {
			return ResponseModel.returnListSuccess(articleClassifyService.recommendlist(articleClassifyDto));
		} catch (Exception e) {
			logger.error("recommendlist error", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Long> getUpOrDownRecommend(Long classifyId,Integer flag) {
		try {
			return ResponseModel.returnListSuccess(articleClassifyService.getUpOrDownRecommend(classifyId,flag));
		} catch (Exception e) {
			logger.error("articleClassify error by getUpOrDownRecommend", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<List<ArticleClassifyVo>> getArticleClassifys(Long articleClassifyId) {
		try {
			return ResponseModel.returnListSuccess(articleClassifyService.getArticleClassifys(articleClassifyId));
		} catch (Exception e) {
			logger.error("获取ArticleClassifyVo列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<List<ArticleClassifyVo>> getArticleClassifys(Long articleClassifyId,Integer deviceType) {
		try {
			return ResponseModel.returnListSuccess(articleClassifyService.getArticleClassifys(articleClassifyId,deviceType));
		} catch (Exception e) {
			logger.error("获取ArticleClassifyVo列表失败", e);
			return ResponseModel.returnException(e);
		}
	}


	@Override
	public RpcResponse<List<Long>> getArticleClassifyIds(Long articleClassifyId) {
		try {
			return ResponseModel.returnListSuccess(articleClassifyService.getArticleClassifyIds(articleClassifyId));
		} catch (Exception e) {
			return ResponseModel.returnException(e);
		}
    }

    @Override
    public RpcResponse<Object> classifyNameCheck(ArticleClassify articleClassify) {
        try {
            articleClassifyService.classifyNameCheck(articleClassify);
            return ResponseModel.returnObjectSuccess(null);
        } catch (Exception e) {
            return ResponseModel.returnException(e);
        }
    }
}

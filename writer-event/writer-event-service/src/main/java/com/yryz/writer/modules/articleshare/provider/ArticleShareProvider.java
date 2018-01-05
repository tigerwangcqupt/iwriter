package com.yryz.writer.modules.articleshare.provider;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.articleshare.ArticleShareApi;
import com.yryz.writer.modules.articleshare.entity.ArticleShare;
import com.yryz.writer.modules.articleshare.vo.ArticleShareVo;
import com.yryz.writer.modules.articleshare.dto.ArticleShareDto;
import com.yryz.writer.modules.articleshare.service.ArticleShareService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleShareProvider implements ArticleShareApi {

	private static final Logger logger = LoggerFactory.getLogger(ArticleShareProvider.class);

	@Autowired
	private ArticleShareService articleShareService;

	/**
	*  获取ArticleShare明细
	*  @param  articleShareId
	*  @return
	* */
	public RpcResponse<ArticleShare> get(Long articleShareId) {
		try {
			return ResponseModel.returnObjectSuccess(articleShareService.get(ArticleShare.class, articleShareId));
		} catch (Exception e) {
			logger.error("获取ArticleShare明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取ArticleShare明细
	*  @param  articleShareId
	*  @return
	* */
	public RpcResponse<ArticleShareVo> detail(Long articleShareId) {
		try {
			return ResponseModel.returnObjectSuccess(articleShareService.detail(articleShareId));
		} catch (Exception e) {
			logger.error("获取ArticleShare明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取ArticleShare列表
    * @param articleShareDto
    * @return
    *
	*/
    public RpcResponse<PageList<ArticleShareVo>> list(ArticleShareDto articleShareDto) {
        try {
			 return ResponseModel.returnListSuccess(articleShareService.selectList(articleShareDto));
        } catch (Exception e) {
        	logger.error("获取ArticleShare列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<PageList<ArticleShareVo>> listByWriter(ArticleShareDto articleShareDto) {
		try {
			return ResponseModel.returnListSuccess(articleShareService.selectListByWriter(articleShareDto));
		} catch (Exception e) {
			logger.error("获取ArticleShare列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<Boolean> saveArticleShare(ArticleShare articleShare) {
		try {
			long successNum = articleShareService.saveArticleShare(articleShare);
			return ResponseModel.returnListSuccess(successNum == 1 ? true : false);
		} catch (Exception e) {
			logger.error("获取ArticleShare列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

}

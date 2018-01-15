package com.yryz.writer.modules.articlearticleclassify.provider;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.articlearticleclassify.ArticleArticleClassifyApi;
import com.yryz.writer.modules.articlearticleclassify.entity.ArticleArticleClassify;
import com.yryz.writer.modules.articlearticleclassify.vo.ArticleArticleClassifyVo;
import com.yryz.writer.modules.articlearticleclassify.dto.ArticleArticleClassifyDto;
import com.yryz.writer.modules.articlearticleclassify.service.ArticleArticleClassifyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleArticleClassifyProvider implements ArticleArticleClassifyApi {

	private static final Logger logger = LoggerFactory.getLogger(ArticleArticleClassifyProvider.class);

	@Autowired
	private ArticleArticleClassifyService articleArticleClassifyService;

	/**
	*  获取ArticleArticleClassify明细
	*  @param  articleArticleClassifyId
	*  @return
	* */
	public RpcResponse<ArticleArticleClassify> get(Long articleArticleClassifyId) {
		try {
			return ResponseModel.returnObjectSuccess(articleArticleClassifyService.get(ArticleArticleClassify.class, articleArticleClassifyId));
		} catch (Exception e) {
			logger.error("获取ArticleArticleClassify明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取ArticleArticleClassify明细
	*  @param  articleArticleClassifyId
	*  @return
	* */
	public RpcResponse<ArticleArticleClassifyVo> detail(Long articleArticleClassifyId) {
		try {
			return ResponseModel.returnObjectSuccess(articleArticleClassifyService.detail(articleArticleClassifyId));
		} catch (Exception e) {
			logger.error("获取ArticleArticleClassify明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取ArticleArticleClassify列表
    * @param articleArticleClassifyDto
    * @return
    *
	*/
    public RpcResponse<PageList<ArticleArticleClassifyVo>> list(ArticleArticleClassifyDto articleArticleClassifyDto) {
        try {
			 return ResponseModel.returnListSuccess(articleArticleClassifyService.selectList(articleArticleClassifyDto));
        } catch (Exception e) {
        	logger.error("获取ArticleArticleClassify列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<PageList<ArticleArticleClassifyVo>> queryByClassifyId(ArticleArticleClassifyDto articleArticleClassifyDto) {
		try {
			return ResponseModel.returnListSuccess(articleArticleClassifyService.queryByClassifyId(articleArticleClassifyDto));
		} catch (Exception e) {
			logger.error("获取ArticleArticleClassify列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

}

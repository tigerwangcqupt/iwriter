package com.yryz.writer.modules.articleClassify.provider;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.common.web.ResponseModel;
import com.yryz.writer.modules.articleClassify.ArticleClassifyApi;
import com.yryz.writer.modules.articleClassify.dto.ArticleClassifyDto;
import com.yryz.writer.modules.articleClassify.entity.ArticleClassify;

import com.yryz.writer.modules.articleClassify.service.ArticleClassifyService;
import com.yryz.writer.modules.articleClassify.vo.ArticleClassifyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleClassifyProvider implements ArticleClassifyApi {

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

}

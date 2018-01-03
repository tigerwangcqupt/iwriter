package com.yryz.writer.modules.articlecomment.provider;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.articlecomment.ArticleCommentApi;
import com.yryz.writer.modules.articlecomment.entity.ArticleComment;
import com.yryz.writer.modules.articlecomment.vo.ArticleCommentVo;
import com.yryz.writer.modules.articlecomment.dto.ArticleCommentDto;
import com.yryz.writer.modules.articlecomment.service.ArticleCommentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCommentProvider implements ArticleCommentApi {

	private static final Logger logger = LoggerFactory.getLogger(ArticleCommentProvider.class);

	@Autowired
	private ArticleCommentService articleCommentService;

	/**
	*  获取ArticleComment明细
	*  @param  articleCommentId
	*  @return
	* */
	public RpcResponse<ArticleComment> get(Long articleCommentId) {
		try {
			return ResponseModel.returnObjectSuccess(articleCommentService.get(ArticleComment.class, articleCommentId));
		} catch (Exception e) {
			logger.error("获取ArticleComment明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取ArticleComment明细
	*  @param  articleCommentId
	*  @return
	* */
	public RpcResponse<ArticleCommentVo> detail(Long articleCommentId) {
		try {
			return ResponseModel.returnObjectSuccess(articleCommentService.detail(articleCommentId));
		} catch (Exception e) {
			logger.error("获取ArticleComment明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取ArticleComment列表
    * @param articleCommentDto
    * @return
    *
	*/
    public RpcResponse<PageList<ArticleCommentVo>> list(ArticleCommentDto articleCommentDto) {
        try {
			 return ResponseModel.returnListSuccess(articleCommentService.selectList(articleCommentDto));
        } catch (Exception e) {
        	logger.error("获取ArticleComment列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	/**
	 * 查询写手的全部文章的用户评论分页
	 * @param articleCommentDto
	 * @return
	 */
	@Override
	public RpcResponse<PageList<ArticleCommentVo>> listByWriter(ArticleCommentDto articleCommentDto) {
		try {
			return ResponseModel.returnListSuccess(articleCommentService.selectListByWriter(articleCommentDto));
		} catch (Exception e) {
			logger.error("获取ArticleComment列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

}

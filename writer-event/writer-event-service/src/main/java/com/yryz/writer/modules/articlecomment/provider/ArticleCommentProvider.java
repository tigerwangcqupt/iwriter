package com.yryz.writer.modules.articlecomment.provider;
import com.yryz.writer.common.web.ResponseModel;
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
import org.springframework.util.Assert;

/**
 * 文章评论服务
 */
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

	@Override
	public RpcResponse<Boolean> saveArticleComment(ArticleComment articleComment) {
		try {
			Assert.notNull(articleComment.getTargetId(), "被回复评论不能为空");
			Assert.notNull(articleComment.getWriterId(), "写手不能为空");
			Assert.notNull(articleComment.getCommentUserId(), "评论用户不能为空");
			Assert.hasText(articleComment.getCommentUserNickname(), "收藏者昵称不为空");
			Assert.notNull(articleComment.getArticleId(), "文章不能为空");
			Assert.notNull(articleComment.getArticleTitle(), "文章标题不能为空");
			Assert.hasText(articleComment.getContent(), "评论内容不能为空");
			Assert.notNull(articleComment.getCommentType(), "评论类型不能为空");
			if (articleComment.getContent().length() > 200){
				throw new IllegalArgumentException("评论长度不能超过200");
			}
			return ResponseModel.returnListSuccess(articleCommentService.saveArticleComment(articleComment));
		} catch (Exception e) {
			logger.error("保存ArticleComment失败", e);
			return ResponseModel.returnException(e);
		}
	}

}

package com.yryz.writer.modules.articlecomment.entity;

import com.yryz.common.entity.GenericEntity;
import java.util.Date;
/**
 * 
  * @ClassName: ArticleComment
  * @Description: 文章评论表实体类
  * @author huyangyang
  * @date 2018-01-03 11:43:39
  *
 */
public class ArticleComment extends GenericEntity{
	

	/**
	 * 被评论ID
	 */	 
    private  Long targetId;
    

	/**
	 * 评论类型 0用户评论 1写手评论
	 */	 
    private  Integer commentType;
    

	/**
	 * 评论者写手id
	 */	 
    private  Long commentWriterId;
    

	/**
	 * 评论者用户id
	 */	 
    private  Long commentUserId;
    

	/**
	 * 评论所属文章id
	 */	 
    private  Long articleId;
    

	/**
	 * 评论内容
	 */	 
    private  String content;
    

	/**
	 * 0上架1下架
	 */	 
    private  Integer shelveFlag;
    

	/**
	 * 0正常1已删除
	 */	 
    private  Integer delFlag;
    

	/**
	 * 推荐状态 0非推荐 1 推荐
	 */	 
    private  Integer recommend;

	/**
	 * 评论用户的昵称
	 */
	private String commentUserNickname;

	/**
	 * 文章标题
	 */
	private String articleTitle;

	public Long getTargetId() {
		return this.targetId;
	}
	
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
		
	public Integer getCommentType() {
		return this.commentType;
	}
	
	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}
		
	public Long getCommentWriterId() {
		return this.commentWriterId;
	}
	
	public void setCommentWriterId(Long commentWriterId) {
		this.commentWriterId = commentWriterId;
	}
		
	public Long getCommentUserId() {
		return this.commentUserId;
	}
	
	public void setCommentUserId(Long commentUserId) {
		this.commentUserId = commentUserId;
	}
		
	public Long getArticleId() {
		return this.articleId;
	}
	
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
		
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
		
	public Integer getShelveFlag() {
		return this.shelveFlag;
	}
	
	public void setShelveFlag(Integer shelveFlag) {
		this.shelveFlag = shelveFlag;
	}
		
	public Integer getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
		
	public Integer getRecommend() {
		return this.recommend;
	}
	
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getCommentUserNickname() {
		return commentUserNickname;
	}

	public void setCommentUserNickname(String commentUserNickname) {
		this.commentUserNickname = commentUserNickname;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleTitle() {

		return articleTitle;
	}
}
package com.yryz.writer.modules.articleshare.entity;

import com.yryz.common.entity.GenericEntity;
import java.util.Date;
/**
 * 
  * @ClassName: ArticleShare
  * @Description: 文章分享详情实体类
  * @author huyangyang
  * @date 2018-01-02 20:24:27
  *
 */
public class ArticleShare extends GenericEntity{
	

	/**
	 * 创建者id(分享用户id)
	 */	 
    private  Long userId;
    

	/**
	 * 创建者昵称
	 */	 
    private  String createUserNickname;
    

	/**
	 * 文章id
	 */	 
    private  Long articleId;
    

	/**
	 * 文章标题
	 */	 
    private  String articleTitle;
    

	public Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
		
	public String getCreateUserNickname() {
		return this.createUserNickname;
	}
	
	public void setCreateUserNickname(String createUserNickname) {
		this.createUserNickname = createUserNickname;
	}
		
	public Long getArticleId() {
		return this.articleId;
	}
	
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
		
	public String getArticleTitle() {
		return this.articleTitle;
	}
	
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
		
}
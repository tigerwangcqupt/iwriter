package com.yryz.writer.modules.articlefavorite.entity;

import com.yryz.common.entity.GenericEntity;
import java.util.Date;
/**
 * 
  * @ClassName: ArticleFavorite
  * @Description: 文章收藏详情实体类
  * @author huyangyang
  * @date 2018-01-02 20:52:42
  *
 */
public class ArticleFavorite extends GenericEntity{

	/**
	 * 创建者id(收藏用户id)
	 */	 
    private  Long userId;
    

	/**
	 * 创建者昵称
	 */	 
    private  String createUserNickname;
    

	/**
	 * 创建者头像地址
	 */	 
    private  String userHeadImg;
    

	/**
	 * 文章id
	 */	 
    private  Long articleId;
    

	/**
	 * 文章标题
	 */	 
    private  String articleTitle;

	/**
	 * 写手id
	 */
	private Long writerId;



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
		
	public String getUserHeadImg() {
		return this.userHeadImg;
	}
	
	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
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

	public void setWriterId(Long writerId) {
		this.writerId = writerId;
	}

	public Long getWriterId() {

		return writerId;
	}

}
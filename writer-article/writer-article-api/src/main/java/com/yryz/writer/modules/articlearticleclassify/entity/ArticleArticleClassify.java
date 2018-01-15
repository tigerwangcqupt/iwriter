package com.yryz.writer.modules.articlearticleclassify.entity;

import com.yryz.writer.common.entity.GenericEntity;

import java.util.Date;
/**
 * 
  * @ClassName: ArticleArticleClassify
  * @Description: 文章分类关联表实体类
  * @author huyangyang
  * @date 2018-01-15 10:34:38
  *
 */
public class ArticleArticleClassify extends GenericEntity {
	

	/**
	 * 文章id
	 */	 
    private  Long articleId;
    

	/**
	 * 分类id
	 */	 
    private  Long classifyId;
    

	/**
	 * 排序
	 */	 
    private  Integer sort;
    

	/**
	 * 0未推荐 1推荐
	 */	 
    private  Integer recommend;
    

	/**
	 * 分类ID所有上级全路径
	 */	 
    private  String path;

	/**
	 * 文章标题
	 */
	private String title;
    

	public Long getArticleId() {
		return this.articleId;
	}
	
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
		
	public Long getClassifyId() {
		return this.classifyId;
	}
	
	public void setClassifyId(Long classifyId) {
		this.classifyId = classifyId;
	}
		
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
		
	public Integer getRecommend() {
		return this.recommend;
	}
	
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
		
	public String getPath() {
		return this.path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {

		return title;
	}
}
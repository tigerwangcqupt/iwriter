package com.yryz.writer.modules.articleclassify.entity;

import com.yryz.writer.common.entity.GenericEntity;

/**
 * 
  * @ClassName: ArticleClassify
  * @Description: 分类表(文章用)实体类
  * @author huyangyang
  * @date 2018-01-09 11:03:59
  *
 */
public class ArticleClassify extends GenericEntity {
	

	/**
	 * 父级id
	 */	 
    private  Long parentId;

	/**
	 * 图标
	 */	 
    private  String icon;
    

	/**
	 * 分类名称
	 */	 
    private  String classifyName;
    

	/**
	 * 分类描述
	 */	 
    private  String classifyDesc;
    

	/**
	 * 是否推荐 0未推荐 1推荐
	 */	 
    private  Integer recommendFlag;
    

	/**
	 * 排序
	 */	 
    private  Integer sort;
    

	/**
	 * 末级分类:0是,1否
	 */	 
    private  Integer lastStageFlag;
    

	/**
	 * 是否上架: 0上架1下架
	 */	 
    private  Integer shelveFlag;
    

	/**
	 * 是否删除:0正常1已删除
	 */	 
    private  Integer delFlag;

	/**
	 * 创建者昵称
	 */
	private String createUserNickName;

	public Long getParentId() {
		return this.parentId;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
		
	public String getIcon() {
		return this.icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
		
	public String getClassifyName() {
		return this.classifyName;
	}
	
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
		
	public String getClassifyDesc() {
		return this.classifyDesc;
	}
	
	public void setClassifyDesc(String classifyDesc) {
		this.classifyDesc = classifyDesc;
	}
		
	public Integer getRecommendFlag() {
		return this.recommendFlag;
	}
	
	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
	}
		
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
		
	public Integer getLastStageFlag() {
		return this.lastStageFlag;
	}
	
	public void setLastStageFlag(Integer lastStageFlag) {
		this.lastStageFlag = lastStageFlag;
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

	public String getCreateUserNickName() {
		return createUserNickName;
	}

	public void setCreateUserNickName(String createUserNickName) {
		this.createUserNickName = createUserNickName;
	}


}
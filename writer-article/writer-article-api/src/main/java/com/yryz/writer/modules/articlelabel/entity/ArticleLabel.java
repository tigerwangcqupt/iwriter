package com.yryz.writer.modules.articlelabel.entity;

import com.yryz.writer.common.entity.GenericEntity;

import java.util.Date;
/**
 * 
  * @ClassName: ArticleLabel
  * @Description: 标签表(文章用)实体类
  * @author huyangyang
  * @date 2018-01-11 19:29:39
  *
 */
public class ArticleLabel extends GenericEntity {
	

	/**
	 * 标签名称
	 */	 
    private  String labelName;
    

	/**
	 * 标签描述
	 */	 
    private  String labelDescription;
    

	/**
	 * icon图标
	 */	 
    private  String icon;
    

	/**
	 * 0上架1下架
	 */	 
    private  Integer shelveFlag;
    

	/**
	 * 0正常1已删除
	 */	 
    private  Integer delFlag;
    

	/**
	 * 排序
	 */	 
    private  Integer sort;

	/**
	 * 推荐标识
	 */
	private Integer recommendFlag;

	public String getLabelName() {
		return this.labelName;
	}
	
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
		
	public String getLabelDescription() {
		return this.labelDescription;
	}
	
	public void setLabelDescription(String labelDescription) {
		this.labelDescription = labelDescription;
	}
		
	public String getIcon() {
		return this.icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
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
		
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
	}
}
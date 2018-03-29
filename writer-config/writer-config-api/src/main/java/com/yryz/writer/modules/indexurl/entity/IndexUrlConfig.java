package com.yryz.writer.modules.indexurl.entity;



import com.yryz.writer.common.entity.GenericEntity;

import java.util.Date;

/**
 * 
  * @ClassName: IndexUrlConfig
  * @Description: 首页rul配置表实体类
  * @author wangsenyong
  * @date 2018-03-29 15:11:09
  *
 */
public class IndexUrlConfig extends GenericEntity {
	

	/**
	 * 0上架，1下架
	 */	 
    private  Integer shelveFlag;
    

	/**
	 * 配置名称
	 */	 
    private  String configName;
    

	/**
	 * 配置描述
	 */	 
    private  String configDesc;
    

	/**
	 * 1 url图 2 普通配置 
	 */	 
    private  Integer configType;
    

	/**
	 * 图片地址
	 */	 
    private  String imgUrl;
    

	/**
	 * 跳转地址
	 */	 
    private  String configHref;
    

	/**
	 * 排序
	 */	 
    private  Integer sort;
    

	/**
	 * 开始时间
	 */	 
    private  Date beginTime;
    

	/**
	 * 结束时间
	 */	 
    private  Date endTime;
    

	public Integer getShelveFlag() {
		return this.shelveFlag;
	}
	
	public void setShelveFlag(Integer shelveFlag) {
		this.shelveFlag = shelveFlag;
	}
		
	public String getConfigName() {
		return this.configName;
	}
	
	public void setConfigName(String configName) {
		this.configName = configName;
	}
		
	public String getConfigDesc() {
		return this.configDesc;
	}
	
	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}
		
	public Integer getConfigType() {
		return this.configType;
	}
	
	public void setConfigType(Integer configType) {
		this.configType = configType;
	}
		
	public String getImgUrl() {
		return this.imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
		
	public String getConfigHref() {
		return this.configHref;
	}
	
	public void setConfigHref(String configHref) {
		this.configHref = configHref;
	}
		
	public Integer getSort() {
		return this.sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
		
	public Date getBeginTime() {
		return this.beginTime;
	}
	
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
		
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
		
}
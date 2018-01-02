package com.yryz.writer.modules.indexcolumn.entity;

import com.yryz.common.entity.GenericEntity;

/**
 * 
  * @ClassName: IndexColumn
  * @Description: 写手首页栏目表实体类
  * @author huyangyang
  * @date 2018-01-02 10:04:46
  *
 */
public class IndexColumn extends GenericEntity{
	

	/**
	 * 栏目名称
	 */	 
    private  String itemName;
    

	/**
	 * 栏目访问路径
	 */	 
    private  String url;
    

	public String getItemName() {
		return this.itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
		
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
		
}
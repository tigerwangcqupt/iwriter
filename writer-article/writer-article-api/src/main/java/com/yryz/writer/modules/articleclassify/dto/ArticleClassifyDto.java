package com.yryz.writer.modules.articleclassify.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: ArticleClassifyDto
 * @Description: ArticleClassifyDto
 * @author huyangyang
 * @date 2018-01-09 11:03:59
 *
 */
public class ArticleClassifyDto extends PageList {

    /** 分类名称 */
    private String classifyName;

    /** 上级id */
    private String  parentId;

    /** 上级分类名称 */
    private String parentClassifyName;

    /**
     *排序属性
     */
    private String orderFiled;

    /**
     *排序值 asc desc
     */
    private String orderValue;

    /** 排序 */
    private String orderStr;

    /**
     * 是否上架: 0上架1下架
     */
    private  Integer shelveFlag;


    /**
     * 默认需要分页
     */
    private boolean pageFlag = true;
    /**
     * 是否删除:0正常1已删除
     */
    private  Integer delFlag;

    /**
     * 分类类型: 1:android;2:ios
     */
    private  Integer devType;

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public String getClassifyName() {

        return classifyName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setOrderFiled(String orderFiled) {
        this.orderFiled = orderFiled;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getOrderFiled() {

        return orderFiled;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public String getOrderStr() {

        return orderStr;
    }

    public String getParentClassifyName() {
        return parentClassifyName;
    }

    public void setParentClassifyName(String parentClassifyName) {
        this.parentClassifyName = parentClassifyName;
    }

    public void setShelveFlag(Integer shelveFlag) {
        this.shelveFlag = shelveFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getShelveFlag() {

        return shelveFlag;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public boolean isPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(boolean pageFlag) {
        this.pageFlag = pageFlag;
    }
}

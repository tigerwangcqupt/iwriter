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

    public String getParentClassifyName() {
        return parentClassifyName;
    }

    public void setParentClassifyName(String parentClassifyName) {
        this.parentClassifyName = parentClassifyName;
    }
}

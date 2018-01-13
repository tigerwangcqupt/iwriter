package com.yryz.writer.modules.articlelabel.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: ArticleLabelDto
 * @Description: ArticleLabelDto
 * @author huyangyang
 * @date 2018-01-11 19:29:39
 *
 */
public class ArticleLabelDto extends PageList {


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
     * 是否删除:0正常1已删除
     */
    private  Integer delFlag;

    /**
     * 标签名称
     */
    private  String labelName;


    public void setOrderFiled(String orderFiled) {
        this.orderFiled = orderFiled;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public void setShelveFlag(Integer shelveFlag) {
        this.shelveFlag = shelveFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getOrderFiled() {

        return orderFiled;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public Integer getShelveFlag() {
        return shelveFlag;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelName() {

        return labelName;
    }
}

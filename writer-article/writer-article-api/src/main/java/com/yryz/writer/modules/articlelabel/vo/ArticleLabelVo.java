package com.yryz.writer.modules.articlelabel.vo;
import java.io.Serializable;
import com.yryz.writer.modules.articlelabel.entity.ArticleLabel;

/**
 * @ClassName: ArticleLabelVo
 * @Description: ArticleLabelVo
 * @author huyangyang
 * @date 2018-01-11 19:29:39
 *
 */
public class ArticleLabelVo implements Serializable {

    //唯一ID
    private Long kid;

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
     * 创建者id
     */
    private String createUserId;

    /**
     * 创建者姓名
     */
    private String createUserName;

    /**  创建时间  */
    private String createDate;

    public Long getKid() {
        return kid;
    }

    public String getLabelName() {
        return labelName;
    }

    public String getLabelDescription() {
        return labelDescription;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getShelveFlag() {
        return shelveFlag;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public Integer getSort() {
        return sort;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setShelveFlag(Integer shelveFlag) {
        this.shelveFlag = shelveFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}

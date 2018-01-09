package com.yryz.writer.modules.articleclassify.vo;
import java.io.Serializable;

/**
 * @ClassName: ArticleClassifyVo
 * @Description: ArticleClassifyVo
 * @author huyangyang
 * @date 2018-01-09 11:03:59
 *
 */
public class ArticleClassifyVo implements Serializable {

    /**  分类名称  */
    private String classifyName;

    /**  创建者昵称  */
    private String createUser;

    /**  创建时间  */
    private String createDate;

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

    //唯一ID
    private Long kid;

    //内容数（文章总数）
    private Long articleAmount;

    /**
     * 图标
     */
    private  String icon;

    public String getClassifyName() {
        return classifyName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public Long getKid() {
        return kid;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public Integer getShelveFlag() {
        return shelveFlag;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public String getCreateUserNickName() {
        return createUserNickName;
    }

    public void setShelveFlag(Integer shelveFlag) {
        this.shelveFlag = shelveFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public void setCreateUserNickName(String createUserNickName) {
        this.createUserNickName = createUserNickName;
    }

    public void setArticleAmount(Long articleAmount) {
        this.articleAmount = articleAmount;
    }

    public Long getArticleAmount() {
        return articleAmount;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

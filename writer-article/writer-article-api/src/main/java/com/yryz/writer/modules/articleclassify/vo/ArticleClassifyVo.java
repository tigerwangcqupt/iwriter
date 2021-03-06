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

    /**
     * 父级id
     */
    private  Long parentId;
    /**
     * 父级名称
     */
    private String parentClassifyName;
    /**
     * 图标
     */
    private  String icon;
    /**
     * 分类名称
     * */
    private String classifyName;
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
    /**
     * 创建者id
     */
    private String createUserId;
    /**
     * 创建者昵称
     * */
    private String createUser;
    /**
     * 创建者昵称
     * */
    private String createUserName;
    /**
     * 创建时间
     * */
    private String createDate;

    //唯一ID
    private Long kid;

    //内容数（文章总数）
    private Long articleAmount;

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
    public String getParentClassifyName() {
        return parentClassifyName;
    }

    public void setParentClassifyName(String parentClassifyName) {
        this.parentClassifyName = parentClassifyName;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setClassifyDesc(String classifyDesc) {
        this.classifyDesc = classifyDesc;
    }

    public Long getParentId() {

        return parentId;
    }

    public String getClassifyDesc() {
        return classifyDesc;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserId() {

        return createUserId;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserName() {

        return createUserName;
    }

    public Integer getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(Integer recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLastStageFlag() {
        return lastStageFlag;
    }

    public void setLastStageFlag(Integer lastStageFlag) {
        this.lastStageFlag = lastStageFlag;
    }
}

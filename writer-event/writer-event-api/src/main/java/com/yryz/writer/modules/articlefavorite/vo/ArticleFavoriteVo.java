package com.yryz.writer.modules.articlefavorite.vo;
import java.io.Serializable;
import java.util.Date;

import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;

/**
 * @ClassName: ArticleFavoriteVo
 * @Description: ArticleFavoriteVo
 * @author huyangyang
 * @date 2018-01-02 20:52:42
 *
 */
public class ArticleFavoriteVo implements Serializable {

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章id
     */
    private  Long articleId;

    /**
     * 创建者id(收藏用户id)
     */
    private  Long userId;

    private Date createDate;

    private String content;

    /**
     * 创建者昵称
     */
    private  String createUserNickname;


    /**
     * 创建者头像地址
     */
    private  String userHeadImg;

    /**
     * 写手id
     */
    private Long writerId;

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateUserNickname(String createUserNickname) {
        this.createUserNickname = createUserNickname;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public String getArticleTitle() {

        return articleTitle;
    }

    public Long getArticleId() {
        return articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getContent() {
        return content;
    }

    public String getCreateUserNickname() {
        return createUserNickname;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public Long getWriterId() {
        return writerId;
    }
}

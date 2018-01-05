package com.yryz.writer.modules.articlecomment.vo;
import java.io.Serializable;
import java.util.Date;

import com.yryz.writer.modules.articlecomment.entity.ArticleComment;

/**
 * @ClassName: ArticleCommentVo
 * @Description: ArticleCommentVo
 * @author huyangyang
 * @date 2018-01-03 11:43:39
 *
 */
public class ArticleCommentVo implements Serializable {

    /**  评论id  */
    private Long commentId;

    /**  文章id  */
    private Long articleId;

    /**  文章标题  */
    private String articleTitle;

    /**  评论用户昵称  */
    private String commentUserNickname;

    /**  评论用户id  */
    private Long userId;

    /**  评论用户头像  */
    private String userHeadImg;

    /**
     * 评论内容
     */
    private String content;

    /**
     *评论时间
     */
    private Date createDate;


    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public String getContent() {
        return content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getCommentUserNickname() {
        return commentUserNickname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setCommentUserNickname(String commentUserNickname) {
        this.commentUserNickname = commentUserNickname;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }
}

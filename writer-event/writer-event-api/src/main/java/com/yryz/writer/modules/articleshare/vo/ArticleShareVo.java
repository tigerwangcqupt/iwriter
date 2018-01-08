package com.yryz.writer.modules.articleshare.vo;
import java.io.Serializable;
import java.util.Date;

import com.yryz.writer.modules.articleshare.entity.ArticleShare;

/**
 * @ClassName: ArticleShareVo
 * @Description: ArticleShareVo
 * @author huyangyang
 * @date 2018-01-02 20:24:27
 *
 */
public class ArticleShareVo implements Serializable {

    /**
     * 文章标题
     */
    private  String articleTitle;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建者id(分享用户id)
     */
    private  Long userId;

    /**
     * 内容：【用户昵称】分享了你的【文章标题】
     */
    private String content;

    /**
     * 创建者昵称
     */
    private  String createUserNickname;

    public String getArticleTitle() {
        return articleTitle;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getCreateUserNickname() {
        return createUserNickname;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateUserNickname(String createUserNickname) {
        this.createUserNickname = createUserNickname;
    }
}

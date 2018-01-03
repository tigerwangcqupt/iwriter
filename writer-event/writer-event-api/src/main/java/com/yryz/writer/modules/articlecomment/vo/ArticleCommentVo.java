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

    /**
     * 评论内容
     */
    private String content;

    /**
     *评论时间
     */
    private Date createDate;

    /**
     *评论标题
     */
    private String title;

    /**
     * 评论用户昵称
     */
    private String userName;

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getUserName() {
        return userName;
    }
}

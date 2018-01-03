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
     *评论时间
     */
    private String title;

}

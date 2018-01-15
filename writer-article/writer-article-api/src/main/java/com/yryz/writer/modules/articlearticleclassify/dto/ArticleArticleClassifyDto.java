package com.yryz.writer.modules.articlearticleclassify.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: ArticleArticleClassifyDto
 * @Description: ArticleArticleClassifyDto
 * @author huyangyang
 * @date 2018-01-15 10:34:38
 *
 */
public class ArticleArticleClassifyDto extends PageList {

    /**  文章分类id  */
    private Long articleClassifyId;

    public Long getArticleClassifyId() {
        return articleClassifyId;
    }

    public void setArticleClassifyId(Long articleClassifyId) {
        this.articleClassifyId = articleClassifyId;
    }
}

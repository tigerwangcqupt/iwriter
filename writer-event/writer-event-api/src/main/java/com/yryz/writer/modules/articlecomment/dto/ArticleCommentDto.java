package com.yryz.writer.modules.articlecomment.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: ArticleCommentDto
 * @Description: ArticleCommentDto
 * @author huyangyang
 * @date 2018-01-03 11:43:39
 *
 */
public class ArticleCommentDto extends PageList {

    /**  写手id  */
    public Long custId;

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getCustId() {

        return custId;
    }
}

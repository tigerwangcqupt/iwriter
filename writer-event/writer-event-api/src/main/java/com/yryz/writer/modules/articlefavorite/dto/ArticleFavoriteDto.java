package com.yryz.writer.modules.articlefavorite.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: ArticleFavoriteDto
 * @Description: ArticleFavoriteDto
 * @author huyangyang
 * @date 2018-01-02 20:52:42
 *
 */
public class ArticleFavoriteDto extends PageList {
    /**  写手id  */
    public String custId;

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustId() {

        return custId;
    }
}

package com.yryz.writer.modules.articleshare.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: ArticleShareDto
 * @Description: ArticleShareDto
 * @author huyangyang
 * @date 2018-01-02 20:24:27
 *
 */
public class ArticleShareDto extends PageList {

    /**  写手id  */
    public String custId;

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustId() {

        return custId;
    }
}

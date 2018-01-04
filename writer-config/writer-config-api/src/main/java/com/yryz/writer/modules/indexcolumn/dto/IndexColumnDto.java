package com.yryz.writer.modules.indexcolumn.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: IndexColumnDto
 * @Description: IndexColumnDto
 * @author huyangyang
 * @date 2018-01-02 10:04:46
 *
 */
public class IndexColumnDto extends PageList {

    /**  写手id  */
    public Long custId;

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getCustId() {

        return custId;
    }

}

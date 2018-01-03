package com.yryz.writer.modules.message.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * File Name: MessageDto
 * Package Name: com.yryz.writer.modules.message.dto
 * Description: TODO
 *
 * @author huyangyang
 * @create 2018-01-03 17:04
 **/
public class MessageDto extends PageList {

    /**  写手id  */
    public String custId;


    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustId() {

        return custId;
    }
}

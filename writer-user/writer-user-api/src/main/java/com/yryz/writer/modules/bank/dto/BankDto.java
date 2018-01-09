package com.yryz.writer.modules.bank.dto;

import com.yryz.component.rpc.dto.PageList;

import java.util.List;

/**
 * @ClassName: BankDto
 * @Description: BankDto
 * @author zhongying
 * @date 2017-12-29 15:38:16
 *
 */
public class BankDto extends PageList {

    //创建者id
    private String createUserId;

    //创建者id集合
    private List<String> writerIdList;

    public List<String> getWriterIdList() {
        return writerIdList;
    }

    public void setWriterIdList(List<String> writerIdList) {
        this.writerIdList = writerIdList;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
}

package com.yryz.writer.modules.bank.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: BankDto
 * @Description: BankDto
 * @author zhongying
 * @date 2017-12-29 15:38:16
 *
 */
public class BankDto extends PageList {

    //创建者id
    private String creatUserId;

    public String getCreatUserId() {
        return creatUserId;
    }

    public void setCreatUserId(String creatUserId) {
        this.creatUserId = creatUserId;
    }
}

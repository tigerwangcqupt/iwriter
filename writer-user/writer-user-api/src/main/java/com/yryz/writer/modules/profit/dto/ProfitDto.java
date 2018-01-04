package com.yryz.writer.modules.profit.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: ProfitDto
 * @Description: ProfitDto
 * @author zhongying
 * @date 2017-12-29 15:36:15
 *
 */
public class ProfitDto extends PageList {

    private Long createUserId;

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
}

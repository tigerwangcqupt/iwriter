package com.yryz.writer.modules.fans.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @author luohao
 * @ClassName: FansDto
 * @Description: FansDto
 * @date 2018-01-02 20:08:19
 */
public class FansDto extends PageList {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

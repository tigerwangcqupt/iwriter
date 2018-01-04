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
    private String createUserId;
    private Long writerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }
}

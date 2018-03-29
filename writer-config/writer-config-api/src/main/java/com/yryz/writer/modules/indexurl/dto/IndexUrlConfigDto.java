package com.yryz.writer.modules.indexurl.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @ClassName: IndexUrlConfigDto
 * @Description: IndexUrlConfigDto
 * @author wangsenyong
 * @date 2018-03-29 15:11:09
 *
 */
public class IndexUrlConfigDto extends PageList {

    /**
     * 业务主键
     */
    private Long kid;

    /**
     * 0上架，1下架
     */
    private  Integer shelveFlag;

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public Integer getShelveFlag() {
        return shelveFlag;
    }

    public void setShelveFlag(Integer shelveFlag) {
        this.shelveFlag = shelveFlag;
    }
}

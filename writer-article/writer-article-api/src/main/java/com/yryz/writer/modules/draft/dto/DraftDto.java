package com.yryz.writer.modules.draft.dto;

import com.yryz.component.rpc.dto.PageList;

/**
 * @author luohao
 * @ClassName: DraftDto
 * @Description: DraftDto
 * @date 2017-12-29 14:40:13
 */
public class DraftDto extends PageList {
    private String createUserId;
    private Integer draftStatus;
    private Integer status;

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getDraftStatus() {
        return draftStatus;
    }

    public void setDraftStatus(Integer draftStatus) {
        this.draftStatus = draftStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

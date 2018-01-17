package com.yryz.writer.modules.draft.vo;

import java.io.Serializable;

public class UserVo implements Serializable{
    private Long kid;
    private String userName;

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

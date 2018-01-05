package com.yryz.writer.modules.fans.vo;

import java.io.Serializable;

import com.yryz.writer.modules.fans.entity.Fans;

/**
 * @author luohao
 * @ClassName: FansVo
 * @Description: FansVo
 * @date 2018-01-02 20:08:19
 */
public class FansVo implements Serializable {
    private Long userId;
    private String nickName;
    private String headImg;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

}

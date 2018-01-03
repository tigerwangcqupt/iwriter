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
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

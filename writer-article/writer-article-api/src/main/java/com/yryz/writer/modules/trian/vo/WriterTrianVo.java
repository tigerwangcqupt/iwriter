package com.yryz.writer.modules.trian.vo;

import com.yryz.writer.modules.trian.entity.WriterTrian;

/**
 * @Author: liupan
 * @Path: com.yryz.writer.modules.trian.vo
 * @Desc:
 * @Date: 2018/5/29.
 */
public class WriterTrianVo extends WriterTrian {

    private String trainModeStr;

    private String trainTimeStr;

    public String getTrainModeStr() {
        return trainModeStr;
    }

    public void setTrainModeStr(String trainModeStr) {
        this.trainModeStr = trainModeStr;
    }

    public String getTrainTimeStr() {
        return trainTimeStr;
    }

    public void setTrainTimeStr(String trainTimeStr) {
        this.trainTimeStr = trainTimeStr;
    }
}

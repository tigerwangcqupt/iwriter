package com.yryz.writer.modules.id.api;


import com.yryz.writer.common.exception.YyrzPcException;

import java.util.List;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2017/8/15
 * @description 发号器服务
 */
public interface IdAPI {

    /**
     * 根据type获取id
     * @param type
     * @return
     * @throws YyrzPcException
     */
    Long getId(String type) throws YyrzPcException;


    /**
     * 基于Twitter的分布式自增ID算法Snowflake实现分布式有序
     * @return 返回18位的自增ID
     * @throws YyrzPcException
     */
    Long getSnowflakeId() throws YyrzPcException;

    /**
     * 返回指定个数的id
     * 基于Twitter的分布式自增ID算法Snowflake实现分布式有序
     * @param num
     * @return
     * @throws YyrzPcException
     */
    List<Long> getSnowflakeIds(Integer num) throws YyrzPcException;


}

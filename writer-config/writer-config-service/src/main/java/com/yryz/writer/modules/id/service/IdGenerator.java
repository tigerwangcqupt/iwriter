
package com.yryz.writer.modules.id.service;


import com.yryz.writer.modules.id.exception.IdGenerateException;

/**
 * @author zhangkun
 * @version 1.0
 * @date 2017/8/14
 * @description
 * 分布式自增ID生成算法，目前仅提供Snowflake实现
 * 参见: https://github.com/baidu/uid-generator
 */
public interface IdGenerator {

    /**
     * Get a unique ID
     *
     * @return
     * @throws IdGenerateException
     */
    long getID() throws IdGenerateException;

    /**
     * Parse the  into elements which are used to generate the. <br>
     * Such as timestamp & workerId & sequence...
     *
     * @param uid
     * @return Parsed info
     */
    String parseID(long uid);

}

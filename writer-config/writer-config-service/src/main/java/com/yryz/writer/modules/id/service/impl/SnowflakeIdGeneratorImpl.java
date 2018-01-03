package com.yryz.writer.modules.id.service.impl;/*package com.yryz.qstone.modules.id.service.impl;
package com.yryz.capital.service.modules.id.service.impl;

import com.yryz.capital.service.modules.id.service.IdGenerator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

*/
/**
 * @author zhangkun
 * @version 1.0
 * @date 2017/8/14
 * @description
 *  基于Twitter的分布式自增ID算法Snowflake实现分布式有序
 *  开源实现参见: https://github.com/baidu/uid-generator
 *//*

@Service
public class SnowflakeIdGeneratorImpl implements IdGenerator, InitializingBean {
    private final long twepoch = 1288834974657L;// 起始标记点，作为基准
    private final long workerIdBits = 5L;//只允许workid的范围为：0-1023
    private final long datacenterIdBits = 5L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public void init(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    @Override
    public Long nextLong() {
        return this.nextId();
    }

    @Override
    public String next() {
        return String.valueOf(this.nextLong());
    }

    protected synchronized Long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {//闰秒
            long offset = lastTimestamp - timestamp;
            if (offset <= 5) {
                try {
                    wait(offset << 1);
                    timestamp = timeGen();
                    if (timestamp < lastTimestamp) {
                        throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
            }
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    */
/**
     * 保证返回的毫秒数在参数之后
     *
     * @param lastTimestamp
     * @return
     *//*

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    */
/**
     * 获得系统当前毫秒数
     *
     * @return
     *//*

    protected long timeGen() {
        return System.currentTimeMillis();
    }

}
*/

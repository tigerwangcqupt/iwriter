/*
 * RedisCache.java
 * Copyright (c) 2011,融众网络技术有限公司(www.11186.com)
 * All rights reserved.
 * ---------------------------------------------------------------------
 * 2011-04-01 Created
 */
package com.yryz.common.redis;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yryz.common.context.Context;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * <p>
 * Redis的工具类，功能有对对象的读写，过期时间设置，选择Redis库
 * </p>
 *
 * @author kinjoYang
 * @version 1.0 2011-04-01
 * @since 1.0
 */
public class RedisPool {

    private int timeout = 20000;

    private Logger logger = LoggerFactory.getLogger(RedisPool.class);

    private JedisPoolConfig poolConfig = new JedisPoolConfig();

    private JedisPool jedisPool;

    private static RedisPool redisPool = new RedisPool();

    private RedisPool() {
        init();
    }

    public static RedisPool getInstance() {
        return redisPool;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * 初始化客户端
     * @return
     */
    private boolean init() {
        try {
            String host = Context.getProperty("jedis.host");
            int port = Integer.parseInt(Context.getProperty("jedis.port"));
            String password = Context.getProperty("jedis.password");
            String maxActive = Context.getProperty("jedis.maxActive");
            String maxIdle = Context.getProperty("jedis.maxIdle");
            String minIdle = Context.getProperty("jedis.minIdle");
            String maxWait = Context.getProperty("jedis.maxWait");
            String minEvictableIdleTimeMillis = Context.getProperty("jedis.minEvictableIdleTimeMillis");
            String timeBetweenEvictionRunsMillis = Context.getProperty("jedis.timeBetweenEvictionRunsMillis");
            poolConfig.setMaxTotal(Integer.parseInt(StringUtils.isBlank(maxActive) ? "1000" : maxActive.trim()));
            poolConfig.setMaxIdle(Integer.parseInt(StringUtils.isBlank(maxIdle) ? "1000" : maxIdle.trim()));
            poolConfig.setMinIdle(Integer.parseInt(StringUtils.isBlank(minIdle) ? "10" : minIdle.trim()));
            poolConfig.setMaxWaitMillis(Integer.parseInt(StringUtils.isBlank(maxWait) ? "20000" : maxWait.trim()));
            // 设定多长时间视为失效链接
            if (StringUtils.isNotBlank(minEvictableIdleTimeMillis)) {
                poolConfig.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis.trim()));
            }
            // 设定每隔多长时间进行有效检查与上面参数同时使用
            if (StringUtils.isNotBlank(timeBetweenEvictionRunsMillis)) {
                poolConfig.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis.trim()));
            }

            if (StringUtils.isBlank(password)) {
                jedisPool = new JedisPool(poolConfig, host, port, timeout);
            } else {
                jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
            }
            logger.info("initialize Redis Sucessful.");
            return Boolean.TRUE;
        } catch (JedisConnectionException e) {
            logger.error(" JedisConnectionException :", e);
        } catch (NumberFormatException e) {
            logger.error(" NumberFormatException :", e);
        } catch (Exception e) {
            logger.error(" Exception :", e);
        }
        return Boolean.FALSE;
    }

}

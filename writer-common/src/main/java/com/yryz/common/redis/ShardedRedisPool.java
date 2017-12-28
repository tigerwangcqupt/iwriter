package com.yryz.common.redis;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yryz.common.context.Context;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class ShardedRedisPool {

    private static Logger logger = Logger.getLogger(RedisPool.class);

    /**
     * Jedis 线程池
     */
    private static final Map<String, ShardedJedisPool> POOL_CACHE = new Hashtable<String, ShardedJedisPool>();

    private ShardedRedisPool() throws Exception {
        initPool();
    }

    /**
     * 初始化连接
     */
    private void initPool() throws Exception {
//		String source = PropertiesOperate.getRedisConfig("SOURCE");
        String source = Context.getProperty("REDIS_SOURCE");
        String sourceArr[] = source.split(",");
        for (int i = 0; i < sourceArr.length; i++) {
            String s = sourceArr[i];
            try {
                load(s);
            } catch (Exception e) {
                logger.error("Init Redis Pool(" + s + ") Failed! " + e.getMessage());
                throw new Exception(e);
            }
        }
    }

    private void load(String source) {
        logger.debug("Init Redis Pool " + source);

        String HOST = Context.getProperty("REDIS_HOST_" + source);
        String PORT = Context.getProperty("REDIS_PORT_" + source);
        String PASSWORD = Context.getProperty("REDIS_PASSWORD_" + source);
        logger.debug("REDIS_HOST=" + HOST + ";REDIS_PORT=" + PORT + ";REDIS_PASSWORD="
                + PASSWORD);

        String[] hosts = HOST.split("\\;");
        String[] ports = PORT.split("\\;");
        String[] passwords = null;
        if (PASSWORD != null && PASSWORD.length() > 0) {
            passwords = PASSWORD.split("\\;");
        }
        List<JedisShardInfo> infos = new ArrayList<JedisShardInfo>();
        for (int j = 0; j < hosts.length; j++) {
            JedisShardInfo shardInfo = new JedisShardInfo(hosts[j],
                    Integer.parseInt(ports[j]));
            if (PASSWORD != null && PASSWORD.trim().length() > 0) {
                if (passwords[j] != null && passwords[j].length() > 0) {
                    shardInfo.setPassword(passwords[j]);
                }
            }
            infos.add(shardInfo);
        }
        JedisPoolConfig config = getJedisPoolConfig();

        ShardedJedisPool pool = new ShardedJedisPool(config,
                infos,
                Hashing.MURMUR_HASH,
                Sharded.DEFAULT_KEY_TAG_PATTERN);
        POOL_CACHE.put(source, pool);
        logger.info("Init Redis Pool " + source + " Over");
    }

    private JedisPoolConfig getJedisPoolConfig() {
        String MAXACTIVE = Context.getProperty("REDIS_MAXACTIVE");
        String MAXIDLE = Context.getProperty("REDIS_MAXIDLE");
        String MINIDLE = Context.getProperty("REDIS_MINIDLE");
        String MAXWAIT = Context.getProperty("REDIS_MAXWAIT");
        String isTestOnBorrow = Context.getProperty("REDIS_TESTONBORROW");
        String minEvictableIdleTimeMillis = Context.getProperty("REDIS_minEvictableIdleTimeMillis");
        String timeBetweenEvictionRunsMillis = Context.getProperty("REDIS_timeBetweenEvictionRunsMillis");
        String isTestOnReturn = Context.getProperty("REDIS_ISTESTONRETURN");
        String isTestWhileIdle = Context.getProperty("REDIS_ISTESTWHILEIDLE");

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(StringUtils.isBlank(MAXACTIVE) ? "200" : MAXACTIVE.trim()));
        config.setMaxIdle(Integer.parseInt(StringUtils.isBlank(MAXIDLE) ? "50" : MAXIDLE.trim()));
        config.setMinIdle(Integer.parseInt(StringUtils.isBlank(MINIDLE) ? "10" : MINIDLE.trim()));
        config.setMaxWaitMillis(Integer.parseInt(StringUtils.isBlank(MAXWAIT) ? "10000" : MAXWAIT.trim()));
        config.setTestOnBorrow(Boolean.parseBoolean(isTestOnBorrow));
        if (StringUtils.isBlank(isTestOnReturn)) {
            config.setTestOnReturn(Boolean.TRUE);
        } else {
            config.setTestOnReturn(Boolean.parseBoolean(isTestOnReturn));
        }
        if (StringUtils.isBlank(isTestWhileIdle)) {
            config.setTestWhileIdle(Boolean.TRUE);
        } else {
            config.setTestWhileIdle(Boolean.parseBoolean(isTestWhileIdle));
        }
        // 设定多长时间视为失效链接
        if (StringUtils.isNotBlank(minEvictableIdleTimeMillis)) {
            config.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis.trim()));
        }
        // 设定每隔多长时间进行有效检查与上面参数同时使用
        if (StringUtils.isNotBlank(timeBetweenEvictionRunsMillis)) {
            config.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis.trim()));
        }
        return config;
    }

    /**
     * 获得redis连接
     *
     * @return
     */
    public synchronized ShardedJedis getSession(String source) {
        ShardedJedisPool pool = POOL_CACHE.get(source);
        if (pool != null) {
            ShardedJedis jedis = null;
            jedis = pool.getResource();
//			logger.info( "[" + source + "] status Active:" + pool.getNumActive() + " , Idle:" + pool.getNumIdle() + " , Waiters:" + pool.getNumWaiters());
            return jedis;
        } else {
            return null;
        }
    }

    /**
     * 获取连接信息
     *
     * @param source
     * @return
     */
    public ShardedJedisPool getShardedJedisPool(String source) {
        return POOL_CACHE.get(source);
    }

    /**
     * 释放redis连接
     *
     * @param jedis
     */
    public void releaseSession(ShardedJedis jedis, String source) {
//	    if (POOL_CACHE.get(source) != null && jedis != null)
//	    {
//	        try
//	        {
//	            POOL_CACHE.get(source).returnResource(jedis);
//	        }
//	        catch (Exception e)
//	        {
//	            POOL_CACHE.get(source).returnBrokenResource(jedis);
//	        }
//	    }
        if (jedis != null) {
            jedis.close();
        }
    }

}

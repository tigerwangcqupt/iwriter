package com.yryz.writer.modules.writer.service.redis;

import com.yryz.service.api.api.exception.RedisOptException;
import com.yryz.writer.common.redis.utils.JedisUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * @author zhongying
 * @version 1.0
 * @date 2017年11月20日 下午5:10:36
 * @Description 消息的redis存储
 */
@Service
public class TokenRedis {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public final static int LOCK_EXPIRE_DEFAULT = 60;
	

	public static final String MESSAGE = "yryz.writer.token";
	
	public static String getMessagekey(String custId){
		return MESSAGE +"."+ custId;
	}
	
	
    /**
     * 查询消息已读状态
     */
    public String getToken(String custId){
		String key = getMessagekey(custId);
		try {
			String token = JedisUtils.get(key);
			return token;
		} catch (Exception e) {
			logger.error("[getToken] error", e);
			throw new RedisOptException(e);
		}
    }
    

    /**
     * 添加用户token
     * @param custId
     * @param tokenValue
     */
    public boolean addToken(String custId , String tokenValue){
		String key = getMessagekey(custId);
		String lockKey = key + "_LOCK";

		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);

		try {
			long lock = JedisUtils.setnx(lockKey, "LOCK",0);
			if (lock > 0) {
				if (0 == JedisUtils.expire(lockKey, LOCK_EXPIRE_DEFAULT)) {// 设置失效时间失败，则删除lock，抛出异常
					JedisUtils.del(lockKey);
					throw new RedisOptException("addUnread's lock setting fault");
				}
				JedisUtils.set(key, tokenValue,0 );
				JedisUtils.del(lockKey);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			JedisUtils.del(lockKey);
			logger.error("[addToken] error", e);
			throw new RedisOptException(e);
		}
    }
	

}

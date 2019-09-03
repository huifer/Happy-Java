package com.huifer.happy.redis.service;

import com.huifer.happy.common.entity.base.RedisKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 描述:
 * redis可视化
 *
 * @author huifer
 * @date 2019-09-03
 */
@Service
public class RedisViewService {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	protected static final Logger log = LoggerFactory.getLogger(RedisViewService.class);



	/**
	 * 查询redis中key
	 *
	 * @param isSystem 系统定义
	 * @return
	 */
	public List<String> queryKey(boolean isSystem) throws Exception {
		if (isSystem) {
			// 只查询RedisKeys
			try {
				List<String> redisKeys = getRedisKeys();
				return redisKeys;
			} catch (IllegalAccessException e) {
				log.error("获取Java中redis-key异常,{}", e);
				return null;
			}
		} else {
			Set<String> keys = stringRedisTemplate.keys("*");
			return keys.stream().collect(Collectors.toList());
		}
	}


	/**
	 * 获取RedisKey的所有value
	 *
	 * @return redis_keys
	 */
	private List<String> getRedisKeys() throws IllegalAccessException {
		RedisKeys redisKeys = new RedisKeys();
		Class<RedisKeys> redisKeysClass = RedisKeys.class;
		Field[] fields = redisKeysClass.getDeclaredFields();
		List<String> res = new ArrayList<>();
		for (Field field : fields) {
			//设置是否允许访问，不是修改原来的访问权限修饰词。
			field.setAccessible(true);
			String o = (String) field.get(redisKeys);
			res.add(o);
		}
		return res;
	}
}

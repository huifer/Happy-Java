package com.huifer.happy.redis.service;

import com.huifer.happy.common.entity.base.RedisKeys;
import com.huifer.happy.redis.entity.RedisKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
	private RedisTemplate redisTemplate;

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
			Set<String> keys = redisTemplate.keys("*");
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

	/**
	 * 返回所有类
	 *
	 * @return {@link RedisKey} set集合
	 */
	public Set<RedisKey> keys() {
		log.trace("开始获取所有redis-key");
		Set keys = redisTemplate.keys("*");Set<RedisKey> redisKeys = new TreeSet<>();
		keys.forEach(k -> {
			RedisKey redisKey = new RedisKey();
			redisKey.setKey(k);
			redisKey.setDataType(redisTemplate.type(k));
			redisKey.setValue(getValue(k, redisTemplate.type(k)));
			redisKeys.add(redisKey);
		});
		log.info("获取到的redis-key={}", redisKeys);
		return redisKeys;
	}

	/**
	 * 获取redis中k-v
	 *
	 * @param key      需要搜索的key
	 * @param dataType 当前key的类型{@link DataType}
	 * @return Object value
	 */
	private Object getValue(Object key, DataType dataType) {
		Object value = null;
		switch (dataType) {
			case SET:
				SetOperations setOperations = redisTemplate.opsForSet();
				value = setOperations.members(key);
				break;
			case HASH:
				HashOperations hashOperations = redisTemplate.opsForHash();
				value = hashOperations.entries(key);
				break;
			case LIST:
				ListOperations listOperations = redisTemplate.opsForList();
				value = listOperations.range(key, 0, -1);
				break;
			case ZSET:
				ZSetOperations zSetOperations = redisTemplate.opsForZSet();
				value = zSetOperations.range(key, 0, -1);
				break;
			case STRING:
				ValueOperations valueOperations = redisTemplate.opsForValue();
				value = valueOperations.get(key);
				break;
			default:
				break;
		}
		return value;

	}



}

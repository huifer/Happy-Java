package com.huifer.happy.redis.service;

import com.huifer.happy.interfaces.redis.RedisOperationInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 * 默认redis操作
 *
 * @author huifer
 * @date 2019-08-31
 */
@Service
public class DefaultRedisOpService implements RedisOperationInterface {
	private static final Logger log = LoggerFactory.getLogger(DefaultRedisOpService.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public String getKeyString(String key) throws Exception {
		log.trace("开始获取redis值");
		try {
			String value = stringRedisTemplate.opsForValue().get(key);
			log.info("redis取值key={},value={}", key, value);
			return value;
		} catch (Exception e) {
			log.error("redis值获取失败={}", e);
			return null;
		}
	}

	@Override
	public String setKeyString(String key, String value) throws Exception {
		log.trace("开始设置redis值");
		try {
			stringRedisTemplate.opsForValue().set(key, value);
			log.info("redis设置key={},value={}", key, value);
			return value;
		} catch (Exception e) {
			log.error("redis 设置值失败={}", e);
			return null;
		}
	}

	@Override
	public List getList(String key) throws Exception {
		log.trace("开始获取redis值");
		try {
			List<String> value = stringRedisTemplate.opsForList().range(key, 0, -1);
			log.info("redis取值key={},value={}", key, value);
			return value;
		} catch (Exception e) {
			log.error("redis值获取失败={}", e);
			return null;
		}
	}

	@Override
	public List setList(String key, String value) throws Exception {
		log.trace("开始设置redis值");
		try {
			stringRedisTemplate.opsForList().rightPush(key, value);
			log.info("redis取值key={},value={}", key, value);
			return getList(key);
		} catch (Exception e) {
			log.error("redis 设置值失败={}", e);
			return null;
		}

	}
}

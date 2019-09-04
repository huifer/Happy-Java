package com.huifer.happy.redis.service;

import com.huifer.happy.redis.entity.RedisKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReTestTest {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RedisViewService redisViewService;

	@Test
	public void editor() {
		redisTemplate.opsForHash().put("k", "123", "123");
		redisTemplate.opsForValue().set("k", "kljl");
		Set<RedisKey> keys = redisViewService.keys();

		keys.forEach(
				redisKey -> {
					DataType dataType = redisKey.getDataType();

					switch (dataType) {
						case SET:
							SetOperations setOperations = redisTemplate.opsForSet();
							break;
						case HASH:
							HashOperations hashOperations = redisTemplate.opsForHash();
							break;
						case LIST:
							ListOperations listOperations = redisTemplate.opsForList();
							break;
						case ZSET:
							ZSetOperations zSetOperations = redisTemplate.opsForZSet();
							break;
						case STRING:
							ValueOperations valueOperations = redisTemplate.opsForValue();
							valueOperations.set(redisKey.getKey(), "new_value");
							break;
						default:
							break;

					}

				}
		);
	}


}
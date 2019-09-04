package com.huifer.happy.redis.controller;

import com.huifer.happy.redis.entity.RedisKey;
import com.huifer.happy.redis.service.RedisViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-08-31
 */
@RestController
@RequestMapping("/redis")
public class RedisController {


	@Autowired
	private RedisViewService redisViewService;


	@GetMapping("/")
	public Set<RedisKey> getKeys() throws Exception {
		 return redisViewService.keys();
	}

	/**
	 * 获取所有key
	 *
	 * @param isSystem 是否系统内
	 * @return
	 */
	@GetMapping("/keys")
	public List<String> getKeys(
			@RequestParam(value = "is_system", defaultValue = "true") boolean isSystem
	) throws Exception {
		return redisViewService.queryKey(isSystem);
	}
}

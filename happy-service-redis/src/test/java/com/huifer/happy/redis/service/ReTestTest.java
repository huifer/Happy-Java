package com.huifer.happy.redis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReTestTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisViewService redisViewService;

    /**
     * 初始化测试数据
     */
    @Test
    public void init() {
        redisTemplate.opsForValue().set("k", "13245");

        redisTemplate.opsForHash().put("h", "hk", "1");
        redisTemplate.opsForHash().put("h", "hc", "22");

        redisTemplate.opsForZSet().add("z", "zs", 123);
        redisTemplate.opsForZSet().add("z", "z4", 124);
        redisTemplate.opsForZSet().add("z", "z5", 124);

        redisTemplate.opsForList().rightPushAll("eee", "lr", "lr");
        redisTemplate.opsForList().rightPushAll("eee", "lr", "lrr");

        redisTemplate.opsForSet().add("s", "ss", "asd", "ajkslf");
    }

    @Test
    public void setValueTest() {
        Integer[] value = new Integer[10];
        double[] score = new double[10];
        String[] filed = new String[10];
        for (int i = 0; i < 10; i++) {
            value[i] = i;
            score[i] = i;
            filed[i] = String.valueOf(i);
        }

        redisViewService.setValue("k", value, score, filed);
        redisViewService.setValue("h", value, score, filed);
        redisViewService.setValue("z", value, score, filed);
        redisViewService.setValue("eee", value, score, filed);
        redisViewService.setValue("s", value, score, filed);
    }

    @Test
    public void deleteTest() {
        Integer[] value = new Integer[10];
        double[] score = new double[10];
        String[] filed = new String[10];
        for (int i = 0; i < 10; i++) {
            value[i] = i;
            score[i] = i;
            filed[i] = String.valueOf(i);
        }


        redisViewService.delete("k", value, filed);
        redisViewService.delete("h", value, filed);
        redisViewService.delete("z", value, filed);
        redisViewService.delete("eee", value, filed);
        redisViewService.delete("s", value, filed);
    }

    @Test
    public void updateTest() {
        redisViewService.updateStringValue("k", 1231231);
        redisViewService.updateZSetValue("z", "z5", 666);
        redisViewService.updateSetValue("s", "ss");
        redisViewService.updateListValue("eee", "lr1231231");
        redisViewService.updateHashFiled("h", "hk", "11353252523253");
    }


}
package com.huifer.happy.interfaces.redis;

import java.util.List;

/**
 * 描述:
 * redis操作接口
 *
 * @author huifer
 * @date 2019-08-31
 */
public interface RedisOperationInterface {

    /**
     * 获取Key 的值
     *
     * @param key key
     * @return String 字符串
     */
    String getKeyString(String key) throws Exception;

    /**
     * 设置字符串kv
     *
     * @param key   key
     * @param value value
     * @return value
     * @throws Exception
     */
    String setKeyString(String key, String value) throws Exception;

    /**
     * 获取 key 值
     *
     * @param key key
     * @return {@link List}
     * @throws Exception
     */
    List getList(String key) throws Exception;

    /**
     * 设置 key 值
     *
     * @param key  key
     * @param list value
     * @return {@link List}
     * @throws Exception
     */
    List setList(String key, String value) throws Exception;

}

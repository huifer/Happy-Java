package com.huifer.happy.interfaces.msg;

import com.huifer.happy.common.entity.base.BaseMessage;

/**
 * 消息相关操作接口
 */
public interface MessageInterface {
    /**
     * 发送消息
     *
     * @param from 谁发送
     * @param to   给谁发
     * @param msg  消息内容
     * @return {@link BaseMessage} 消息
     */
    BaseMessage sendMessage(String from, String to, String subject, String msg) throws Exception;

}

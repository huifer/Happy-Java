package com.huifer.happy.interfaces.file;

/**
 * 消息相关操作接口
 */
public interface MessageInterface {
    /**
     * 发送消息
     * @param from 谁发送
     * @param to 给谁发
     * @param msg 消息内容
     * @return String 消息
     */
    String sendMessage(String from, String to, String msg);

}

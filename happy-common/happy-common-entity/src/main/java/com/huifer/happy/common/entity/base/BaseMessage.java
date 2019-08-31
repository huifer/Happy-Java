package com.huifer.happy.common.entity.base;

import lombok.Data;

/**
 * 描述: 消息
 *
 * @author huifer
 * @date 2019-08-31
 */
@Data
public class BaseMessage {

	/**
	 * 发送方
	 */
	private String from;
	/**
	 * 接收方
	 */
	private String to;
	/**
	 * 消息内容
	 */
	private String message;
}

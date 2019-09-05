package com.huifer.happy.common.entity.bo;

import com.huifer.happy.common.entity.base.BaseMessage;
import lombok.Data;

/**
 * 描述:
 * 邮件消息
 *
 * @author huifer
 * @date 2019-08-31
 */
@Data
public class EmailBO extends BaseMessage {

    /**
     * 发送方邮箱
     */
    private String sendEmail;
    /**
     * 发送时间
     */
    private Long sendTime;
}

package com.huifer.happy.message;

import com.huifer.happy.common.entity.bo.EmailBO;
import com.huifer.happy.interfaces.msg.MessageInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * 邮件发送服务
 */
@Service
public class EmailMessageService implements MessageInterface {
    private static final Logger log = LoggerFactory.getLogger(EmailMessageService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public EmailBO sendMessage(String from, String to, String subject, String msg) throws Exception {
        log.trace("发送邮件...");
        MimeMessage message = null;
        EmailBO emailBO = new EmailBO();
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(msg, true);
            javaMailSender.send(message);
            emailBO.setFrom(from);
            emailBO.setTo(to);
            emailBO.setMessage(msg);
            // TODO: 2019/8/31 设置发邮箱的地址预设随机？
            log.info("消息内容={}", emailBO);
        } catch (Exception e) {
            log.error("消息发送失败={}", e);
        }
        return emailBO;
    }
}

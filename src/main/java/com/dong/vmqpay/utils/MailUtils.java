package com.dong.vmqpay.utils;

import com.dong.vmqpay.pojo.mail.ToEmail;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.logging.Logger;

public class MailUtils {
    private static volatile MailUtils instance;
    private JavaMailSender mailSender;
    private Logger logger = Logger.getLogger(MailUtils.class.getName());
    private String from;
    private MailUtils() {

    }

    private MailUtils(String from, JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.from = from;
    }

    public static MailUtils getInstance(String from, JavaMailSender mailSender) {
        if (instance == null) {
            synchronized (MailUtils.class) {
                if (instance == null) {
                    instance = new MailUtils(from, mailSender);
                }
            }
        }
        return instance;
    }

    public void sendEmail(ToEmail toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo("507085831@qq.com");
        message.setSubject(toEmail.getSubject());
        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            logger.warning(e.toString());
        }
    }
}


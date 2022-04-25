package com.dong.vmqpay.pojo.mail;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToEmail {

    /**
     * 邮件接收人
     */
    private String[] tos;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;
}

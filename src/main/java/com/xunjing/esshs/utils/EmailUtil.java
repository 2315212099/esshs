package com.xunjing.esshs.utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
@Slf4j
@Component
public class EmailUtil {
    // QQ 邮箱 SMTP 服务器配置
    private final String SMTP_HOST = "smtp.qq.com";
    private final String SMTP_PORT = "587"; // 或 465（SSL）
    @Value("${spring.qq.username}")
    private String USERNAME;// 你的QQ邮箱
    @Value("${spring.qq.password}")
    private String PASSWORD; // SMTP授权码

    public void sendEmail(String to, String subject, String content, boolean isHtml) {
        // 配置邮件服务器属性
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // 启用 TLS 加密
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        // 创建会话
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // 创建邮件对象
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);

            // 根据isHtml参数决定内容类型
            if (isHtml) {
                message.setContent(content, "text/html;charset=UTF-8");
            } else {
                message.setText(content);
            }

            // 发送邮件
            Transport.send(message);
            log.info("邮件发送成功！");
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("邮件发送失败: " + e.getMessage());
        }
    }
}

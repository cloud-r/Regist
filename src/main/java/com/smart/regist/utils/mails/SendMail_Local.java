package com.smart.regist.utils.mails;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Descriptuion TODO
 * @Author Cloudr
 * @Date 2019/11/4 14:22
 **/
public class SendMail_Local {
    /**
     *@Descriptuion TODO 给本地邮件服务器用户发送邮件（测试用）
    **/
    public static void sendMail(String mail,String code) throws Exception {
        //      1.创建连接对象，连接到邮箱服务器
        Properties props = new Properties();
//        props.setProperty("host",value)  //发送主机等信息
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("service@life.com", "123");
            }
        });
//      2.创建邮件对象
        Message message = new MimeMessage(session);
//      2.1设置发件人
        message.setFrom(new InternetAddress("service@life.com"));
//      2.2设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
//      2.3设置邮件主题
        message.setSubject("激活邮件");
//      2.4设置邮件内容
        message.setContent("<h1>激活邮件，请点击以下链接完成激活</h1><h2><a href='http://localhost:8080/regist/active?code=" + code + "'>http://localhost:8080/regist/active?code=" + code + "</a></h2>", "text/html;charset=UTF-8");
//      3.发送一封邮件
        Transport.send(message);
    }
}

package com.smart.regist.utils.resetMail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Descriptuion TODO 给本地邮件服务器用户发送邮件（测试用）
 * @Author Cloudr
 * @Date 2019/11/4 14:22
 **/
public class SendCode_Local {

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
        message.setSubject("验证码");
//      2.4设置邮件内容
        message.setContent("<h1>验证码</h1><h2>" +     code + "</h2>", "text/html;charset=UTF-8");
//      3.发送一封邮件
        Transport.send(message);
    }
}

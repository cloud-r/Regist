package com.smart.regist.utils.mails;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Descriptuion TODO
 * @Author Cloudr
 * @Date 2019/11/4 14:09
 **/
public class SendMail_QQ {
    /**
     *@Descriptuion TODO 给QQ邮箱用户发送邮件
    **/
    public static void sendMail(String mail, String code)throws Exception{
        //      1.创建连接对象，连接到邮箱服务器
        Properties props = new Properties();
        props.setProperty("mail.smtp.host","smtp.qq.com"); //发送主机等信息
        props.setProperty("mail.smtp.auth", "true");// 打开认证
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("739496427@qq.com", "txqytlacqrcgbdgf");
            }
        });
//      QQ邮箱固有
        MailSSLSocketFactory mailss = new MailSSLSocketFactory();
        mailss.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", mailss);

//      2.创建邮件对象
        Message message = new MimeMessage(session);
//      2.1设置发件人
        message.setFrom(new InternetAddress("739496427@qq.com"));//QQ邮箱服务器
//      2.2设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
//      2.3设置邮件主题
        message.setSubject("激活邮件");
//      2.4设置邮件内容
        message.setContent("<h1>激活邮件，请点击以下链接完成激活</h1><h2><a href='http://localhost:8080/regist/active?code=" + code + "'>" +
                "http://localhost:8080/regist/active?code=" + code + "</a></h2>", "text/html;charset=UTF-8");
//      3.发送一封邮件
        Transport.send(message);
    }
}

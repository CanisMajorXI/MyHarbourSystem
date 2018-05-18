package com.myharbour.controller;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private static final int TYPEOFID = 1;
    private static final int TYPEOFEMAIL = 2;
    private static final int TYPEOFERROR = 0;

    /**
     * @param idOrEmail
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public boolean login(@RequestParam("idoremail") String idOrEmail,
                         @RequestParam("password") String password) {
        try {
            if (idOrEmail == null || password == null) return false;
            switch (typeOfUsername(idOrEmail)) {
                case TYPEOFID:
                    //todo
                    break;
                case TYPEOFEMAIL:
                    //todo
                    break;
            }
            //todo
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return false;
    }

    //通过正则表达式判断username类型，是id还是邮箱
    private int typeOfUsername(String username) {
        if (username.matches("\\d{8}")) return TYPEOFID;
        if (username.matches("\\w+(.\\w+)*@\\w+(.\\w)+")) return TYPEOFEMAIL;
        return TYPEOFERROR;
    }

    //随机生成6位验证码
    private int getRandomCode() {
        return new Random().nextInt(899999) + 100000;
    }

    //发送验证码到邮箱
    private boolean sendCodeToEmail(String email, int code) {

        // 1.创建连接对象javax.mail.Session
        // 2.创建邮件对象 javax.mail.Message
        // 3.发送一封激活邮件
        String from = "513768474@qq.com";// 发件人电子邮箱
        String host = "smtp.qq.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)

        Properties properties = System.getProperties();// 获取系统属性

        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");// 打开认证

        try {
            //QQ邮箱需要下面这段代码，163邮箱不需要
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);


            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("513768474@qq.com", "inivhogfaitibhed"); // 发件人邮箱账号、授权码
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("红太阳港口管理系统验证码");
            // 2.4设置邮件内容
            String content = "<html><head></head><body><h1>你的验证码是:" + code + "</h1>请在3分钟内输入该验证码完成注册</body></html>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
            System.out.println("邮件成功发送!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.myharbour.controller;

import com.myharbour.pojo.User;
import com.myharbour.service.UserService;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Random;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService = null;

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
                         @RequestParam("password") String password,
                         @RequestParam("type") Integer type,
                         HttpSession session) {
        if (idOrEmail == null || password == null) return false;
        User user = null;
        try {
            switch (typeOfUsername(idOrEmail)) {
                case TYPEOFID:
                    //todo
                    user = userService.loginById(Integer.parseInt(idOrEmail), password, type);
                    break;
                case TYPEOFEMAIL:
                    user = userService.loginByEmail(idOrEmail, password, type);
                    //todo
                    break;
            }
            System.out.println(user);
            if (user == null) return false;
            session.setAttribute("user", user);
            return true;
            //todo
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param email
     * @param session
     */
    @RequestMapping("/register/get/code")
    @ResponseBody
    public void getCode(@RequestParam("email") String email,
                        HttpSession session) {
        if (email == null || typeOfUsername(email) != TYPEOFEMAIL) return;
        int code = getRandomCode();
        if (sendCodeToEmail(email, code)) {
            session.setAttribute("verificationCode", code);
        }
    }

    @RequestMapping("/register/isnotduplicate")
    @ResponseBody
    public boolean isNotDuplicate(@RequestParam(name = "username", required = false) Integer username,
                                  @RequestParam(value = "email", required = false) String email,
                                  @RequestParam("type") Integer type) {
        if (type == null) return false;
        if (username == null && email == null) return false;
        try {
            return userService.isNotDuplicate(username, email, type);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //1 是 验证码错误
    //2 是 其他错误

    /**
     * @param id
     * @param password
     * @param email
     * @param type
     * @param code
     * @param session
     * @return
     */
    @RequestMapping("/register/addanuser")
    @ResponseBody
    public int addAnUser(@RequestParam(name = "username") Integer id,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "email") String email,
                         @RequestParam(name = "type") Integer type,
                         @RequestParam(name = "vericode") Integer code, HttpSession session) {
        if (id == null || password == null || email == null || type == null || code == null) return 2;

        if (session.getAttribute("verificationCode") == null || !((int) session.getAttribute("verificationCode") == code)) {
            System.out.println("用户输入的验证码" + code);
            System.out.println("实际验证码" + session.getAttribute("verificationCode"));
            return 1;
        }
        User user = new User();
        user.setUserId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setType(type);
        try {
            userService.addAnUser(id, password, email, type);
            return 0;
        } catch (Exception e) {
            return 2;
        }
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

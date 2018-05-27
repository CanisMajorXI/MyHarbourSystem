package com.myharbour.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密的相关工具类
 */
public class PasswordTool {
    /**
     * 字符串MD5摘要生成
     * @param password
     * @return MD5摘要算法生成的32位位字符串
     */
    public static String md5Password(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 加盐的md5摘要算法
     * @param password 原始密码
     * @return 32位十六进制字符串
     */
    public static String addSaltMD5(String password){
        String salt = "0a14b519065d82e8ddf86582e0751993";
        return md5Password(password+salt);
    }

    public static void main(String[] args) {
        String s = "jiangzeminxuming";
        String ss = "qweqwe";
        System.out.println(addSaltMD5(s));
    }
}

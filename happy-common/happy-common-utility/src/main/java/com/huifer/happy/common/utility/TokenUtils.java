package com.huifer.happy.common.utility;

import java.util.Random;

/**
 * token生成工具类，可用作MD5盐值
 */
public class TokenUtils {
    public static final String DEFAULT_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";

    /**
     * 随机生成token长度位len
     *
     * @param len 长度
     * @return String
     */
    public static String generateToken(int len) {
        String inStr = DEFAULT_STRING;
        return randomString(len, inStr);
    }

    /**
     * 随机生成token ,自定义字符串,长度
     *
     * @param len   长度
     * @param inStr 字符串
     * @return String
     */
    protected static String randomString(int len, String inStr) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int nextInt = r.nextInt(inStr.length());
            sb.append(inStr.charAt(nextInt));
        }
        return sb.toString();
    }

    /**
     * 随机生成token ,自定义字符串,长度
     *
     * @param str 字符串
     * @param len 长度
     * @return String
     */
    public static String generateToken(String str, int len) {
        return randomString(len, str);
    }

}

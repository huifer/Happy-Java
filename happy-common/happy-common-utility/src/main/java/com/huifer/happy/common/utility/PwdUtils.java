package com.huifer.happy.common.utility;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;
import java.util.UUID;

/**
 * 密码
 */
public class PwdUtils {
    /**
     * 私钥
     */
    public static final String token = "12a16e";
    public static final String numb = "0123456789";

    public static void main(String[] args) {
//		String uid = "12345";
//		String encodeUid = encodeUid(uid);
//		String s = decodeUid(encodeUid);
//		System.out.println(encodeUid);
//		System.out.println(s);

        for (int i = 0; i < 10; i++) {
            String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
            System.out.println(substring);
        }
    }

    public static String sha1(final String data) {
        byte[] bytes = data.getBytes();
        byte[] bytes1 = DigestUtils.sha1(bytes);

        return toHexString(bytes1);
    }

    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1) {
            throw new IllegalArgumentException("this byteArray must not be null or empty");
        }

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10) {
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    /**
     * 加密uid
     *
     * @param uid
     * @return
     */
    public static String encodeUid(String uid) {
        //  随机生产数字
        return randNumb(10) + uid + randNumb(5);
    }

    private static String randNumb(int n) {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        char[] chars = numb.toCharArray();
        for (int i = 0; i < n; i++) {

            sb.append(chars[r.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    /**
     * 解密uid
     *
     * @param enUid
     * @return
     */
    public static String decodeUid(String enUid) {
        // 获取中间部分数字
        String substring = enUid.substring(10);
        for (int i = 0; i < 5; i++) {
            substring = substring.substring(0, substring.length() - 1);
        }

        return substring;
    }


}

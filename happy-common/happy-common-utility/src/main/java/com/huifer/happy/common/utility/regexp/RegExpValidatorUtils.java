package com.huifer.happy.common.utility.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证
 */
public class RegExpValidatorUtils {
    /**
     *
     * @param regex 正则表达式字符串
     * @param str  要匹配的字符串
     * @return   如果符合返回true,否则返回false
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 邮箱验证
     * @param email 邮箱字符串
     * @return  邮箱与正则匹配返回true，否则false
     */
    public static boolean isEmail(String email){
        String regex = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        return match(regex,email);
    }

    /**
     * 密码验证
     * @param password 密码字符串
     * @return   密码与正则匹配返回true，否则false
     */
    public static boolean isPassword(String password){
        String regex = "[A-Za-z]+[0-9]{6,18}";
        return match(regex, password);
    }

    /**
     * 用户名验证
     * @param name 用户名字符串
     * @return    用户名与正则匹配返回true，否则false
     */
    public static boolean isName(String name){
        String regex = "^[^0-9][\\w_]{5,9}$";
        return match(regex, name);
    }

    /**
     * 验证手机号
     * @param telephone 手机号码
     * @return  手机号码与正则匹配返回true，否则false
     */
    public static boolean isTelephone(String telephone){
        String regex = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$";
        return match(regex, telephone);
    }

    /**
     * 验证电话号码
     * @param phone 电话号码字符串
     * @return   电话号码与正则匹配返回true，否则false
     */
    public static boolean isPhone(String phone){
        String regex = "^[0][1-9]{2,3}-[0-9]{5,10}$|^[1-9]{1}[0-9]{5,8}$";
        return match(regex, phone);
    }

    public static void main(String[] args) {
        String phone = "885402315";
        System.out.println(isPhone(phone));
        System.out.println(isPhone("0571-76540125"));
    }
}

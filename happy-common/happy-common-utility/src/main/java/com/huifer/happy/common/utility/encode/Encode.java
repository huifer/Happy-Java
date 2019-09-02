package com.huifer.happy.common.utility.encode;

import java.io.UnsupportedEncodingException;

/**
 * 编码转换
 */
public class Encode {

    /**
     * utf-8 编码
     */
    public static final String ENCODE_UTF8 = "UTF-8";
    /**
     * gbk编码
     */
    public static final String ENCODE_GBK = "GBK";
    /**
     * iso 8859编码
     */
    public static final String ENCODE_ISO8859 = "ISO-8859-1";
    /**
     * 默认编码:utf-8
     */
    public static final String DEFAULT_ENCODE = ENCODE_UTF8;

    /**
     * iso 编码转 gbk 编码
     */
    public static String iso2gbk(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes(ENCODE_ISO8859), ENCODE_GBK);
    }

    /**
     * iso编码转UTF-8编码
     */
    public static String iso2utf8(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes(ENCODE_ISO8859), ENCODE_UTF8);
    }

}

package com.huifer.happy.common.utility.encryption;

import com.huifer.happy.common.utility.encode.Encode;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

public class DigestUtils {

	public static void main(String[] args) throws Exception {
		String test = "test";
		String md5 = encodeMD5(test);
		System.out.println(md5);
	}

	/**
	 * 用MD5加密文本
	 *
	 * @param string 待加密文本
	 * @return 加密后文本
	 */
	public static String encodeMD5(final String string) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = string.getBytes(Encode.DEFAULT_ENCODE);
		md5.update(bytes);
		StringBuilder sb = new StringBuilder();
		for (byte b : md5.digest()) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}


	/**
	 * 用BASE64加密文本
	 *
	 * @param string 待加密文本
	 * @return 加密后文本
	 */
	public static String encodeBAS464(final String string) throws Exception {
		Base64 base64 = new Base64();
		byte[] bytes = string.getBytes(Encode.DEFAULT_ENCODE);
		String res = base64.encodeAsString(bytes);
		return res;
	}

	/**
	 * 解码BASE64加密文本
	 *
	 * @param string 加密文本
	 * @return 解密后文本
	 * @throws Exception
	 */
	public static String decodeBASE64(final String string) throws Exception {
		Base64 base64 = new Base64();
		String res = new String(base64.decode(string), Encode.ENCODE_UTF8);
		return res;
	}


	private DigestUtils() {
	}
}

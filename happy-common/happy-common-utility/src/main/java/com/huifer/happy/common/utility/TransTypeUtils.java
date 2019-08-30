package com.huifer.happy.common.utility;

import com.huifer.happy.common.exception.CodeException;
import com.huifer.happy.common.utility.encode.Encode;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 类型转换工具
 */
@Slf4j
public class TransTypeUtils {

	public static void main(String[] args) {
	}
	private TransTypeUtils() {

	}

	/**
	 * 字节转{@link InputStream}
	 *
	 * @param bytes 字节数组
	 * @return {@link InputStream}
	 */
	public static InputStream byteArray2InputStream(byte[] bytes) {
		return new ByteArrayInputStream(bytes);
	}

	/**
	 * 输入流转字节
	 *
	 * @param in 输入流
	 * @return byte[]
	 */
	public static byte[] inputStream2ByteArrays(InputStream in) throws IOException {
		byte[] bytes = new byte[1024 * 256];
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int len = 0;
		while ((len = in.read(bytes)) != -1) {
			byteArrayOutputStream.write(bytes, 0, len);
		}
		byteArrayOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 字符串转字节(utf-8)
	 *
	 * @param str 字符串
	 * @return byte[]
	 */
	public static byte[] str2ByteArrays(String str) {
		return str2ByteArrays(str, Encode.DEFAULT_ENCODE);
	}

	/**
	 * 字符串转字节
	 *
	 * @param str      字符串
	 * @param encoding 编码格式
	 * @return byte[]
	 * @throws CodeException
	 */
	public static byte[] str2ByteArrays(String str, String encoding) throws CodeException {
		try {
			return str.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new CodeException("编码异常", e, 1);
		}
	}

	/**
	 * 字节数组转字符串
	 *
	 * @param bytes 字节数组
	 * @return String
	 */
	public static String byteArrays2String(byte[] bytes) throws UnsupportedEncodingException {
		return new String(bytes, Encode.DEFAULT_ENCODE);
	}
}

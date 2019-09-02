package com.huifer.happy.common.utility.encryption;

import com.sun.org.apache.bcel.internal.generic.PUTFIELD;

import javax.print.DocFlavor;
import java.io.File;
import java.security.MessageDigest;

public class MD5Utils {

	public static void main(String[] args) throws Exception {
		String test = "test";
		String md5 = createMD5(test);
		System.out.println(md5);
	}

	public static String createMD5(final String string) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = string.getBytes();
		md5.update(bytes);
		StringBuilder sb = new StringBuilder();
		for (byte b : md5.digest()) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}



	private MD5Utils() {
	}
}

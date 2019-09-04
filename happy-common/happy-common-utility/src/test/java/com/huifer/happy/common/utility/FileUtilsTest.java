package com.huifer.happy.common.utility;

import java.io.File;

import static org.junit.Assert.*;

public class FileUtilsTest {

	public static void main(String[] args) throws Exception{
		File file = new File("C:\\dev\\dw\\Git-2.20.1-64-bit.exe");
		FileUtils.splitFile(file);

		FileUtils.mergeFile(new File("C:\\dev\\dw\\parties"),"exe");
	}

}
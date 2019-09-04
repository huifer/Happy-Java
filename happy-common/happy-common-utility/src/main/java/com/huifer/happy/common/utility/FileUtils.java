package com.huifer.happy.common.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class FileUtils {
	protected static final Logger log = LoggerFactory.getLogger(FileUtils.class);
	/**
	 * 每一块文件大小
	 */
	private static final int SIZE = 1024 * 1024;

	/**
	 * 还原文件
	 *
	 * @param dir        parties 文件夹
	 * @param fileFormat 文件格式(拓展名)
	 * @throws IOException
	 */
	public static void mergeFile(File dir, String fileFormat) throws IOException {
		File[] files = dir.listFiles();
		Properties prop;
		Integer count = null;
		String mergeFilename = null;
		for (File file : files) {
			String filename = file.getAbsoluteFile().getAbsolutePath();
			String prefix = filename.substring(filename.lastIndexOf(".") + 1);
			if ("properties".equals(prefix)) {
				prop = new Properties();
				FileInputStream fis = new FileInputStream(file);
				prop.load(fis);
				count = Integer.parseInt(prop.getProperty("partcount"));
				mergeFilename = prop.getProperty("filename");
			}
		}

		ArrayList<FileInputStream> al = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			al.add(new FileInputStream(new File(dir.getPath() + "/" + (i + 1) + "." + fileFormat)));
		}

		Enumeration en = Collections.enumeration(al);
		try (
				SequenceInputStream sis = new SequenceInputStream(en);
				FileOutputStream fos = new FileOutputStream(new File(dir, mergeFilename));
		) {
			byte[] buf = new byte[SIZE];
			int len = 0;
			while ((len = sis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
		} catch (IOException e) {
			log.error("{}", e);
		}
	}


	/**
	 * 拆分文件,按照1024*1024拆分
	 *
	 * @param file 文件
	 * @throws IOException
	 */
	public static void splitFile(File file) throws IOException {
		String fileFormat = file.getName().substring(file.getName().lastIndexOf('.'));
		File parentDir = file.getParentFile();
		// 写出文件在file 同级目录下parties文件夹中
		File destFile = new File(parentDir, "parties");
		if (!destFile.exists()) {
			destFile.mkdirs();
		}
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[SIZE];
		int len = 0;
		int count = 1;
		FileOutputStream fos = null;
		// 所有文件输出流的集合
		ArrayList<FileOutputStream> fileOutputStreams = new ArrayList<>();
		while ((len = fis.read(buf)) != -1) {
			fos = new FileOutputStream(new File(destFile, (count++) + fileFormat));
			fos.write(buf, 0, len);
			fileOutputStreams.add(fos);
			fos.close();
		}
		fis.close();
		// 写入一个配置文件记录当前文件信息
		Properties prop = new Properties();
		prop.setProperty("partcount", (count - 1) + "");
		prop.setProperty("filename", file.getName());
		fos = new FileOutputStream(new File(destFile, count + ".properties"));
		prop.store(fos, "FileInformation");
		fos.close();
	}


	/**
	 * 写文件
	 *
	 * @param path        写入地址
	 * @param inputStream 输入流
	 */
	public static void write(String path, InputStream inputStream) {
		log.trace("开始写文件");
		try (OutputStream os = new FileOutputStream(path);) {
			byte[] bytes = new byte[1024];
			int len;
			while ((len = inputStream.read(bytes)) != -1) {
				os.write(bytes, 0, len);
			}
		} catch (IOException e) {
			log.error("写文件异常,{}", e);
		}

	}

	/**
	 * 分块写入文件
	 *
	 * @param path        写入地址
	 * @param pathSize    写入文件大小
	 * @param inputStream 输入流
	 * @param inSize      输入流大小
	 * @param chunks      分片数量
	 * @param chunk       分片号
	 */
	public static void writeWithBlock(String path, long pathSize, InputStream inputStream, long inSize, Integer chunks, Integer chunk) {
		log.trace("开始分块写入文件");
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw")) {
			randomAccessFile.setLength(pathSize);
			if (chunk == chunks - 1 && chunk != 0) {
				randomAccessFile.seek(chunk * (pathSize - inSize) / chunk);
			} else {
				randomAccessFile.seek(chunk * inSize);
			}
			byte[] buf = new byte[1024];
			int len;
			while (-1 != (len = inputStream.read(buf))) {
				randomAccessFile.write(buf, 0, len);
			}
		} catch (IOException e) {
			log.error("写文件异常,{}", e);
		}
		log.trace("写入完成");
	}

	private FileUtils() {
	}
}

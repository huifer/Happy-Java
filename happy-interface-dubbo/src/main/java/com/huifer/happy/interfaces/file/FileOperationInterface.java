package com.huifer.happy.interfaces.file;

import java.io.InputStream;

/**
 * 文件操作相关接口
 */
public interface FileOperationInterface {
	/**
	 * 文件上传
	 */
	void uploadFile(byte[] bytes, String filename) throws Exception;

	/**
	 * 文件下载
	 */
	void downloadFile(String filename) throws Exception;

	/**
	 * 下载文件
	 */
	InputStream download(String filename) throws Exception;

	/**
	 * 删除文件
	 */
	void deleteFile(String filename) throws Exception;

	/**
	 * 查询文件
	 */
	void getFileInfo(String filename) throws Exception;

}

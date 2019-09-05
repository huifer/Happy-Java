package com.huifer.happy.interfaces.file;

import com.huifer.happy.common.entity.bo.FileMetaBO;

import java.io.InputStream;

/**
 * 文件操作相关接口
 */
public interface FileOperationInterface {
    /**
     * 文件上传
     *
     * @param bytes    文件bytes
     * @param filename 文件名
     * @return {@link FileMetaBO} 文件信息
     */
    FileMetaBO uploadFile(byte[] bytes, String filename) throws Exception;

    /**
     * 文件下载
     *
     * @param groupName      组名
     * @param remoteFilename 远程文件名
     * @param saveFilename   保存文件名称
     */
    void downloadFile(String groupName, String remoteFilename, String saveFilename) throws Exception;

    /**
     * 下载文件
     *
     * @param groupName      组名
     * @param remoteFilename 远程文件名
     * @return 输入流
     */
    InputStream download(String groupName, String remoteFilename) throws Exception;

    /**
     * 删除文件
     *
     * @param groupName 组名
     * @param filename  文件名称
     * @return boolean true:删除成功,false: 删除失败
     */
    boolean deleteFile(String groupName, String filename) throws Exception;

    /**
     * 查询文件
     *
     * @param groupName 组名
     * @param filename  文件名称
     */
    void getFileInfo(String groupName, String filename) throws Exception;

    /**
     * 从input流中保存文件
     *
     * @param inputStream input流
     * @param filepath    文件路径
     * @throws Exception
     */
    void saveFile(InputStream inputStream, String filepath) throws Exception;


}

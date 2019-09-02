package com.huifer.happy.file.service;


import com.huifer.happy.common.entity.bo.FileMetaBO;
import com.huifer.happy.common.utility.TransTypeUtils;
import com.huifer.happy.interfaces.file.FileOperationInterface;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * FASTDFS 操作类
 */
public class DefaultFileService implements FileOperationInterface {
    public static final String FASTDFS_CLIENT_CONF = "fdfs_client.conf";

    protected static final Logger log = LoggerFactory.getLogger(DefaultFileService.class);

    public static void main(String[] args) throws Exception {
        ClientGlobal.init(FASTDFS_CLIENT_CONF);
        String configInfo = ClientGlobal.configInfo();
        log.info("初始化完成配置信息: {}", configInfo);
    }

    protected void init() {
        log.trace("初始化FastDfs");
        try {
            ClientGlobal.init(FASTDFS_CLIENT_CONF);
        } catch (Exception e) {
            log.error("初始化失败={}", e);
        }
        String configInfo = ClientGlobal.configInfo();
        log.info("初始化完成配置信息: {}", configInfo);
    }

    @Override
    public FileMetaBO uploadFile(byte[] bytes, String filename) throws Exception {
        init();
        log.trace("文件上传");
        String[] files = null;
        String fileExtName = "";
        String period = ".";
        FileMetaBO fileMetaBO = new FileMetaBO();
        if (filename.contains(period)) {
            fileExtName = filename.substring(filename.lastIndexOf(period) + 1);
        } else {
            log.error("上传失败: 文件名不合法");
            return null;
        }
        // 创建链接
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer server = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(server, storageServer);
        // 设置元数据
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", filename);
        metaList[1] = new NameValuePair("fileExtName", fileExtName);
        metaList[2] = new NameValuePair("fileLength", String.valueOf(bytes.length));
        // 上传操作
        try {
            files = storageClient.upload_file(bytes, fileExtName, metaList);
        } catch (Exception e) {
            log.error("上传文件失败,{}", e);
            return null;
        }
        if (files == null) {
            log.error("上传文件失败={}", storageClient.getErrorCode());
        } else {
            fileMetaBO.setGroupName(files[0]);
            fileMetaBO.setRemoteFilename(files[1]);
            log.info("上传时文件元信息={}", fileMetaBO);

            FileInfo fileInfo = storageClient.get_file_info(files[0], files[1]);
            log.info("fdfs文件信息={}", fileInfo);
            ServerInfo[] serverInfos = trackerClient.getFetchStorages(server, files[0], files[1]);
            if (serverInfos == null) {
                log.error("fdfs服务端信息空={}", trackerClient.getErrorCode());
            } else {
                fileMetaBO.setStorageServersCount(String.valueOf(serverInfos.length));
                log.info("fdfs服务端信息数量={}", serverInfos.length);
                // 获取ip&端口
                List<String> ipPorts = new ArrayList<>();
                for (ServerInfo serverInfo : serverInfos) {
                    String ipAddr = serverInfo.getIpAddr();
                    int port = serverInfo.getPort();
                    String ips = ipAddr + ":" + port;
                    ipPorts.add(ips);
                }
                log.info("fdfs 服务IP地址={}", ipPorts);
            }
        }

        // 组装文件信息
        if (files != null) {
            String fileId;
            int ts;
            String token = null;
            String fileUrl;
            InetSocketAddress inetSocketAddress = server.getInetSocketAddress();
            String groupName = files[0];
            String remoteFileName = files[1];

            fileId = groupName + StorageClient1.SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR + remoteFileName;
            fileUrl = "http://" + inetSocketAddress.getAddress().getHostAddress();
            int port = 80;
            if (ClientGlobal.g_tracker_http_port != port) {
                fileUrl += ":" + ClientGlobal.g_tracker_http_port;
            }
            fileUrl += "/" + fileId;
            if (ClientGlobal.g_anti_steal_token) {
                ts = (int) (System.currentTimeMillis() / 1000);
                try {
                    token = ProtoCommon.getToken(fileId, ts, ClientGlobal.g_secret_key);
                } catch (Exception e) {
                    log.error("文件上传失败={}", e);
                }
                fileUrl += "?token=" + token + "&ts=" + ts;
            }
            fileMetaBO.setUrl(fileUrl);
            log.info("文件地址={}", fileUrl);
            log.info("文件上传成功,文件信息={}", fileMetaBO);
        }
        // 关闭资源
        server.close();
        return fileMetaBO;
    }

    @Override
    public void downloadFile(String groupName, String remoteFilename, String saveFilename) throws Exception {
        init();
        log.trace("开始下载");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer server = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(server, storageServer);

        byte[] bytes = storageClient.download_file(groupName, remoteFilename);
        if (bytes == null) {
            log.error("文件下载失败");
            return;
        } else {
            log.info("文件下载成功,文件大小={}", bytes.length);
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(saveFilename);) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (Exception e) {
            log.error("文件下载失败,{}", e);
        }
    }

    @Override
    public InputStream download(String groupName, String remoteFilename) throws Exception {
        init();
        log.trace("开始下载");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer server = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(server, storageServer);
        byte[] bytes = storageClient.download_file(groupName, remoteFilename);
        if (bytes == null) {
            log.error("文件下载失败");
            return null;
        } else {
            log.info("文件下载成功,文件大小={}", bytes.length);
        }
        InputStream inputStream = null;

        inputStream = TransTypeUtils.byteArray2InputStream(bytes);
        return inputStream;
    }

    @Override
    public boolean deleteFile(String groupName, String filename) throws Exception {
        init();
        log.trace("开始删除");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer server = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(server, storageServer);

        int i = storageClient.delete_file(groupName, filename);
        if (i == 0) {
            log.info("删除成功");
            return true;
        } else {
            log.info("删除失败");
            return false;
        }
    }

    @Override
    public void getFileInfo(String groupName, String filename) throws Exception {
        init();
        log.trace("开始删除");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer server = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(server, storageServer);
        NameValuePair[] nameValuePairs = storageClient.get_metadata(groupName, filename);
        for (NameValuePair nameValuePair : nameValuePairs) {
            log.info("文件名={},value={}", nameValuePair.getName(), nameValuePair.getValue());
        }
    }

    @Override
    public void saveFile(InputStream inputStream, String filepath) throws Exception {
        log.trace("开始保存文件");
        OutputStream outputStream = new FileOutputStream(filepath);
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
        } catch (Exception e) {
            log.error("文件保存失败,{}", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("输出流关闭异常,{}", e);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("输入流关闭异常,{}", e);
                }
            }
        }
    }
}

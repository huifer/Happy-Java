package com.huifer.happy.file.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流下载service
 */
@Service
public class LimiterFileService {
    /**
     * 下载计数器:记录有多少个下载请求
     */
    public static final AtomicInteger DOWNLOAD_NUM = new AtomicInteger();
    protected static final Logger log = LoggerFactory.getLogger(LimiterFileService.class);
    /**
     * 最大下载速度,单位：kb
     */
    @Value("${max.download.speed}")
    public int maxDownloadSpeed;


    public void downloadBreak(String name, HttpServletRequest request, HttpServletResponse response) {
        String fullPath = "C:\\Users\\1\\Downloads\\" + name;
        File downloadFile = new File(fullPath);

        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        response.setHeader("Accept-Ranges", "bytes");
        long downloadSize = downloadFile.length();
        long fromPos = 0, toPos = 0;
        if (request.getHeader("Range") == null) {
            response.setHeader("Content-Length", downloadSize + "");
        } else {
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            String range = request.getHeader("Range");
            String bytes = range.replaceAll("bytes=", "");
            String[] ary = bytes.split("-");
            fromPos = Long.parseLong(ary[0]);
            if (ary.length == 2) {
                toPos = Long.parseLong(ary[1]);
            }
            int size;
            if (toPos > fromPos) {
                size = (int) (toPos - fromPos);
            } else {
                size = (int) (downloadSize - fromPos);
            }
            response.setHeader("Content-Length", size + "");
            downloadSize = size;
        }
        RandomAccessFile in = null;
        OutputStream out = null;
        try {
            in = new RandomAccessFile(downloadFile, "rw");
            // 设置下载起始位置
            if (fromPos > 0) {
                in.seek(fromPos);
            }
            // 缓冲区大小
            int bufLen = (int) (downloadSize < 2048 ? downloadSize : 2048);
            byte[] buffer = new byte[bufLen];
            int num;
            int count = 0; // 当前写到客户端的大小
            out = response.getOutputStream();
            while ((num = in.read(buffer)) != -1) {
                out.write(buffer, 0, num);
                count += num;
                if (downloadSize - count < bufLen) {
                    bufLen = (int) (downloadSize-count);
                    if(bufLen==0){
                        break;
                    }
                    buffer = new byte[bufLen];
                }
            }
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    /**
     * 限流下载
     * // TODO: 2019/9/2 下载暂停会出现异常
     *
     * @param exportUrl 下载文件url
     * @param speed     速度
     * @param response  response
     */
    public void downloadLimit(String exportUrl, int speed, HttpServletResponse response) {
        OutputStream out = null;
        String fileName = exportUrl.substring(exportUrl.lastIndexOf('/') + 1);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        int downloadSpeed = speed;

        try (
                InputStream fileInputStream = new FileInputStream(exportUrl);
        ) {
            BandwidthLimiter bandwidthLimiter = new BandwidthLimiter(downloadSpeed);
            DownloadLimiter downloadLimiter = new DownloadLimiter(fileInputStream, bandwidthLimiter);

            out = response.getOutputStream();
            int len = 0;
            while ((len = downloadLimiter.read()) != -1) {
                out.write(len);
                bandwidthLimiter.setMaxRate(speed);
            }
            out.flush();
            out.close();
            downloadLimiter.close();
        } catch (IOException e) {
            log.error("下载失败={}", e);
        } finally {
            closeStream(out, null);
        }
    }

    /**
     * 限流下载
     *
     * @param exportUrl 下载文件url
     * @param response  response
     */
    public void downloadLimit(String exportUrl, HttpServletResponse response) {
        DOWNLOAD_NUM.incrementAndGet();
        OutputStream out = null;
        DownloadLimiter downloadLimiter = null;
        String fileName = exportUrl.substring(exportUrl.lastIndexOf('/') + 1);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (InputStream fileInputStream = new FileInputStream(exportUrl);) {
            int downloadSpeed = maxDownloadSpeed / DOWNLOAD_NUM.get();
            BandwidthLimiter bandwidthLimiter = new BandwidthLimiter(downloadSpeed);
            downloadLimiter = new DownloadLimiter(fileInputStream, bandwidthLimiter);
            out = response.getOutputStream();
            int len = 0;
            while ((len = downloadLimiter.read()) != -1) {
                out.write(len);
                bandwidthLimiter.setMaxRate(maxDownloadSpeed / DOWNLOAD_NUM.get());
            }
            out.flush();
            out.close();
            downloadLimiter.close();
        } catch (IOException e) {
            log.error("下载失败={}", e);
        } finally {
            closeStream(out, downloadLimiter);
        }
        DOWNLOAD_NUM.decrementAndGet();
    }

    /**
     * 关闭流
     *
     * @param out             OutputStream
     * @param downloadLimiter DownloadLimiter
     */
    protected void closeStream(OutputStream out, DownloadLimiter downloadLimiter) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                log.error("输出流关闭失败{}", e);
            }
        }
        if (downloadLimiter != null) {
            try {
                downloadLimiter.close();
            } catch (IOException e) {
                log.error("下载限流关闭失败={}", e);
            }
        }
    }


}

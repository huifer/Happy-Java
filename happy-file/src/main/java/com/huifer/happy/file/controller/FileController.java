package com.huifer.happy.file.controller;

import com.huifer.happy.file.service.BandwidthLimiter;
import com.huifer.happy.file.service.DownloadLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限速查询
 */
@RestController
public class FileController {
	public static AtomicInteger downloadNum = new AtomicInteger();

	@GetMapping("/")
	public String heat() {
		return "heat";
	}

	@RequestMapping("/downloadFile")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {

		downloadNum.incrementAndGet();  // 请求计数器 +1
		OutputStream out = null;
		InputStream fileInputStream = null;
		DownloadLimiter downloadLimiter = null;
		try {
			String export_url = "C:/Users/1/Downloads/CentOS-7-x86_64-Minimal-1810.iso";
			String fileName = export_url.substring(export_url.lastIndexOf("/") + 1);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			fileInputStream = new FileInputStream(export_url);
			//计算下载速度  总共的带宽是2M 速度是256KB/s
			int downloadSpeed = 500 / downloadNum.get();
			BandwidthLimiter bandwidthLimiter = new BandwidthLimiter(downloadSpeed);
			downloadLimiter = new DownloadLimiter(fileInputStream, bandwidthLimiter);
			out = response.getOutputStream();
			int len = 0;
			while ((len = downloadLimiter.read()) != -1) {
				out.write(len);
				bandwidthLimiter.setMaxRate(500 / downloadNum.get());
			}
			out.flush();
			out.close();
			downloadLimiter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (downloadLimiter != null) {
				try {
					downloadLimiter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			downloadNum.decrementAndGet();   // 请求计数器 -1
		}
	}
}

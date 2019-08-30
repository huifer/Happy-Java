package com.huifer.happy.common.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件分片表: t_file_sec
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileSecPO {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 分区id
	 */
	private Integer burstId;
	/**
	 * 文件地址
	 */
	private String url;
}

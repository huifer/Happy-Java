package com.huifer.happy.common.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 文件信息表: t_file_info
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoPO {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件大小
	 */
	private BigDecimal size;
	/**
	 * 分片数
	 */
	private Integer burstSize;
	/**
	 * 用户id
	 */
	private Long userId;
}

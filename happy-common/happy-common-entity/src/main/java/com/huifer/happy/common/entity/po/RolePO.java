package com.huifer.happy.common.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限表: t_role
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePO {
	/**
	 * 权限id
	 */
	private Long id;
	/**
	 * 权限名称
	 */
	private String roleName;
}

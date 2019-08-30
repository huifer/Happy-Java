package com.huifer.happy.common.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表: t_user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {
	/**
	 * 用户id
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 权限id
	 */
	private Integer roleId;
}

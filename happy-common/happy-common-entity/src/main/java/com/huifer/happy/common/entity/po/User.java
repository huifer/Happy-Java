package com.huifer.happy.common.entity.po;

import java.math.BigDecimal;

public class User {
    /**
    * id
    */
    private Long id;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 登录名
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 盐
    */
    private String salt;

    /**
    * 从t_role传递id
    */
    private Long roleId;

    /**
    * 注册时间
    */
    private Long registTime;

    /**
    * vip过期时间
    */
    private Long vipOuttime;

    /**
    * vip开通时间
    */
    private Long vipOpentime;

    /**
    * 上次登录时间
    */
    private Long lastlandingTime;

    /**
    * 用户类型:0:管理员;1:vip;2:普通用户
    */
    private Integer userType;

    /**
    * 已用文件空间
    */
    private BigDecimal usedContent;

    /**
    * 修改时间
    */
    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Long registTime) {
        this.registTime = registTime;
    }

    public Long getVipOuttime() {
        return vipOuttime;
    }

    public void setVipOuttime(Long vipOuttime) {
        this.vipOuttime = vipOuttime;
    }

    public Long getVipOpentime() {
        return vipOpentime;
    }

    public void setVipOpentime(Long vipOpentime) {
        this.vipOpentime = vipOpentime;
    }

    public Long getLastlandingTime() {
        return lastlandingTime;
    }

    public void setLastlandingTime(Long lastlandingTime) {
        this.lastlandingTime = lastlandingTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public BigDecimal getUsedContent() {
        return usedContent;
    }

    public void setUsedContent(BigDecimal usedContent) {
        this.usedContent = usedContent;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
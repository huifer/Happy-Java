package com.huifer.happy.common.entity.po;

public class Role {
    /**
     * id
     */
    private Long id;

    /**
     * 权限:0,管理员1.普通用户,2.vip
     */
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
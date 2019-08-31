package com.huifer.happy.mybatis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest {
    @Autowired
    private RoleMapper roleMapper;


    @Test
    public void add() {
//        Role role = new Role();
//        role.setId(0L);
//        role.setRoleName("超级管理员");
//        roleMapper.insert(role);


        Role role1 = roleMapper.selectByPrimaryKey(0L);
        List<Role> admin = roleMapper.findByRoleName("超级管理员");
        System.out.println();
    }

}
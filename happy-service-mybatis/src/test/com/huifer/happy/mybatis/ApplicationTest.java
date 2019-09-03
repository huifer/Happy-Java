package com.huifer.happy.mybatis;


import com.huifer.happy.common.entity.po.RolePO;
import com.huifer.happy.common.entity.po.UserPO;
import com.huifer.happy.mybatis.mapper.RoleMapper;
import com.huifer.happy.mybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;


    @Test
    public void add() {
//        Role role = new Role();
//        role.setId(0L);
//        role.setRoleName("超级管理员");
//        roleMapper.insert(role);


//        RolePO role1 = roleMapper.selectByPrimaryKey(0L);
//        List<RolePO> admin = roleMapper.findByRoleName("超级管理员");
//        System.out.println();
        UserPO user1 = new UserPO();
        user1.setId(1L);
        user1.setEmail("178079091@qq.com");
        user1.setUsedContent(new BigDecimal("20"));
        user1.setUsername("zhangs");
        user1.setPassword("123");
        user1.setRegistTime(System.currentTimeMillis());
        user1.setRoleId(1L);
        user1.setSalt("aac");
        user1.setUserType(1);
        int a = userMapper.insert(user1);
        System.out.println(a);
    }

}
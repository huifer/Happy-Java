package com.huifer.happy.mybatis;


import com.huifer.happy.common.entity.po.Role;
import com.huifer.happy.mybatis.mapper.RoleMapper;
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


    }

}
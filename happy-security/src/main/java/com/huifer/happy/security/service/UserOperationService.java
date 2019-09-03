package com.huifer.happy.security.service;

import com.huifer.happy.common.entity.po.UserPO;
import com.huifer.happy.common.exception.CheckException;
import com.huifer.happy.common.utility.regexp.RegExpValidatorUtils;
import com.huifer.happy.interfaces.user.UserOperationInterface;
import com.huifer.happy.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserOperationService implements UserOperationInterface {

//    @Autowired
//    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 测试用验证码
     */
    public static final String VERIF_CODE = "C02DE";
    /**
     * 有效期时间1分钟
     */
    public static final int VALIDITY_TIME_ONE = 60;
    /**
     * 验证码存redis的key
     */
    public static final  String VERIF_KEY = "email_code:";
    private static final Logger log = LoggerFactory.getLogger(UserOperationService.class);

    @Override
    public String createVerifiCode(String email) throws Exception {
        // TODO: 2019/8/31 暂时没有构建验证码框架放固定值进行调试
        //1. 生产密码
        // 2. 存放在redis 中 有效期1分钟
        redisTemplate.opsForValue().set(VERIF_KEY+email,VERIF_CODE,60);

        return VERIF_CODE;
    }

    @Override
    public void registerUser(String email, String username, String pwd, String vCode) throws Exception {

        long nowTime = System.currentTimeMillis();
        if (checkUserInfo(email, username, pwd, vCode)) {
            UserPO userPO = new UserPO();
            userPO.setEmail(email);
            userPO.setUsername(username);
            userPO.setPassword(pwd);
         //   userPO.setSalt(null);
         //   userPO.setRoleId();
            userPO.setUserType(2);
            userPO.setRegistTime(nowTime);
            userPO.setUsedContent(new BigDecimal("50"));
            userPO.setUpdateTime(nowTime);
            insertUserInfo(userPO);
        }

    }

    public boolean checkUserInfo(String email, String username, String pwd, String vCode) {
        // 1. 通过email 从redis 中获取 验证码
        log.trace("验证用户信息...");
        String redisVcode = redisTemplate.opsForValue().get(VERIF_KEY+email);
        if (!redisVcode.equalsIgnoreCase(vCode)) {
            // 跑出异常
            throw  new CheckException("验证码错误！",1);
//            log.error("验证验证码失败{}",new CheckException("验证码错误！",404));
        }
        // 正则校验邮箱 , 用户名， 密码
        if (!RegExpValidatorUtils.isEmail(email)) {
            throw  new CheckException("邮箱格式错误！",1);
        }
        if (!RegExpValidatorUtils.isName(username)) {
            throw  new CheckException("用户名格式错误！",1);
        }
        if (!RegExpValidatorUtils.isPassword(pwd)) {
            throw  new CheckException("密码格式错误！",1);
        }

        return true;
    }


    @Transactional(rollbackFor = Throwable.class)
    public void insertUserInfo(UserPO record) {
//        int insert = userMapper.insert(record);
    }
}

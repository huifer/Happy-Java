package com.huifer.happy.interfaces.user;

/**
 * 用户 操作接口
 */
public interface UserOperationInterface {


    /**
     * 根据邮箱返回一个验证码
     *
     * @param email
     * @return
     */
    String createVerifiCode(String email) throws Exception;

    /**
     * 接受基本参数创建用户信息
     *
     * @param email
     */
    void registerUser(String email, String username, String pwd, String vCode) throws Exception;


}

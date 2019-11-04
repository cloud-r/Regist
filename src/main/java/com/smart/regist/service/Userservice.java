package com.smart.regist.service;

import com.smart.regist.domain.User;

public interface Userservice {

    void save(User user) throws Exception; //保存注册用户信息

    User findByCode(String code) throws Exception; //根据激活码查找用户

    void update(User user); //激活后更新用户信息

    User findByMail(String mail) throws Exception;  //根据邮箱查找用户

    void updatePassword(User user); //重置密码后更新用户密码

    void updateSecurity(User user); //重置密码后删除验证码

}
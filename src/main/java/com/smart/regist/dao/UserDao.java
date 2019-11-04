package com.smart.regist.dao;

import com.smart.regist.domain.User;

public interface UserDao {

    void save(User user);    //保存注册用户信息

    User findByCode(String code) throws Exception; //根据激活码查找用户

    void update(User user);  //激活后更新用户信息

    User findByMail(String mail); //根据邮箱查找用户

    void updatePassword(User user); //重置密码后更新用户密码

    void updateSecurity(User user);
}

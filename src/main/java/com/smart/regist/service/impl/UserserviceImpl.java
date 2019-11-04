package com.smart.regist.service.impl;


import com.smart.regist.dao.impl.UserDaoImpl;
import com.smart.regist.domain.User;
import com.smart.regist.service.Userservice;
import com.smart.regist.utils.mails.MailUtils;
import com.smart.regist.utils.resetMail.SendCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserserviceImpl implements Userservice {

    @Autowired
    private UserDaoImpl dao;

    @Override
    public void save(User user) throws Exception {
        /**
         *@Descriptuion TODO 将用户信息存入数据库并发送激活邮件
        **/
        dao.save(user);
        MailUtils.sendMail(user.getMail(),user.getCode());

    }

    @Override
    public User findByCode(String code)throws Exception {
        /**
         *@Descriptuion TODO 根据激活码查询用户是否存在
        **/
        return dao.findByCode(code);
    }

    @Override
    public void update(User user) {
        /**
         *@Descriptuion TODO 激活后修改用户信息
        **/
        dao.update(user);
    }
    @Override
    public User findByMail(String mail) throws Exception {
        /**
         *@Descriptuion TODO 根据邮箱查询用户是否存在
        **/
        return  dao.findByMail(mail);
    }

    @Override
    public void updatePassword(User user) {
       dao.updatePassword(user);
    }

    @Override
    public void updateSecurity(User user) {
        dao.updateSecurity(user);
    }


    public void SendSecurityCode(String mail,Integer code) throws Exception {
        String codes=code.toString();
        SendCode.sendMail(mail,codes);
    }
}

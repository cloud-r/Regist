package com.smart.regist.dao.impl;

/**
 * @Descriptuion TODO 对数据库的操作
 * @Author Cloudr
 * @Date 2019/11/4 20:54
 **/

import com.smart.regist.dao.UserDao;
import com.smart.regist.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


//交给springboot管理的注解
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) {
        /**
         *@Descriptuion TODO 将用户注册信息存入数据库
         **/
        jdbcTemplate.update("insert into spring.user (id,name,password,mail,state,code) values(?,?,?,?,?,?)",
                user.getId(),user.getName(),user.getPasssword(),user.getMail(),user.getState(),user.getCode());
    }

    public User findByCode(String code) throws Exception{
        /**
         *@Descriptuion TODO 根据激活码查询用户
         **/

        QueryRunner queryRunner = new QueryRunner();

        String sql = "select * from spring.user where code=?";
        User user= new User();

        jdbcTemplate.query(sql, new Object[]{code}, new RowCallbackHandler(){
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPasssword(resultSet.getString("password"));
                user.setMail(resultSet.getString("mail"));
                user.setCode(code);
                user.setState(resultSet.getInt("state"));

            }
        });
        return user;

    }

    public void update(User user) {
        /**
         *@Descriptuion TODO 激活后更新用户信息(code设为null state设为1表示已经激活)
         **/
        String sql = "update spring.user set state=?,code=? where id=?";
        Object[] params = {user.getState(), user.getCode(), user.getId()};
        jdbcTemplate.update(sql,params);
    }

    public User findByMail(String mail) {

        /**
         *@Descriptuion TODO 根据邮箱查询用户
         **/
        QueryRunner queryRunner = new QueryRunner();

        String sql = "select * from spring.user where mail=?";
        User user= new User();

        jdbcTemplate.query(sql, new Object[]{mail}, new RowCallbackHandler(){
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPasssword(resultSet.getString("password"));
                user.setMail(mail);
                user.setCode(resultSet.getString("code"));
                user.setState(resultSet.getInt("state"));
                user.setSecurityCode(resultSet.getInt("securityCode"));

            }
        });
        return user;
    }

    public void updatePassword(User user) {
        /**
         *@Descriptuion TODO 更新密码
        **/
        String sql = "update spring.user set password=? where id=?";
        Object[] params = {user.getPasssword(), user.getId()};
        jdbcTemplate.update(sql,params);
    }

    public void updateSecurity(User user) {
        /**
         *@Descriptuion TODO 设置securityCode
        **/
        String sql = "update spring.user set SecurityCode=? where id=?";
        Object[] params = {user.getSecurityCode(), user.getId()};
        jdbcTemplate.update(sql,params);
    }

}

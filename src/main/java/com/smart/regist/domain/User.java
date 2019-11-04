package com.smart.regist.domain;

/**
 * @Descriptuion TODO
 * @Author Cloudr
 * @Date 2019/10/24 20:53
 **/
public class User {
    private Integer id;
    private String passsword;
    private String name;
    private String mail;
    private String code;//激活码
    private Integer state; //激活状态 0:未激活  1:已激活
    private Integer SecurityCode; //重置密码时的验证码

    public User() {
    }

    public void setSecurityCode(Integer securityCode) {
        SecurityCode = securityCode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getPasssword() {
        return passsword;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getState() {
        return state;
    }

    public Integer getSecurityCode() {
        return SecurityCode;
    }

}
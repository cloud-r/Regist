package com.smart.regist.utils;

/**
 * @Descriptuion TODO 获取一个六位数 即用户重置密码时用到的激活码
 * @Author Cloudr
 * @Date 2019/11/4 14:57
 **/
public class SecurityCode {

    public static Integer GetSecurityCode(){
        int a=(int)(Math.random()*1000000);
        return a;
    }
}

package com.smart.regist.utils.resetMail;

/**
 * @Descriptuion TODO 发送重置密码验证码邮件的工具类
 * @Author life
 * @Date 2019/10/26 15:12
 **/
public class SendCode {
    /**
     * @param mail   给谁发邮件
     * @param code 邮件激活码
     */
    public static void sendMail(String mail, String code) throws Exception {
        /**
         *@Descriptuion TODO 根据邮箱类型发送邮件
         **/
        if(mail.endsWith("@qq.com"))
            SendCode_QQ.sendMail(mail, code);
        if(mail.endsWith("@life.com"))
            SendCode_Local.sendMail(mail, code);
    }
}

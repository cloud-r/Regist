package com.smart.regist.controller;

import com.smart.regist.domain.User;
import com.smart.regist.service.impl.UserserviceImpl;
import com.smart.regist.utils.SecurityCode;
import com.smart.regist.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Descriptuion TODO
 * @Author Cloudr
 * @Date 2019/10/29 17:27
 **/
@RestController
public class RController {

    @Autowired
    private UserserviceImpl userservice;

    //这里是首先进入登陆页面，然后再进行其他的操作
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("regist");
        return mv;
    }

    @PostMapping("/regist")          //@RequestParam后面参数一定要对应！！！
    public ModelAndView login(@RequestParam("name")String name, @RequestParam("password") String password, @RequestParam("mail") String mail){
        ModelAndView success = new ModelAndView();
        try{
//          获取并封装数据
            User user = new User();
            user.setName(name);
            user.setPasssword(password);
            user.setMail(mail);
            user.setState(0);
            user.setCode(UUIDUtils.getUUID());
//          调用业务层处理数据(存入数据库并发送激活邮件)
            userservice.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        success.setViewName("registSuccess");
        return success;
    }

    @GetMapping("/regist/active")
//    @ResponseBody
    public ModelAndView registActive(@RequestParam("code") String code){
        ModelAndView result= new ModelAndView();
        String x="未操作";
        try{
//          根据激活码查询用户
            User user = userservice.findByCode(code);
//          如果返回的user的ID值为空 则表示注册用户(激活or未激活)中没有此返回的user
            if(user.getId()!=null){
                user.setState(1);
                user.setCode(null);
                userservice.update(user);
                result.setViewName("active_success");
            }
            else {
                result.setViewName("active_fail");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/reSetPassword")
    public ModelAndView reset(){
        ModelAndView mv = new ModelAndView("reSetPassword");
        return mv;
    }

    @PostMapping("/reSetPassword")
    public ModelAndView reset(@RequestParam("mail") String mail) throws Exception {
        ModelAndView mv = new ModelAndView();

        Integer securityCode= SecurityCode.GetSecurityCode();
        // 根据邮箱查询用户
        User user = userservice.findByMail(mail);

        if(user.getId()!=null){
            //如果查询到有此用户 设置默认安全码并给邮箱发送验证码
            user.setSecurityCode(securityCode);
            userservice.updateSecurity(user);
            userservice.SendSecurityCode(mail,securityCode);

            mv.setViewName("reSetPasswords");
        }
        else{
            //若没有查到则提示错误
            mv.setViewName("reSetPasswords_fail");
        }

        return mv;
    }

    @PostMapping("/setPassword")
    public ModelAndView resetp(@RequestParam("securityCode") String securityCode1,
                               @RequestParam("password") String password,@RequestParam("mail") String mail) throws Exception {
        /**
         *@Descriptuion TODO 验证激活码并重设密码
         **/
        ModelAndView mv = new ModelAndView();

        Integer sc=new Integer(securityCode1);
        //获得邮箱账号对于的user
        User user = userservice.findByMail(mail);
        Integer a=user.getSecurityCode();

        //如果user的securityCode和验证码一致
        if(a.equals(sc)){
            user.setPasssword(password);
            userservice.updatePassword(user);

            //重置securityCode为0
            user.setSecurityCode(0);
            userservice.updateSecurity(user);
            mv.setViewName("reSetSuccess");
        }
        else {
            mv.setViewName("reSetFail");
        }

        return mv;
    }

}

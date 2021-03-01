package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("map")
public class UrlMappingTest {

    @GetMapping("/g")
    @ResponseBody
    public String getMethod(){
        return "This is get method";
    }

    @PostMapping("/p")
    @ResponseBody
    public String postMethod(User user){
        System.out.println(user);
        return  "<fieldset><legend>登陆成功</legend>用户名：" +user.getUsername()+
                "<br>密码："+user.getPassword()+"</fieldset>";
    }
}

package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginMav(String name){
        return "login.jsp";
    }

    @PostMapping("/main")
    public String checkUser(User user, ModelMap modelMap){
        modelMap.addAttribute("user",user);
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())){
            return "main.jsp";
        }else{
            modelMap.addAttribute("message","用户或密码错误，请重新登录！");
            return "/login.jsp";
        }
    }
}

package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UrlMappingTest {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/p")
    public String postMethod(User user, ModelMap modelMap){
        if(user.getUsername().equals("admin") && "admin".equals(user.getPassword())){
            modelMap.addAttribute("user",user);
        }else {
            return "redirect:/index.html";
        }
        return "main";
    }
}

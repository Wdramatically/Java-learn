package com.imooc.springmvc.controller;


import com.imooc.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ftl")
public class FreemarkerTestController {

    @GetMapping("/test")
    public ModelAndView testFreemarker(){
        ModelAndView modelAndView = new ModelAndView("test");
        User user = new User();
        user.setUsername("martin");
        modelAndView.addObject("u",user);
        return modelAndView;
    }
}

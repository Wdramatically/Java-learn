package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class TestController {

    @GetMapping("/t")
    @ResponseBody
    public String hello(@DateTimeFormat(pattern = "yyyy-MM-dd") Date createDate) {
        return "Hello Spring MVC";
    }

    @GetMapping("/s")
    @ResponseBody
    public ModelAndView showInfo(@RequestParam("UserId") Integer userId) {
        ModelAndView mav = new ModelAndView("/view.jsp");
        User user = new User();
        if (userId == 1) {
            user.setUsername("martin");
        } else if (userId == 2) {
            user.setUsername("carl");
        }
        mav.addObject("u", user);
        return mav;
    }

    @PostMapping("/bmi/bmi.do")
    public String showBMI(Double height, Double weight, ModelMap modelMap) {
        String view = "/src/webapp/BMIRes.jsp";
        String bmiAns;
        if (weight == null || height == null){

            return "/exception.html";
        }
        double bmi = weight / (height * height) * 10000;
        if (bmi < 19){
            bmiAns = "多吃点，太瘦了！注意加强营养~";
        }else if (bmi > 25){
            bmiAns = "该减肥了！注意加强锻炼~";
        }else{
            bmiAns = "体重正常，注意保持~";
        }
        modelMap.addAttribute("res",bmiAns);
        return view;
    }
}

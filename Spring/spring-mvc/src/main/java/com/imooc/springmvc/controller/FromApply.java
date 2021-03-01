package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.Form;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FromApply {

    //List接收
    //@PostMapping("/apply")
    @ResponseBody
    public String apply(String name, String course, @RequestParam List<Integer> purpose){
        System.out.println(name);
        System.out.println(course);
        for (Integer p : purpose) {
            System.out.println(p);
        }
        return "SUCCESS";
    }

    @PostMapping("/apply")
    @ResponseBody
    public String apply(Form form){
        System.out.println(form);
        return "SUCCESS";
    }
}

package com.imooc.springbootdemo.controller;

import com.imooc.springbootdemo.entity.News;
import com.imooc.springbootdemo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/findNews/{id}")
    public News findNews(@PathVariable Integer id){
        return newsService.findNewsById(id);
    }
}

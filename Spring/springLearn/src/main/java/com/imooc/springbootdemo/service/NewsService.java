package com.imooc.springbootdemo.service;

import com.imooc.springbootdemo.dao.NewsRepository;
import com.imooc.springbootdemo.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News findNewsById(Integer id){
        return newsRepository.findById(id);
    }
}

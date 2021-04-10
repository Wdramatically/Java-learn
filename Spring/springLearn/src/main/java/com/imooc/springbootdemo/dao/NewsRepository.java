package com.imooc.springbootdemo.dao;

import com.imooc.springbootdemo.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NewsRepository {
    @Select("select * from news where id = #{id}")
    News findById(Integer id);
}

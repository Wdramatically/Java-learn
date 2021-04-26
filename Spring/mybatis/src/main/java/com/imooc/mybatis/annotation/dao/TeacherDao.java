package com.imooc.mybatis.annotation.dao;

import com.imooc.mybatis.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherDao {
    @Insert({
            "<script>",
                "insert into teacher(name,sex,j_no,subject,grade,description) values",
                "<foreach collection='list' item='item' index='index' separator=','>",
                    "(#{item.name},#{item.sex},#{item.jNo},#{item.subject},#{item.grade},#{item.description})",
                "</foreach>",
            "</script>"
    })
    void batchInsert(@Param(value="list") List<Teacher> teachers);

    @Select("select * from teacher")
    List<Teacher> pageSelect();
}

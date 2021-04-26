package com.imooc.mybatis.annotation.dao;

import com.imooc.mybatis.entity.Song;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SongDao {

    @Select("select * from song")
    public List<Song> findAll();

    @Select("select * from song where category = #{type}")
    public List<Song> findByType(@Param("type") String type);

    @Insert("insert into song(name,singer,category,writer,language,issudate) values(#{name},#{singer},#{category},#{writer},#{language},#{issudate})")
    public int insertOne(Song song);

}

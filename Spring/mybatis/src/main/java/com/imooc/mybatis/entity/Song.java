package com.imooc.mybatis.entity;

import java.sql.Date;

public class Song {
    private  Integer id;

    private String name;

    private String singer;

    private String category;

    private String writer;

    private String language;

    private Date issudate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getIssudate() {
        return issudate;
    }

    public void setIssudate(Date issudate) {
        this.issudate = issudate;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", category='" + category + '\'' +
                ", writer='" + writer + '\'' +
                ", language='" + language + '\'' +
                ", issudate=" + issudate +
                '}';
    }
}

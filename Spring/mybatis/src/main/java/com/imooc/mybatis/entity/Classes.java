package com.imooc.mybatis.entity;

import java.util.List;

public class Classes {

    private Integer id;

    private String classNo;

    private String name;

    private String major;

    List<NewStudent> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<NewStudent> getStudents() {
        return students;
    }

    public void setStudents(List<NewStudent> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", classNo='" + classNo + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", students=" + students +
                '}';
    }
}

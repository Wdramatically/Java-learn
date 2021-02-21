package com.imooc.spring.jdbc.entity;

import java.util.Date;

public class Employee {
    private Integer eno;
    private String eName;
    private float salary;
    private String dName;
    private Date hireDate;

    public Integer getEno() {
        return eno;
    }

    public void setEno(Integer eno) {
        this.eno = eno;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eno=" + eno +
                ", eName='" + eName + '\'' +
                ", salary=" + salary +
                ", dName='" + dName + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}

package com.imooc.spring.jdbc;

import com.imooc.spring.jdbc.dao.EmployeeDao;
import com.imooc.spring.jdbc.entity.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        EmployeeDao employeeDao = context.getBean("employeeDao", EmployeeDao.class);
        Employee employee1 = employeeDao.findEmployeeById(3308);
        System.out.println(employee1);
        System.out.println(employeeDao.findEmployeeByDept("研发部"));
        System.out.println(employeeDao.findFieldOfEmployee("张三"));
    }
}

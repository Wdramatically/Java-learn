package com.imooc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StandardJDBCDemo {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            //1.加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.创建数据库连接
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/imooc?serverTimezone=Hongkong&useUnicode=true&characterEncoding=utf-8&userSSL=false",
                    "root",
                    "12590"
            );
            //3.创建Statement对象
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from employee");
            //4.遍历查询结果
            while (rs.next()) {
                Integer empno = rs.getInt(1);
                String ename = rs.getString("ename");
                float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(dname + "-" + empno + "-" + ename + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && connection.isClosed() == false) {
                    //5.关闭连接，释放资源
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

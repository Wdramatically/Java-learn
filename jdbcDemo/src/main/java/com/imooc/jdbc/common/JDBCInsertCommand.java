package com.imooc.jdbc.common;

import com.imooc.DBUtils;

import java.sql.*;
import java.util.Scanner;

public class JDBCInsertCommand implements Command{
    public void executor() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入员工ID：");
        Integer eno = sc.nextInt();
        System.out.println("请输入员工姓名：");
        String ename = sc.next();
        System.out.println("请输入员工薪资：");
        Integer salary = sc.nextInt();
        System.out.println("请输入员工部门：");
        String dname = sc.next();
        try {
            conn = DBUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "insert into employee(eno,ename,salary,dname) values(?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,eno);
            pstmt.setString(2,ename);
            pstmt.setInt(3,salary);
            pstmt.setString(4,dname);
            int count = pstmt.executeUpdate();
            System.out.println("新员工入职！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn,null,pstmt);
        }
    }
}

package com.imooc.jdbc.common;

import com.imooc.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCSearchCommand implements Command{

    public void executor() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        System.out.println("请输入要查询的部门：");
        Scanner input = new Scanner(System.in);
        String dname = input.next();

        try {
            conn = DBUtils.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from employee where dname = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dname);
            rs = pstmt.executeQuery();
            while(rs.next()){
                String ename = rs.getString("ename");
                int salary = rs.getInt("salary");
                System.out.println(ename + "-" + salary + "-" + dname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn,rs,pstmt);
        }
    }
}

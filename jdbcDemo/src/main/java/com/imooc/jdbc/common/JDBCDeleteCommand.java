package com.imooc.jdbc.common;

import com.imooc.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCDeleteCommand implements Command{
    public void executor() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入员工ID：");
        Integer eno = sc.nextInt();
        try {
            conn = DBUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "delete from employee where eno = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,eno);
            int count = pstmt.executeUpdate();
            System.out.println("工号:" + eno + "员工离职！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn,null,pstmt);
        }
    }

}

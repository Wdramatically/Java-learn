package com.imooc.jdbc.common;

import com.imooc.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCUpdateCommand implements Command{
    public void executor() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要加薪的部门：");
        String dname = sc.next();
        try {
            conn = DBUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "update employee set salary = salary +1000 where dname = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dname);
            int count = pstmt.executeUpdate();
            System.out.println(dname + "员工加薪！");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(conn,null,pstmt);
        }
    }
}

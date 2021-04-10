package com.imooc;

import java.sql.*;

public class DBUtils {

    /**
     * 获取连接
     * @return 返回新的连接
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/imooc?serverTimezone=Hongkong&useUnicode=true&characterEncoding=utf-8&userSSL=false",
                "root",
                "12590"
        );
        return connection;
    }

    /**
     *关闭连接
     * @param conn 数据库连接
     * @param rs 结果集
     * @param stmt Statement对象
     */
    public static void closeConnection(Connection conn, ResultSet rs, Statement stmt){
        try {
            if (rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null){
                stmt.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

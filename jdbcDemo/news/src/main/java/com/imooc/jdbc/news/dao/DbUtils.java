package com.imooc.jdbc.news.dao;

import com.imooc.jdbc.news.entity.News;
import com.imooc.jdbc.news.util.CommonUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DbUtils {
    /**
     * 查看新闻
     */
    public static void query() {
        //加载属性文件
        Connection conn = CommonUtils.getConnection();
        String sql = "select * from news";
        QueryRunner qr = new QueryRunner();
        try {
            List<News> result = qr.query(conn, sql, new BeanListHandler<>(News.class));
            for (News news : result) {
                System.out.println(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 按id查询新闻
     *
     * @param id
     * @return
     */
    public static News queryById(Integer id) {
        //加载属性文件
        Connection conn = CommonUtils.getConnection();
        News news = null;
        String sql = "select * from news where id = ?";
        QueryRunner qr = new QueryRunner();
        try {
            news = qr.query(conn, sql, new BeanHandler<News>(News.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return news;
    }

    /**
     * 修改新闻标题及内容
     *
     * @param id
     * @param title
     * @param content
     */
    public static void update(Integer id, String title, String content) {
        Connection conn = CommonUtils.getConnection();
        String sql = "update news set title=?,content=? where id=?";
        QueryRunner qr = new QueryRunner();
        try {
            int update = qr.update(conn, sql, title, content, id);
            if (update > 0) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加新闻
     *
     * @param title
     * @param content
     */
    public static void insert(String title, String content, String createTime) {
        Connection conn = CommonUtils.getConnection();
        String sql = "insert into news(title,content,create_time) values(?,?,?)";
        QueryRunner qr = new QueryRunner();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(createTime);
            int update = qr.update(conn, sql, title, content, new java.sql.Date(date.getTime()));
            if (update > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e ) {
            System.out.println("时间格式输入有误，请重新输入");
        }
        finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除新闻
     *
     * @param id
     */
    public static void delete(Integer id) {
        Connection conn = CommonUtils.getConnection();
        String sql = "delete from news where id = ?";
        QueryRunner qr = new QueryRunner();
        try {
            int update = qr.update(conn, sql, id);
            if (update > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

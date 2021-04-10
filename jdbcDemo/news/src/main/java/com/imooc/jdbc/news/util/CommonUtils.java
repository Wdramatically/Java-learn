package com.imooc.jdbc.news.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.news.dao.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.Properties;

public class CommonUtils {

    /**
     * 获取数据库连接
     * @return 返回新的数据库连接
     */
    public static Connection getConnection(){
        Properties properties = new Properties();
        Connection conn = null;
        try {
            //获取文件路径
            String propertiesFile = DbUtils.class.getResource("/druid-config.properties").getPath();
            //将文件路径反编译为UTF-8
            propertiesFile = new URLDecoder().decode(propertiesFile,"UTF-8");
            //通过输入流加载配置文件
            properties.load(new FileInputStream(propertiesFile));
            //创建datasource
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}

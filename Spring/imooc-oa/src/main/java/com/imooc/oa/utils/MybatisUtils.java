/**
 * FileName: MybatisUtils
 * Author:   86155
 * Date:     2021/5/1 21:53
 * Description:
 */

package com.imooc.oa.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.function.Function;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Object executeQuery(Function<SqlSession, Object> func) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Object result = func.apply(sqlSession);
            return result;
        }finally {
            sqlSession.close();
        }
    }

    public static Object executeUpdate(Function<SqlSession, Object> func){
        //关闭自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        try {
            Object result = func.apply(sqlSession);
            sqlSession.commit();
            return result;
        }finally {
            sqlSession.close();
        }
    }
}

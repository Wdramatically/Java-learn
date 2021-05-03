package com.imooc.oa.test;

import com.imooc.oa.utils.MybatisUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "testServlet", urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = (String)MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.selectOne("test.select");
        });
        request.setAttribute("result",result);
        request.getRequestDispatcher("/test.ftl").forward(request,response);
    }
}

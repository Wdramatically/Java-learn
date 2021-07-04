package com.imooc.oa.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ForwordServlet", urlPatterns = "/forward/*")
public class ForwordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String substring = url.substring(1);
        String path = substring.substring(substring.indexOf('/'));
        request.getRequestDispatcher(path + ".ftl").forward(request,response);
    }
}

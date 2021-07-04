package com.imooc.oa.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.oa.entity.Notice;
import com.imooc.oa.entity.User;
import com.imooc.oa.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "NoticeServlet", value = "/notice/*")
public class NoticeServlet extends HttpServlet {
    NoticeService noticeService = new NoticeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void getNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        Long employeeId = user.getEmployeeId();
        List<Notice> notices = noticeService.getNotice(employeeId);
        Map result = new HashMap<>();
        result.put("code", "0");
        result.put("data", notices);
        result.put("message", "");
        result.put("count", notices.size());
        String json = JSON.toJSONString(result);
        response.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = request.getRequestURI();
        String pattern = url.substring(url.lastIndexOf("/") + 1);
        if ("list".equals(pattern)) {
            getNotice(request, response);
        }
    }
}

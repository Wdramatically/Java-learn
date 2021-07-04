package com.imooc.oa.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.oa.entity.LeaveForm;
import com.imooc.oa.entity.Notice;
import com.imooc.oa.entity.User;
import com.imooc.oa.service.LeaveService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LeaveFormServlet", urlPatterns = "/leave/*")
public class LeaveFormServlet extends HttpServlet {
    private LeaveService leaveService = new LeaveService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = request.getRequestURI();
        String pattern = url.substring(url.lastIndexOf("/") + 1);
        if ("create".equals(pattern)) {
            createLeaveForm(request, response);
        } else if ("list".equals(pattern)) {
            getLeaveFormList(request, response);
        } else if ("audit".equals(pattern)) {
            auditLeaveForm(request, response);
        }
    }

    private void createLeaveForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
        Map<String, Object> result = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        String formType = request.getParameter("formType");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String reason = request.getParameter("reason");
        try {
            LeaveForm leaveForm = new LeaveForm();
            leaveForm.setEmployeeId(user.getEmployeeId());
            leaveForm.setFormType(Integer.parseInt(formType));
            leaveForm.setStartTime(sdf.parse(startTime));
            leaveForm.setEndTime(sdf.parse(endTime));
            leaveForm.setCreateTime(new Date());
            leaveForm.setReason(reason);
            leaveService.createLeaveFrom(leaveForm);
            result.put("code", 0);
            result.put("message", "申请成功");
        } catch (Exception e) {
            result.put("code", e.getClass().getSimpleName());
            result.put("message", e);
            e.printStackTrace();
        }
        String json = JSON.toJSONString(result);
        response.getWriter().println(json);
    }

    private void getLeaveFormList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        List<Map> leaveList = leaveService.getLeaveList(user.getEmployeeId(), "process");
        Map result = new HashMap<>();
        result.put("code", "0");
        result.put("data", leaveList);
        result.put("message", "");
        result.put("count", leaveList.size());
        String json = JSON.toJSONString(result);
        response.getWriter().println(json);
    }

    private void auditLeaveForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        Long formId = Long.parseLong(request.getParameter("formId"));
        String result = request.getParameter("result");
        String reason = request.getParameter("reason");
        Map responseResult = new HashMap<>();
        try {
            leaveService.audit(formId, user.getEmployeeId(), result, reason);
            responseResult.put("code", "0");
            responseResult.put("message", "success");
        }catch (Exception e){
            responseResult.put("code", e.getClass().getSimpleName());
            responseResult.put("message", e.getMessage());
        }
        String json = JSON.toJSONString(responseResult);
        response.getWriter().println(json);
    }
}

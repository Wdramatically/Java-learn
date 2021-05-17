package com.imooc.oa.controller;

import com.imooc.oa.dao.EmployeeDao;
import com.imooc.oa.entity.Department;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.entity.Node;
import com.imooc.oa.entity.User;
import com.imooc.oa.service.DepartmentService;
import com.imooc.oa.service.EmployeeService;
import com.imooc.oa.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "indexServlet", urlPatterns = "/index")
public class indexServlet extends HttpServlet {
    private UserService userService = new UserService();
    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        List<Node> node = userService.selectNodeByUserId(user.getUserId());
        Employee employee = employeeService.selectEmployeeById(user.getEmployeeId());
        Department department = departmentService.selectDepartmentById(employee.getDepartmentId());
        request.setAttribute("node_list", node);
        request.setAttribute("current_employee", employee);
        request.setAttribute("current_department", department);
        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

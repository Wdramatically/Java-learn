package com.imooc.springmvc.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*获取用户ip地址*/
        request.getRemoteAddr();
        /*获取访问地址*/
        request.getRequestURL();
        request.getHeader("");
        System.out.println("拦截器配置成功！");
        return true;
    }
}

package com.imooc.springmvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class MyInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] usernames = request.getParameterValues("username");
        String[] passwords = request.getParameterValues("password");
        StringBuilder sb = new StringBuilder();
        sb.append(request.getRemoteAddr());
        sb.append('|');
        sb.append(request.getRequestURI());
        sb.append('|');
        sb.append(request.getHeader("user-agent"));
        logger.info(sb.toString());

        Object user = request.getAttribute("user");
        if (Objects.isNull(user) && (Objects.isNull(usernames) || Objects.isNull(passwords))){
            return false;
        }else {
            return true;
        }
    }
}

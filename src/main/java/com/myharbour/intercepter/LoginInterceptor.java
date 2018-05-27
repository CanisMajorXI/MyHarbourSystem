package com.myharbour.intercepter;

//import com.redsun.pojo.User;

import com.myharbour.pojo.User;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        String uri = request.getRequestURI().toLowerCase();
        HttpSession session = request.getSession(false);
        if (session == null) return sendRedirect(response);
        User user = (User) session.getAttribute("user");
        if (user == null) return sendRedirect(response);
        if (uri.contains("/operator.html")) {
            if (user.getType() != User.TYPE_OPERATOR) return sendRedirect(response);
        }
        if (uri.contains("/shipper.html")) {
            if (user.getType() != User.TYPE_SHIPPER) return sendRedirect(response);
        }
        // /api/operator/**","/api/shipper/**
        if (uri.contains("/api/operator")) {
            if (user.getType() != User.TYPE_OPERATOR) return false;
        }
        if (uri.contains("/api/shipper")) {
            if (user.getType() != User.TYPE_SHIPPER) return false;
        }

        return true;
    }

    public boolean sendRedirect(HttpServletResponse response) throws Exception {
        System.out.println("用户未登录！");
        response.sendRedirect("/login.html");
        return false;
    }
}
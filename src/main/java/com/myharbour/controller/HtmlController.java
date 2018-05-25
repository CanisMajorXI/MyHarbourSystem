package com.myharbour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HtmlController {
    //外部路径到内部路径的映射
    @RequestMapping("/*.html")
    public String GetInternalPath(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        return request.getRequestURI();
    }

}

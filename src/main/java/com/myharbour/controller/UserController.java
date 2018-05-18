package com.myharbour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping("/login")
    @ResponseBody
    public boolean login() {
        return false;
    }

}

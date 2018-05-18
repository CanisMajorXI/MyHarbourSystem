package com.myharbour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/operator")
public class OperatorController {

    @RequestMapping("/get/containers/")
    public ModelAndView getContainers(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            //todo
            modelAndView.setView(new MappingJackson2JsonView());
            return modelAndView;
        }catch (Exception e){
         e.printStackTrace();
         return modelAndView;
        }
    }
}

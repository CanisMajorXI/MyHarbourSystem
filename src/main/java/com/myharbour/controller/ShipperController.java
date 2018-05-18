package com.myharbour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/shipper")
public class ShipperController {

    /**
     * @return
     */

    @RequestMapping("/get/cargoattr")
    public ModelAndView getCargoAttr(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            //todo
            modelAndView.setView(new MappingJackson2JsonView());
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return modelAndView;
        }
    }

    /**
     * @param type
     * @param size
     * @return
     */
    @RequestMapping("/add/emptycontainer")
    @ResponseBody
    public Integer addEmptyContainer(@RequestParam("type") Integer type, @RequestParam("size") Integer size, HttpSession session) {
        try {
            if (type == null || size == null) return null;
            //todo
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param cargo_type
     * @return
     */
    @RequestMapping("/add/cargo")
    @ResponseBody
    public Integer addCargo(@RequestParam("type") Integer cargo_type, HttpSession session) {
        try {
            if (cargo_type == null) return null;
            //todo
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param cargo_type
     * @param size
     * @param amount
     * @return
     */
    @RequestMapping("/add/containerwithcargo")
    @ResponseBody
    public String addContainerWithCargo(@RequestParam("container_type") Integer cargo_type,
                                        @RequestParam("size") Integer size,
                                        @RequestParam("amount") Integer amount, HttpSession session) {
        try {
            if (cargo_type == null || size == null || amount == null) return null;
            if (!(amount == 1 || amount == 2)) return null;
            if (size == 1 && amount == 2) return null;
            //todo
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/get/info")
    public ModelAndView getInfo(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Integer id = (Integer) session.getAttribute("id");
            //todo
            modelAndView.setView(new MappingJackson2JsonView());
            return modelAndView;
        }catch (Exception e){
            e.printStackTrace();
            return modelAndView;
        }
    }
}
/*
*    try {

            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }*/
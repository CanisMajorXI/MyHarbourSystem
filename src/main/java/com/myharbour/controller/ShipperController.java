package com.myharbour.controller;

import com.myharbour.pojo.ResultantCargoAttr;
import com.myharbour.pojo.User;
import com.myharbour.service.CargoAttrService;
import com.myharbour.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/shipper")
public class ShipperController {

    @Autowired
    private CargoAttrService cargoAttrService = null;

    @Autowired
    private SubmitService submitService = null;

    /**
     * @return
     */
//http://localhost:8080/api/shipper/get/cargoattrs
    @RequestMapping("/get/cargoattrs")
    public ModelAndView getCargoAttrs(ModelMap modelMap, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            //todo
            List<ResultantCargoAttr> resultantCargoAttrs = cargoAttrService.getCargoAttrs();
            modelMap.addAttribute("cargoAttrs", resultantCargoAttrs);
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
    @RequestMapping("/add/empty-container")
    @ResponseBody
    public boolean addEmptyContainer(@RequestParam("type") Integer type,
                                     @RequestParam("size") Integer size, HttpSession session) {
        if (type == null || size == null) return false;
        User user = (User) session.getAttribute("user");

        if (user == null || user.getType() != User.TYPE_SHIPPER) return false;
        try {
            submitService.submitAnEmptyContainer(user.getUserId(), type, size);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param cargo_type
     * @return
     */
    @RequestMapping("/add/cargo")
    @ResponseBody
    public Integer addCargo(@RequestParam("type") Integer cargo_type) {
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
    @RequestMapping("/add/container-with-cargo")
    @ResponseBody
    public String addContainerWithCargo(@RequestParam("container_type") Integer cargo_type,
                                        @RequestParam("size") Integer size,
                                        @RequestParam("amount") Integer amount) {
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
        } catch (Exception e) {
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
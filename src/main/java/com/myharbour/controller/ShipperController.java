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
     * @param cargoType
     * @param gross
     * @param session
     * @return
     */
    @RequestMapping("/add/cargo")
    @ResponseBody
    public boolean addCargo(@RequestParam("type") Integer cargoType,
                            @RequestParam("gross") Integer gross, HttpSession session) {
        if (cargoType == null || gross == null) return false;
        User user = (User) session.getAttribute("user");
        if (user == null || user.getType() != User.TYPE_SHIPPER) return false;
        try {
            submitService.submitBulkCargo(user.getUserId(), cargoType, gross);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param type
     * @param size
     * @param amount
     * @param cargoType1
     * @param cargoType2
     * @param gross1
     * @param gross2
     * @return
     */
    @RequestMapping("/add/container-with-cargos")
    @ResponseBody
    public boolean addContainerWithCargo(@RequestParam("type") Integer type,
                                         @RequestParam("size") Integer size,
                                         @RequestParam("amount") Integer amount,
                                         @RequestParam("cargo_type1") Integer cargoType1,
                                         @RequestParam(value = "cargo_type2", required = false) Integer cargoType2,
                                         @RequestParam("gross1") Integer gross1,
                                         @RequestParam(value = "gross2", required = false) Integer gross2) {
        if (type == null || size == null || amount == null || cargoType1 == null || gross1 == null) return false;
        if (!(amount == 1 || amount == 2)) return false;
        //todo
        if (size == 1 && amount == 2) return false;
        if ((size == 1 && gross2 != null)) return false;
        try {
            //todo
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
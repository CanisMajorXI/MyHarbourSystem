package com.myharbour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/shipper")
public class ShipperController {

    @RequestMapping("/addemptycontainer")
    public boolean addEmptyContainer(@RequestParam("type") Integer type, @RequestParam("size") Integer size) {
        try {
            if (type == null || size == null) return false;
            //todo
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/addcargo")
    public boolean addCargo(@RequestParam("type") Integer cargo_type, @RequestParam("size") Integer gross) {
        try {
            if(cargo_type == null || gross == null) return false;
            //todo
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
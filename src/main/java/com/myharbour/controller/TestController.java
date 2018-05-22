package com.myharbour.controller;

import com.myharbour.dao.CargoAttrMapper;
import com.myharbour.pojo.CargoAttr;
import com.myharbour.pojo.RowBounds;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private CargoAttrMapper cargoAttrMapper = null;

    @RequestMapping("/1")
    @ResponseBody
    public CargoAttr test() {
       CargoAttr cargoAttr = cargoAttrMapper.getCargoAttrById(10000001,new RowBounds());
       return cargoAttr;
    }
    @RequestMapping("/2")
    @ResponseBody
    public CargoAttr test2() {
        CargoAttr cargoAttr = new CargoAttr();
        cargoAttr.setCargoTypeId(10000044);
        cargoAttr.setCargoName("角蛙");
        cargoAttr.setContainerType(0);
        cargoAttr.setValid(true);
        cargoAttr.setUnitType("只");
        cargoAttrMapper.insertCargoAttr(cargoAttr);
       /// CargoAttr cargoAttr = cargoAttrMapper.getCargoAttrById(10000001,new RowBounds());
        return cargoAttr;
    }
    @RequestMapping("/3")
    @ResponseBody
    public CargoAttr test3() {
        CargoAttr cargoAttr = new CargoAttr();
        cargoAttr.setCargoTypeId(10000033);
        cargoAttr.setCargoName("jaingzemin");
        cargoAttr.setContainerType(0);
        cargoAttr.setValid(true);
        cargoAttr.setUnitType("zhi");
        cargoAttrMapper.insertCargoAttr(cargoAttr);
        /// CargoAttr cargoAttr = cargoAttrMapper.getCargoAttrById(10000001,new RowBounds());
        return cargoAttr;
    }
    @RequestMapping("/4")
    @ResponseBody
    public CargoAttr test3(@RequestParam("name") String name) {
        CargoAttr cargoAttr = new CargoAttr();
        cargoAttr.setCargoTypeId(10000024);
        cargoAttr.setCargoName(name);
        cargoAttr.setContainerType(0);
        cargoAttr.setValid(true);
        cargoAttr.setUnitType("zhi");
        cargoAttrMapper.insertCargoAttr(cargoAttr);
        /// CargoAttr cargoAttr = cargoAttrMapper.getCargoAttrById(10000001,new RowBounds());
        return cargoAttr;
    }
}

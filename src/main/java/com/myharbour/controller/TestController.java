package com.myharbour.controller;

import com.myharbour.dao.CargoAttrMapper;
import com.myharbour.dao.CargoMapper;
import com.myharbour.dao.ContainerMapper;
import com.myharbour.pojo.Cargo;
import com.myharbour.pojo.CargoAttr;
import com.myharbour.pojo.Container;
import com.myharbour.pojo.ResultantCargoInfo;
import com.myharbour.service.ExportService;
import com.myharbour.service.InsertablePositionService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private CargoAttrMapper cargoAttrMapper = null;
    @Autowired
    private CargoMapper cargoMapper = null;
    @Autowired
    private ContainerMapper containerMapper = null;
    @Autowired
    private ExportService exportService = null;
    @Autowired
    private InsertablePositionService insertablePositionService = null;


    @RequestMapping("/1")
    @ResponseBody
    public CargoAttr test1() {
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
    @RequestMapping("/getcargo1")
    @ResponseBody
    public List<Cargo> cargo1(){
        List<Cargo> cargos = cargoMapper.getCargos(null,null,null,null,null,new RowBounds());
        return cargos;
    }
    @RequestMapping("/container1")
    @ResponseBody
    public List<Cargo> container1(){
        List<Cargo> cargos = cargoMapper.getCargoById(10000001,new RowBounds());
        return cargos;
    }
    @RequestMapping("/10")
    @ResponseBody
    public boolean t10(){
//        Cargo cargo = new Cargo();
//        cargo.setContainerId(10000015);
//        cargo.setValid(false);
//        cargoMapper.updateCargo(cargo);
        Container container = new Container();
        container.setContainerId(10000015);
        container.setValid(false);
        containerMapper.updateContainer(container);
        return true;
    }

    @RequestMapping("/test")
    @ResponseBody
    public boolean test(@RequestParam("id")Integer id) {
        insertablePositionService.getInsertablePosition(id);
        return true;
    }
}

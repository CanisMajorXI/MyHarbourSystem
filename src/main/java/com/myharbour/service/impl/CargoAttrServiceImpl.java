package com.myharbour.service.impl;

import com.myharbour.dao.CargoAttrMapper;
import com.myharbour.pojo.CargoAttr;
import com.myharbour.pojo.ResultantCargoAttr;
import com.myharbour.service.CargoAttrService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoAttrServiceImpl implements CargoAttrService {

    @Autowired
    private CargoAttrMapper cargoAttrMapper = null;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public List<ResultantCargoAttr> getCargoAttrs() {
        CargoAttr cargoAttr1 = new CargoAttr();
        List<CargoAttr> cargoAttrs = cargoAttrMapper.getCargoAttrs(null, null, null, null);
        List<ResultantCargoAttr> list = new ArrayList<>();
        for (CargoAttr cargoAttr : cargoAttrs) {
            ResultantCargoAttr resultantCargoAttr = new ResultantCargoAttr();
            resultantCargoAttr.setCargoTypeId(cargoAttr.getCargoTypeId());
            resultantCargoAttr.setNameAndUnit("货物名称：" + cargoAttr.getCargoName() + "  单位：" + cargoAttr.getUnitType());
            resultantCargoAttr.setContainerTypeId(cargoAttr.getContainerType());
            list.add(resultantCargoAttr);
        }
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void addACargoAttr(Integer cargoTypeId, String cargoName, Integer containerType, String unitType) {
        //todo
    }
}

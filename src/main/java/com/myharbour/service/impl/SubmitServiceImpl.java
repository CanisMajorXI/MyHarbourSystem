package com.myharbour.service.impl;

import com.myharbour.dao.CargoAttrMapper;
import com.myharbour.dao.CargoMapper;
import com.myharbour.dao.ContainerMapper;
import com.myharbour.pojo.Cargo;
import com.myharbour.pojo.CargoAttr;
import com.myharbour.pojo.Container;
import com.myharbour.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubmitServiceImpl implements SubmitService {

    @Autowired
    private ContainerMapper containerMapper = null;

    @Autowired
    private CargoMapper cargoMapper = null;

    @Autowired
    private CargoAttrMapper cargoAttrMapper = null;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void submitAnEmptyContainer(Integer userId,
                                       Integer type,
                                       Integer size) {
        Container container = new Container();
        container.setUserId(userId);
        container.setType(type);
        container.setSize(size);
        //空箱位置都设置为0
        container.setRow(0);
        container.setColumn(0);
        container.setLayer(0);
        container.setContainerArea(Container.AREA_TASK);
        containerMapper.insertContainer(container);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void submitAContainerWithCargos(Integer userId,
                                           Integer containerType,
                                           Integer size,
                                           Integer amount,
                                           Integer[] cargoTypes, Integer[] grosses) {
        Container container = new Container();
        for (int i = 0; i < amount; i++) {
            CargoAttr cargoAttr = cargoAttrMapper.getCargoAttrById(cargoTypes[i]);
            if (cargoAttr == null || !cargoAttr.getContainerType().equals(containerType)) {
                System.out.println("没有该种类货物或者箱子类型和货物类型不符合！");
                throw new RuntimeException();
            }
        }
        //空箱位置都设置为0
        container.setRow(0);
        container.setColumn(0);
        container.setLayer(0);
        container.setContainerArea(Container.AREA_TASK);
        container.setUserId(userId);
        container.setType(containerType);
        container.setSize(size);
        //使用了主键回填
        containerMapper.insertContainer(container);
        for (int i = 0; i < amount; i++) {
            Cargo cargo = new Cargo();
            cargo.setUserId(userId);
            cargo.setCargoTypeId(cargoTypes[i]);
            cargo.setGross(grosses[i]);
            cargo.setContainerId(container.getContainerId());
            cargoMapper.insertCargo(cargo);
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void submitBulkCargo(Integer userId, Integer cargoType, Integer gross) {
        CargoAttr cargoAttr = cargoAttrMapper.getCargoAttrById(cargoType);
        if (cargoAttr == null) {
            System.out.println("没有该类型货物！");
            throw new RuntimeException();
        }
        Cargo cargo = new Cargo();
        cargo.setCargoTypeId(cargoType);
        cargo.setUserId(userId);
        cargo.setGross(gross);
        cargoMapper.insertCargo(cargo);
    }
}

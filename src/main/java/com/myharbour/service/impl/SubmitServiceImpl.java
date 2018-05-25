package com.myharbour.service.impl;

import com.myharbour.dao.CargoMapper;
import com.myharbour.dao.ContainerMapper;
import com.myharbour.pojo.Cargo;
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

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void submitAnEmptyContainer(Integer userId,
                                       Integer type,
                                       Integer size) {
        Container container = new Container();
        container.setUserId(userId);
        container.setType(type);
        container.setSize(size);
        container.setContainerArea(Container.AREA_TASK);
        containerMapper.insertContainer(container);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void submitAContainerWithCargos(Integer userId,
                                           Integer size,
                                           Integer cargo_type,
                                           Integer amount, Integer... gross) {

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void submitBulkCargo(Integer userId, Integer cargoType, Integer gross) {
        Cargo cargo = new Cargo();
        cargo.setCargoTypeId(cargoType);
        cargo.setUserId(userId);
        cargo.setGross(gross);
        cargoMapper.insertCargo(cargo);
    }
}

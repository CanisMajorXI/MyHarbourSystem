package com.myharbour.service.impl;

import com.myharbour.service.SubmitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubmitServiceImpl implements SubmitService {

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void submitAnEmptyContainer(Integer containerId, Integer userId, Integer type, Integer size) {

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void submitAContainerWithCargos(Integer cargo_type, Integer size, Integer amount) {

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public void submitBulkCargo(Integer cargoType) {

    }
}

package com.myharbour.service.impl;

import com.myharbour.pojo.Container;
import com.myharbour.service.QueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryServiceImpl implements QueryService{


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    @Override
    public List<Container> getContainersBySpecificParas(Integer size, Integer type, Integer area, int page) {
        //test
        List<Container>containers = new ArrayList<>();
        for(int i = 0;i< 10;i++){
            Container container = new Container();
            container.setContainerId(10000000+i);
            container.setSize(i%2);
            container.setType(i%3);
            container.setContainerArea(i%4);
            container.setLayer(0);
            container.setColumn(0);
            container.setRow(0);
            containers.add(container);
        }
        return containers;
        //test
    }
}

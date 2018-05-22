package com.myharbour.service.impl;

import com.myharbour.dao.ContainerMapper;
import com.myharbour.pojo.Container;
import com.myharbour.service.QueryService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private ContainerMapper containerMapper = null;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public Integer getCountBySpecificParas(Integer size, Integer type, Integer area) {
        return containerMapper.getContainers(null, area, null, null, null,
                null, type, size, new RowBounds()).size();
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public List<Container> getContainersBySpecificParas(Integer size, Integer type, Integer area, int page) {
        int limit = 9;
        RowBounds rowBounds = new RowBounds((page-1)*limit,limit);
        return containerMapper.getContainers(null, area, null, null, null,
                null, type, size, rowBounds);
        //test
//        List<Container>containers = new ArrayList<>();
//        for(int i = 0;i< 10;i++){
//            Container container = new Container();
//            container.setContainerId(10000000+i);
//            container.setSize(i%2);
//            container.setType(i%3);
//            container.setContainerArea(i%4);
//            container.setLayer(0);
//            container.setColumn(0);
//            container.setRow(0);
//            containers.add(container);
//        }
//        return containers;
        //test
    }
}

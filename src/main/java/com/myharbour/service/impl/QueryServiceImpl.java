package com.myharbour.service.impl;

import com.myharbour.dao.CargoMapper;
import com.myharbour.dao.ContainerMapper;
import com.myharbour.pojo.Cargo;
import com.myharbour.pojo.Container;
import com.myharbour.pojo.ResultantCargoInfo;
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

    @Autowired
    private CargoMapper cargoMapper = null;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public List<Cargo> getCargosByContainerId(int containerId) {
        return cargoMapper.getCargos(null, null, null, null, containerId, new RowBounds());
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public Integer getContainersCountBySpecificParas(Integer size, Integer type, Integer area) {
        return containerMapper.getContainers(null, area, null, null, null,
                null, type, size, new RowBounds()).size();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public Integer getCargosCountByBySpecificParas(Integer containerId, Integer containerType) {
        return cargoMapper.getCountByBySpecificParas(containerId, containerType);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public List<ResultantCargoInfo> getResultantCargoInfoBySpecificParas(Integer containerId,
                                                                         Integer containerType,
                                                                         Integer userId,
                                                                         int page) {

        int limit = 9;//前端一页显示的数量
        RowBounds rowBounds = page == 0 ? new RowBounds() : new RowBounds((page - 1) * limit, limit);
        return cargoMapper.getResultantCargoInfoBySpecificParas(containerId, containerType,userId, rowBounds);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public List<Container> getContainersBySpecificParas(Integer size,
                                                        Integer type,
                                                        Integer area,
                                                        Integer userId,
                                                        int page) {
        int limit = 9;//前端一页显示的数量
        RowBounds rowBounds = page == 0 ? new RowBounds() : new RowBounds((page - 1) * limit, limit);
        return containerMapper.getContainersWithEmptyStatus(null, area, userId, null, null,
                null, type, size, rowBounds);
    }

    @Override
    public void test() {

    }
}

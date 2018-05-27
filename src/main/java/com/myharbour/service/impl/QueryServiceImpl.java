package com.myharbour.service.impl;

import com.myharbour.dao.CargoMapper;
import com.myharbour.dao.ContainerMapper;
import com.myharbour.pojo.Cargo;
import com.myharbour.pojo.Container;
import com.myharbour.pojo.Position;
import com.myharbour.pojo.ResultantCargoInfo;
import com.myharbour.service.InsertablePositionService;
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

    @Autowired
    private InsertablePositionService insertablePositionService = null;

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
        return cargoMapper.getResultantCargoInfoBySpecificParas(null, containerId, containerType, userId, rowBounds);
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

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public List<Position> getInsertablePosition(Integer containerId, Integer op) {
        switch (op) {
            case InsertablePositionService.OP_IMPORT:
                return insertablePositionService.getInsertablePositionById(containerId);
            case InsertablePositionService.OP_LOAD:
                List<Container> tempContainerList = containerMapper.getContainersWithEmptyStatus(containerId, null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        new RowBounds());
                if (tempContainerList.size() != 1) throw new RuntimeException();
                Container myContainer = tempContainerList.get(0);
                myContainer.setEmptyStatus(2);
                return insertablePositionService.getInsertablePosition(myContainer);
            case InsertablePositionService.OP_MOVE:
                return insertablePositionService.getInsertablePositionById(containerId);
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public List<Container> getLoadableEmptyContainers(Integer cargoId) {
        ResultantCargoInfo resultantCargoInfo = cargoMapper.getResultantCargoInfoBySpecificParas(cargoId, null, null,
                null, new RowBounds()).get(0);
        int containerType = resultantCargoInfo.getCargoAttr().getContainerType();
        List<Container> containers = containerMapper.getContainers(null,
                Container.AREA_EMPTY,
                null,
                null,
                null,
                null,
                null,
                containerType, new RowBounds());
        return containers;
    }
}

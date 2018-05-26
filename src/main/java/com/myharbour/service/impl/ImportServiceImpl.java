package com.myharbour.service.impl;

import com.myharbour.dao.ContainerMapper;
import com.myharbour.pojo.Container;
import com.myharbour.pojo.Position;
import com.myharbour.service.ImportService;
import com.myharbour.service.InsertablePositionService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImportServiceImpl implements ImportService {

    @Autowired
    private ContainerMapper containerMapper = null;
    @Autowired
    private InsertablePositionService insertablePositionService = null;


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public void importAContainer(Integer containerId, Position position) {
        Container myContainer = containerMapper.getContainersWithEmptyStatus(containerId,
                null, null, null, null, null, null, null, new RowBounds()).get(0);
        if (myContainer.getEmptyStatus() == 0 && position.getArea() != Container.AREA_EMPTY)
            throw new RuntimeException();
        if (myContainer.getEmptyStatus() != 0 && position.getArea() == Container.AREA_EMPTY)
            throw new RuntimeException();
        if (myContainer.getEmptyStatus() != 0 && myContainer.getType() + 1 != position.getArea())
            throw new RuntimeException();
        List<Position> positionList = insertablePositionService.getInsertablePosition(myContainer);
        if (!positionList.contains(position)) throw new RuntimeException();
        Container container = new Container();
        container.setContainerId(myContainer.getContainerId());
        container.setRow(position.getRow());
        container.setColumn(position.getColumn());
        container.setLayer(position.getLayer());
        container.setContainerArea(position.getArea());
        containerMapper.updateContainer(container);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public void loadACargoIntoAnEmptyContainer(Integer cargoId, Integer containerId, Position position) {

    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public void movePosition(Integer containerId, Position position) {

    }
}

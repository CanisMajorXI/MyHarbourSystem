package com.myharbour.service.impl;

import com.myharbour.dao.ContainerMapper;
import com.myharbour.pojo.*;
import com.myharbour.service.InsertablePositionService;
import com.myharbour.util.InsertablePositionTool;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InsertablePositionServiceImpl implements InsertablePositionService {

    @Autowired
    private ContainerMapper containerMapper = null;

    /**
     * @param containerId
     * @return 根据ID查询箱子的信息，确定符合该箱子的可插入位置，返回Position的列表
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public List<Position> getInsertablePosition(Integer containerId) {
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
        int destArea = 0;
        switch (myContainer.getContainerArea()) {
            case Container.AREA_TASK:
            case Container.AREA_EMPTY:
                destArea = myContainer.getEmptyStatus() == 0 ? 0 : myContainer.getType() + 1;
                break;
            case Container.AREA_ORDINARY:
            case Container.AREA_FREEZE:
            case Container.AREA_HAZARD:
                destArea = myContainer.getContainerArea();
                break;
        }
        List<Container> destContainerList = containerMapper.getContainers(null,
                destArea,
                null,
                null,
                null,
                null,
                null,
                null
                , new RowBounds());
        int a =4;
        List<Position> list = InsertablePositionTool.getInsertablePosition(Area.getInstanceByArea(destArea),destContainerList,0);
      int aw =4;
//        List<containerMapper.getContainersWithEmptyStatus (null, destArea, null,
//                null, null, null, null, null, new RowBounds());
        return null;
    }


}

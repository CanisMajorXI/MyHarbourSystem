package com.myharbour.dao;

import com.myharbour.pojo.Container;
import com.myharbour.pojo.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerMapper {
    List<Container> getValidContainers(Container container, RowBounds rowBounds);

    List<Container> getUnvalidContainers(Container container, RowBounds rowBounds);

    List<Container> getContainerById(Integer id,RowBounds rowBounds);

    void insertContainer(Container container);

    void updateContainer(Container container);
}

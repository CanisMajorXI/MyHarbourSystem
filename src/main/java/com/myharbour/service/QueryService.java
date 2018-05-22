package com.myharbour.service;

import com.myharbour.pojo.Cargo;
import com.myharbour.pojo.Container;

import java.util.List;

public interface QueryService {
    /**
     * @param containerId
     * @return
     * 根据箱子id返回箱子Pojo,因为id是主键，所以返回的是单个
     */
    Container getContainerById(int containerId);

    /**
     *
     * @param containerId
     * @return
     * 根据箱子Id返回该箱子中的货物，小箱子最多有一份货物，大箱子可以有1份或者两份货物
     */
    List<Cargo> getCargosByContainerId(int containerId);

    /**
     *
     * @return
     *获取所有散货
     */
    List<Cargo> getBulkCargos();


    List<Container> getOutwardContainers();
}

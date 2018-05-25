package com.myharbour.service;

import com.myharbour.pojo.Cargo;
import com.myharbour.pojo.Container;
import com.myharbour.pojo.ResultantCargoInfo;

import java.util.List;

public interface QueryService {
    /**
     * @param containerId
     * @return 根据箱子id返回箱子Pojo, 因为id是主键，所以返回的是单个
     */
//    Container getContainerById(int containerId);
//
    /**
     * @param containerId
     * @return 根据箱子Id返回该箱子中的货物，小箱子最多有一份货物，大箱子可以有1份或者两份货物
     */
    List<Cargo> getCargosByContainerId(int containerId);
//


//    /**
//     * @return 获取所有散货
//     */
//    List<Cargo> getBulkCargos(int page);
//
//    /**
//     *
//     * @param size
//     * @param type
//     * @param area
//     * @param page
//     * @return
//     */

    Integer getCountBySpecificParas(Integer size, Integer type, Integer area);

    List<Container> getContainersBySpecificParas(Integer size,
                                                 Integer type,
                                                 Integer area,
                                                 int page);

    /**
     *
     * @param containerId
     * @return
     */
    List<ResultantCargoInfo> getResultantCargoInfoBySpecificParas(Integer containerId,Integer containerType);

//    /**
//     * @return 获取所有的在仓库外的箱子
//     */
//    List<Container> getOutwardContainers();
//
//    /**
//     * @return 获取所有的在仓库内的箱子
//     * public static final int TOTAL_ROWS_EMPTY = 5;
//     * public static final int TOTAL_ROWS_ORDINARY = 10;
//     * public static final int TOTAL_ROWS_FREEZE = 6;
//     * public static final int TOTAL_ROWS_HAZARD = 6;
//     */
//
//    List<Container> getInwardContainers();
//
//    /**
//     *
//     * @return
//     */
//    List<Container> getContainersInEmptyArea();
//
//    /**
//     *
//     * @return
//     */
//    List<Container> getContainersInOrdinaryArea();
//
//    /**
//     *
//     * @return
//     */
//    List<Container> getContainersInFreezeArea();
//
//    /**
//     *
//     * @return
//     */
//    List<Container> getContainersInHazardArea();

}

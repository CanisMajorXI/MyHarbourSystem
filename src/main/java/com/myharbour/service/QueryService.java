package com.myharbour.service;

import com.myharbour.pojo.Cargo;
import com.myharbour.pojo.Container;
import com.myharbour.pojo.Position;
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


    /**
     * 获取箱子总数量，配合分页使用
     * @param size
     * @param type
     * @param area
     * @return
     */
    Integer getContainersCountBySpecificParas(Integer size, Integer type, Integer area);

    /**
     * 获取散货的总数量，配合分页使用
     * @param containerId
     * @param containerType
     * @return
     */
    Integer getCargosCountByBySpecificParas(Integer containerId, Integer containerType);

    List<Container> getContainersBySpecificParas(Integer size,
                                                 Integer type,
                                                 Integer area,
                                                 Integer userId,
                                                 int page);

    /**
     * @param containerId
     * @return
     */
    List<ResultantCargoInfo> getResultantCargoInfoBySpecificParas(Integer containerId,
                                                                  Integer containerType,
                                                                  Integer userId,
                                                                  int page);


    List<Position> getInsertablePosition(Integer containerId,Integer op);
//
//    /**
//     * @return 获取所有的在仓库内的箱子
//     * public static final int TOTAL_ROWS_EMPTY = 5;
//     * public static final int TOTAL_ROWS_ORDINARY = 10;
//     * public static final int TOTAL_ROWS_FREEZE = 6;
//     * public static final int TOTAL_ROWS_HAZARD = 6;
//     */
//

}

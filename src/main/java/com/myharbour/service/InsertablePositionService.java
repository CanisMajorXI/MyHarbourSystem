package com.myharbour.service;


import com.myharbour.pojo.Container;
import com.myharbour.pojo.Position;

import java.util.List;

//该服务提供可插入位置的查询
public interface InsertablePositionService {

    public static final int OP_IMPORT = 0;
    public static final int OP_LOAD = 1;
    public static final int OP_MOVE = 2;

    List<Position> getInsertablePosition(Container container);

    List<Position> getInsertablePositionById(Integer containerId);
}

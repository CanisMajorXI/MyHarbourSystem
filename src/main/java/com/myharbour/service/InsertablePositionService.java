package com.myharbour.service;


import com.myharbour.pojo.Position;

import java.util.List;

//该服务提供可插入位置的查询
public interface InsertablePositionService {

    List<Position> getInsertablePosition(Integer containerId);
}

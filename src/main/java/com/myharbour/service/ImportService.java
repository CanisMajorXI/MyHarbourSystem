package com.myharbour.service;

import com.myharbour.pojo.Position;

/**
 * 从任务区导入的仓库
 */
public interface ImportService {

    void importAContainer(Integer containerId, Position position);

    void loadACargoIntoAnEmptyContainer(Integer cargoId,Integer containerId,Position position);

    void movePosition(Integer containerId, Position position);
}

package com.myharbour.service.impl;

import com.myharbour.pojo.Position;
import com.myharbour.service.InsertablePositionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InsertablePositionServiceImpl implements InsertablePositionService{

    /**
     *
     * @param containerId
     * @return
     * 根据ID查询箱子的信息，确定符合该箱子的可插入位置，返回Position的列表
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    @Override
    public List<Position> getInsertablePosition(Integer containerId) {
        //todo
        return null;
    }
}

package com.myharbour.service;

import com.myharbour.pojo.ResultantCargoAttr;

import java.util.List;

public interface CargoAttrService {

    //获取所有的货物名称，单位i

    /**
     * @return
     */
    List<ResultantCargoAttr> getCargoAttrs();

    /**
     *
     * @param cargoTypeId
     * @param cargoName
     * @param containerType
     * @param unitType
     * 新增一个货物的属性，该操作只能由管理员进行
     */
    void addACargoAttr(Integer cargoTypeId,
                       String cargoName,
                       Integer containerType,
                       String unitType);
}

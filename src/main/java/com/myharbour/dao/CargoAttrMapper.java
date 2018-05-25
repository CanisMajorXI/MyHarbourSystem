package com.myharbour.dao;

import com.myharbour.pojo.CargoAttr;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface CargoAttrMapper {

    /**
     * 获得数据库中所有的货物属性列表
     *
     * @param cargoTypeId
     * @param cargoName
     * @param containerType
     * @param unitType
     * @return
     */
    List<CargoAttr> getCargoAttrs(@Param("cargoTypeId") Integer cargoTypeId,
                                  @Param("cargoName") String cargoName,
                                  @Param("containerType") Integer containerType,
                                  @Param("unitType") String unitType
                                 );

    /**
     *
     * @param cargoTypeId
     * @param cargoName
     * @param containerType
     * @param unitType
     * @param rowBounds
     * @return
     */
    List<CargoAttr> getInvalidCargoAttrs(@Param("cargoTypeId") Integer cargoTypeId,
                                         @Param("cargoName") String cargoName,
                                         @Param("containerType") Integer containerType,
                                         @Param("unitType") String unitType,
                                         RowBounds rowBounds);

    /**
     *
     * @param id
     * @param rowBounds
     * @return
     */
    CargoAttr getCargoAttrById(@Param("id") Integer id, RowBounds rowBounds);

    /**
     *
     * @param cargoAttr
     */
    void insertCargoAttr(CargoAttr cargoAttr);

    /**
     *
     * @param cargoAttr
     */
    void updateCargoAttr(CargoAttr cargoAttr);
}

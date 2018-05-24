package com.myharbour.dao;

import com.myharbour.pojo.Cargo;
import com.myharbour.pojo.ResultantCargoInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoMapper {


    /**
     * cargo
     英/'kɑːgəʊ/  美/'kɑrɡo/  全球(美国)
     简明 朗文 柯林斯 21世纪 例句 百科
     n. 货物，船货
     n. (Cargo)人名；(英、西)卡戈
     复数 cargos或cargoes
     考研 | CET4 | GRE | GMAT | 商务英语 | TOEFL

     * @param cargoId
     * @param cargoTypeId
     * @param gross
     * @param containerId
     * @param rowBounds
     * @return
     */
    //不加valid默认查询所有valid为true(1)的列！
    List<Cargo> getCargos(@Param("cargoId") Integer cargoId,
                               @Param("userId") Integer userId,
                               @Param("cargoTypeId") Integer cargoTypeId,
                               @Param("gross") Integer gross,
                               @Param("containerId") Integer containerId,
                               RowBounds rowBounds);

    /**
     *
     * @param containerId
     * @return
     */
    List<ResultantCargoInfo> getResultantCargoInfoByContainerId(Integer containerId);
    /**
     * @param cargoId
     * @param cargoTypeId
     * @param gross
     * @param containerId
     * @param rowBounds
     * @return
     */
    List<Cargo> getInvalidCargos(@Param("cargoId") Integer cargoId,
                                 @Param("userId") Integer userId,
                                 @Param("cargoTypeId") Integer cargoTypeId,
                                 @Param("gross") Integer gross,
                                 @Param("containerId") Integer containerId,
                                 RowBounds rowBounds);

    /**
     * @param id
     * @param rowBounds
     * @return
     */
    List<Cargo> getCargoById(@Param("id") Integer id, RowBounds rowBounds);

    /**
     * @param cargo
     */
    void insertCargo(Cargo cargo);

    /**
     * @param cargo
     */
    void updateCargo(Cargo cargo);

    void updateCargoToInvalidByContainerId(Integer containerId);
}

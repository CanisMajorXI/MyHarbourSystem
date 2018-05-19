package com.myharbour.dao;

import com.myharbour.pojo.CargoAttr;
import com.myharbour.pojo.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoAttrMapper {

    List<CargoAttr> getValidGargoAttr(CargoAttr cargoAttr, RowBounds rowBounds);

    List<CargoAttr> getUnvalidGargoAttr(CargoAttr cargoAttr, RowBounds rowBounds);

    List<CargoAttr> getGargoAttrById(Integer id,RowBounds rowBounds);

    void insertCargoAttr(CargoAttr cargoAttr);

    void updateCargoAttr(CargoAttr cargoAttr);
}

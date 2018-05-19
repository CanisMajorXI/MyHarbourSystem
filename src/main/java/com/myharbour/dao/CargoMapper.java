package com.myharbour.dao;

import com.myharbour.pojo.Cargo;
import com.myharbour.pojo.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoMapper {

    List<Cargo> getValidGargos(Cargo cargo, RowBounds rowBounds);

    List<Cargo> getUnvalidGargos(Cargo cargo, RowBounds rowBounds);

    List<Cargo> getGargoById(Integer id,RowBounds rowBounds);

    void insertCargo(Cargo cargo);

    void updateCargo(Cargo cargo);
}

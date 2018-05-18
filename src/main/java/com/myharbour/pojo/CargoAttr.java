package com.myharbour.pojo;

public class CargoAttr {

    private Integer cargoTypeId;

    private String cargoName;

    private Integer type;

    private String unitType;

    private Boolean valid;

    public Integer getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(Integer cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}

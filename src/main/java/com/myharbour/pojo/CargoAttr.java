package com.myharbour.pojo;

public class CargoAttr {

    //对应箱子类型
    public static final int TYPE_ORDINARY = 0;
    public static final int TYPE_FREEZE = 1;
    public static final int TYPE_HAZARD = 2;

    //判断是否失效
    public static final boolean STATUS_VALID = true;
    public static final boolean STATUS_INVALID = false;


    private Integer cargoTypeId;

    private String cargoName;

    private Integer containerType;

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

    public Integer getContainerType() {
        return containerType;
    }

    public void setContainerType(Integer containerType) {
        this.containerType = containerType;
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

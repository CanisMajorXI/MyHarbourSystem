package com.myharbour.pojo;

public class ResultantCargoAttr {
    private Integer cargoTypeId;
    private String nameAndUnit;
    private Integer containerTypeId;

    public Integer getContainerTypeId() {
        return containerTypeId;
    }

    public void setContainerTypeId(Integer containerTypeId) {
        this.containerTypeId = containerTypeId;
    }

    public Integer getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(Integer cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    public String getNameAndUnit() {
        return nameAndUnit;
    }

    public void setNameAndUnit(String nameAndUnit) {
        this.nameAndUnit = nameAndUnit;
    }
}

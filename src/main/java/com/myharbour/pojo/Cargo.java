package com.myharbour.pojo;

public class Cargo {

    //判断是否失效
    public static final boolean STATUS_VALID = true;
    public static final boolean STATUS_INVALID = false;

    private Integer cargoId;

    private Integer userId;

    private Integer cargoTypeId;

    private Integer gross;

    private Integer containerId;

    private Boolean valid;

    public Integer getCargoId() {
        return cargoId;
    }

    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(Integer cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    public Integer getGross() {
        return gross;
    }

    public void setGross(Integer gross) {
        this.gross = gross;
    }

    public Integer getContainerId() {
        return containerId;
    }

    public void setContainerId(Integer containerId) {
        this.containerId = containerId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}


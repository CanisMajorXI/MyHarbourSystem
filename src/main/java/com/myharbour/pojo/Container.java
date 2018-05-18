package com.myharbour.pojo;

public class Container {

    //对应箱子尺寸
    public static final byte SIZE_SMALL = 0;
    public static final byte SIZE_LARGE = 1;
    //对应箱子类型
    public static final byte TYPE_ORDINARY = 0;
    public static final byte TYPE_FREEZE = 1;
    public static final byte TYPE_HAZARD = 2;

    private Integer containerId;

    private Integer containerArea;

    private Integer userId;

    private Integer row;

    private Integer column;

    private Integer layer;

    private Integer type;

    private Integer size;

    private Boolean valid;


    public Integer getContainerId() {
        return containerId;
    }

    public void setContainerId(Integer containerId) {
        this.containerId = containerId;
    }

    public Integer getContainerArea() {
        return containerArea;
    }

    public void setContainerArea(Integer containerArea) {
        this.containerArea = containerArea;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

}

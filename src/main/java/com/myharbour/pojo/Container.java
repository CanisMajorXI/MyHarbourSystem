package com.myharbour.pojo;

public class Container {

    //对应箱子尺寸
    public static final int SIZE_SMALL = 0;
    public static final int SIZE_LARGE = 1;
    //对应箱子类型
    public static final int TYPE_ORDINARY = 0;
    public static final int TYPE_FREEZE = 1;
    public static final int TYPE_HAZARD = 2;

    //对应箱子在任务区还是在仓库
    public static final int AREA_TASK = -1;
    public static final int AREA_ORDINARY = 0;
    public static final int AREA_FREEZE = 1;
    public static final int AREA_HAZARD = 2;

    //行的范围
    public static final int TOTAL_ROWS_EMPTY = 5;
    public static final int TOTAL_ROWS_ORDINARY = 10;
    public static final int TOTAL_ROWS_FREEZE = 6;
    public static final int TOTAL_ROWS_HAZARD = 6;
    //列的范围
    public static final int TOTAL_COLUMNS_EMPTY = 10;
    public static final int TOTAL_COLUMNS_ORDINARY = 20;
    public static final int TOTAL_COLUMNS_FREEZE = 10;
    public static final int TOTAL_COLUMNS_HAZARD = 10;
    //层的范围
    public static final int TOTAL_LAYERS_EMPTY = 6;
    public static final int TOTAL_LAYERS_ORDINARY = 4;
    public static final int TOTAL_LAYERS_FREEZE = 4;
    public static final int TOTAL_LAYERS_HAZARD = 2;

    //判断是否失效
    public static final boolean STATUS_VALID = true;
    public static final boolean STATUS_INVALID = false;

    private Integer containerId;

    private Integer containerArea;

    private Integer userId;

    private Integer row;

    private Integer column;

    private Integer layer;

    private Integer type;

    private Integer size;

    private Boolean valid;

    //private Boolean is

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

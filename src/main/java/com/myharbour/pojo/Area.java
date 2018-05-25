package com.myharbour.pojo;

/**
 * 区域类
 */
public class Area {

    private int[][][] arrSituation;
    private int areaRow;
    private int areaColumn;
    private int areaLayer;
    private int area;

    private Area(int areaRow, int areaColumn, int areaLayer, int area) {
        this.areaRow = areaRow;
        this.areaColumn = areaColumn;
        this.areaLayer = areaLayer;
        arrSituation = new int[areaRow][areaColumn + 1][areaLayer];
        // 最外层阻隔列全置为1
        for (int i = 0; i < areaRow; i++) {
            for (int k = 0; k < areaLayer; k++) {
                arrSituation[i][areaColumn][k] = 1;
            }
        }
        this.area = area;
    }

    public int[][][] getArrSituation() {
        return arrSituation;
    }

    public int getAreaRow() {
        return areaRow;
    }

    public void setAreaRow(int areaRow) {
        this.areaRow = areaRow;
    }

    public int getAreaColumn() {
        return areaColumn;
    }

    public void setAreaColumn(int areaColumn) {
        this.areaColumn = areaColumn;
    }

    public int getAreaLayer() {
        return areaLayer;
    }

    public void setAreaLayer(int areaLayer) {
        this.areaLayer = areaLayer;
    }

    public int getArea() {
        return area;
    }

    /*//行的范围
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
    * */
    public static Area getInstanceByArea(int area) {
        int row = 0, column = 0, layer = 0;
        switch (area) {
            case Container.AREA_EMPTY:
                row = Container.TOTAL_ROWS_EMPTY;
                column = Container.TOTAL_COLUMNS_EMPTY;
                layer = Container.TOTAL_LAYERS_EMPTY;
                break;
            case Container.AREA_ORDINARY:
                row = Container.TOTAL_ROWS_ORDINARY;
                column = Container.TOTAL_COLUMNS_ORDINARY;
                layer = Container.TOTAL_LAYERS_ORDINARY;
                break;
            case Container.AREA_FREEZE:
                row = Container.TOTAL_ROWS_FREEZE;
                column = Container.TOTAL_COLUMNS_FREEZE;
                layer = Container.TOTAL_LAYERS_FREEZE;
                break;
            case Container.AREA_HAZARD:
                row = Container.TOTAL_ROWS_HAZARD;
                column = Container.TOTAL_COLUMNS_HAZARD;
                layer = Container.TOTAL_LAYERS_HAZARD;
                break;
        }
        return new Area(row, column, layer, area);
    }
}


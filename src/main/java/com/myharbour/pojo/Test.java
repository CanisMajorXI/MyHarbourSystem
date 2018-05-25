package com.myharbour.pojo;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        int type = 0;
        int size;

        int[][][] a = new int[2][3][4];
        System.out.println(a[1][1][1]);
        switch (type) {
            case 1:
                a = new int[5][10][5];
                break;
            case 2:
                a = new int[10][20][5];
                break;
            case 3:
                a = new int[6][10][5];
                break;
            case 4:
                a = new int[6][10][5];
                break;
        }
    }

    public class Position {
        // 行
        int row;
        // 列
        int column;
        // 层
        int layer;
        // 区域 0-3
        int area;

        public Position(int row, int column, int layer, int area) {
            this.row = row;
            this.column = column;
            this.layer = layer;
            this.area = area;
        }
    }

    /**
     * 区域类，包含箱子数组和区域的行列层数
     */
    public class Area {

        int[][][] arrSituation;
        int areaRow;
        int areaColumn;
        int areaLayer;
        int area;

        public Area(int areaRow, int areaColumn, int areaLayer, int area) {
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
    }

    private List<Position> getInsertablePosition(Area area, int size) {
        List<Position> list = new ArrayList<>();
        int[][][] arr = area.getArrSituation();
        for (int i = 0; i < area.getAreaRow(); i++) {
            for (int j = 0; j < area.getAreaColumn(); j++) {
                for (int k = 0; k < area.getAreaLayer(); k++) {
                    if (arr[i][j][k] == 0){

                        if (size == 0){// 小箱子直接插入
                            list.add(new Position(i+1,j+1,k+1,area.getArea()));
                        }else{ // 对于大箱子需要判断右面是否为空
                            if (arr[i][j+1][k] == 0){
                                list.add(new Position(i+1,j+1,k+1,area.getArea()));
                            }
                        }
                    }else if (arr[i][j][k] == 1){

                    }
                }
            }
        }

        return list;
    }

    /**
     * 获得特定区域内的箱子的情况放入数组
     * <p>
     * * @param containers
     *
     * @return 区域情况的数组
     */
    private Area getAreaSituation(Area area, List<Container> containers) {
        // 将获得的container数组内的坐标加入对应区域的坐标
        for (Container container : containers
                ) {
            area.getArrSituation()[container.getRow() - 1][container.getColumn() - 1][container.getLayer() - 1] = 1;
            if (container.getSize() == 1) {
                area.getArrSituation()[container.getRow() - 1][container.getColumn()][container.getLayer() - 1] = 1;
            }
        }
        return area;
    }
}

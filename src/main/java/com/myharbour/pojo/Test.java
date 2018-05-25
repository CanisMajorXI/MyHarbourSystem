package com.myharbour.pojo;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        int type = 0;
        int size = 0;

        Area area = new Area(5, 5, 5, 1);
        int[][][] arr = area.getArrSituation();
        arr[0][0][0] = 1;
        arr[1][0][0] = 1;
        arr[0][0][1] = 1;
        arr[0][0][2] = 1;
        arr[0][0][3] = 1;
        arr[0][0][4] = 1;
        arr[0][1][0] = 1;
        arr[0][1][1] = 1;
        arr[1][2][0] = 1;
        List<MyPosition> list;
        Test test = new Test();
        list = test.getInsertableMyPosition(area, 0);
        System.out.println(list.size());
        for (MyPosition p : list
                ) {
            System.out.println(p);
        }
    }

    static class MyPosition {
        // 行
        int row;
        // 列
        int column;
        // 层
        int layer;
        // 区域 0-3
        int area;

        public MyPosition(int row, int column, int layer, int area) {
            this.row = row;
            this.column = column;
            this.layer = layer;
            this.area = area;
        }

        @Override
        public String toString() {
            return this.row + ":" + this.column + ":" + this.layer;
        }
    }

    static class Area {

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

    private List<MyPosition> getInsertableMyPosition(Area area, int size) {
        List<MyPosition> list = new ArrayList<>();
        int[][][] arr = area.getArrSituation();
        for (int i = 0; i < area.getAreaRow(); i++) {
            for (int j = 0; j < area.getAreaColumn(); j++) {
                for (int k = 0; k < area.getAreaLayer(); k++) {

                    if (arr[i][j][k] == 0 ) {
                        if (size == 0) {// 小箱子
                            if (k == 0) { // 如果是一层可以直接插入
                                list.add(new MyPosition(i + 1, j + 1, k + 1, area.getArea()));
                                continue;
                            }else if (arr[i][j][k-1] == 1){ // 如果不是一层就判断下面是否有
                                list.add(new MyPosition(i + 1, j + 1, k + 1, area.getArea()));
                                continue;
                            }else {
                                continue;
                            }
                        } else { // 对于大箱子需要判断右面是否为空
                            if (arr[i][j + 1][k] == 0 && k == 0) {
                                list.add(new MyPosition(i + 1, j + 1, k + 1, area.getArea()));
                                continue;
                            } else if (arr[i][j + 1][k] == 0 && k != 0) {
                                if (arr[i][j + 1][k - 1] == 1&&arr[i][j][k-1] == 1) {
                                    list.add(new MyPosition(i + 1, j + 1, k + 1, area.getArea()));
                                    continue;
                                }
                            }else{
                                continue;
                            }
                        }
                    } else if (arr[i][j][k] == 1) {
                        continue;
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

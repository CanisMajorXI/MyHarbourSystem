package com.myharbour.util;

import com.myharbour.pojo.Area;
import com.myharbour.pojo.Container;
import com.myharbour.pojo.Position;

import java.util.ArrayList;
import java.util.List;

public class InsertablePositionTool {

    /**
     * 获得可插入的位置的列表
     * @param area 目标区域
     * @param list 目标区域已经存在的箱子的列表
     * @param size 要插入的箱子的大小
     * @return 可插入位置的列表
     */
    public static  List<Position> getInsertablePosition(Area area, List<Container>list, int size){
       return getInsertableMyPosition(getAreaSituation(area,list),size);
    }

    /**
     * 核心算法
     * @param area
     * @param size
     * @return
     */
    private static List<Position> getInsertableMyPosition(Area area, int size) {
        List<Position> list = new ArrayList<>();
        int[][][] arr = area.getArrSituation();
        for (int i = 0; i < area.getAreaRow(); i++) {
            for (int j = 0; j < area.getAreaColumn(); j++) {
                for (int k = 0; k < area.getAreaLayer(); k++) {

                    if (arr[i][j][k] == 0 ) {
                        if (size == 0) {// 小箱子
                            if (k == 0) { // 如果是一层可以直接插入
                                list.add(new Position(i + 1, j + 1, k + 1, area.getArea()));
                                continue;
                            }else if (arr[i][j][k-1] == 1){ // 如果不是一层就判断下面是否有
                                list.add(new Position(i + 1, j + 1, k + 1, area.getArea()));
                                continue;
                            }else {
                                continue;
                            }
                        } else { // 对于大箱子需要判断右面是否为空
                            if (arr[i][j + 1][k] == 0 && k == 0) {
                                list.add(new Position(i + 1, j + 1, k + 1, area.getArea()));
                                continue;
                            } else if (arr[i][j + 1][k] == 0 && k != 0) {
                                if (arr[i][j + 1][k - 1] == 1&&arr[i][j][k-1] == 1) {
                                    list.add(new Position(i + 1, j + 1, k + 1, area.getArea()));
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
     * * @param containers
     *
     * @return 区域情况的数组
     */
    private static Area getAreaSituation(Area area, List<Container> containers) {
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

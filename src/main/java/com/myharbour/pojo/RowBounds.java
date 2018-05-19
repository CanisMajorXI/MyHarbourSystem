package com.myharbour.pojo;

import com.sun.rowset.internal.Row;

public class RowBounds {

    public static final int NO_ROW_OFFSET = 0;
    public static final int NO_ROW_LIMIT = Integer.MAX_VALUE;
    public static final RowBounds DEFAULT = new RowBounds();

    private int offset;  //偏移量
    private int limit;   //限制条数

    public RowBounds(){
        this.offset = NO_ROW_OFFSET;
        this.limit = NO_ROW_LIMIT;
    }

    public RowBounds(int offset,int limit){
        this.offset = offset;
        this.limit = limit;
    }


}

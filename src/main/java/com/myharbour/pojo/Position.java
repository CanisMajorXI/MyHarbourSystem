package com.myharbour.pojo;

public class Position {

    private Integer row;
    private Integer column;
    private Integer layer;
    private Integer area;

    public Position(int row, int column, int layer, int area) {
        this.row = row;
        this.column = column;
        this.layer = layer;
        this.area = area;
    }
public Position() {};

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

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) return false;
        Position p = (Position) obj;
        return this.area.equals(p.getArea())
                && this.row.equals(p.getRow())
                && this.column.equals(p.getColumn())
                && this.layer.equals(p.getLayer());
    }
}

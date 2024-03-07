package com.elie309.reservation.models;

public class Cell {

    private int x;
    private int y;
    private int width;
    private int height;
    private CellType cellType;

    public Cell() {
    }

    public Cell(int x, int y, int width, int height, CellType cellType) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.cellType = cellType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
}

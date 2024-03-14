package com.elie309.reservation.components;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ReservationRect extends StackPane {

    private int rowId;
    private int colId;
    private int spanRowNumber;
    private int spanColNumber;
    private int heightPercentage;

    private final Rectangle rectangle;

    public ReservationRect(double width, double height, int rowId, int colId) {
        setWidth(width);
        setHeight(height);
        setRowId(rowId);
        setColId(colId);

        this.rectangle = new Rectangle();
        this.rectangle.setHeight(height);
        this.rectangle.setWidth(width);
        this.getChildren().add(rectangle);

    }

    //#region Getters & Setters

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getColId() {
        return colId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public int getSpanRowNumber() {
        return spanRowNumber;
    }

    public void setSpanRowNumber(int spanRowNumber) {
        this.spanRowNumber = spanRowNumber;
        this.setHeight(this.getHeight()*this.getSpanRowNumber());
    }

    public int getSpanColNumber() {
        return spanColNumber;
    }

    public void setSpanColNumber(int spanColNumber) {
        this.spanColNumber = spanColNumber;
        this.setWidth(this.getWidth()*this.getSpanColNumber());
    }

    public void setHeightPercentage(int heightPercentage) {
        this.heightPercentage = heightPercentage;
        this.setHeight((this.getHeight()*heightPercentage)/100);
    }

    public int getHeightPercentage() {
        return heightPercentage;
    }



    //#endregion

    //#region Methods

    public void setFillColor(Paint paint){
        this.rectangle.setFill(paint);
    }

    public void setText(String text){
        Text textFX = new Text(text);
        textFX.setFont(Font.font(14));
        this.getChildren().add(textFX);
    }



    //#endregion


    @Override
    public String toString() {
        return super.toString();
    }
}

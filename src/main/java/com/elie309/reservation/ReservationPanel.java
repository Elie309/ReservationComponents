package com.elie309.reservation;

import com.elie309.reservation.models.CellType;
import com.elie309.reservation.models.ReservationCell;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ReservationPanel extends StackPane {

    private final GraphicsContext gc;

    private final int CELL_WIDTH = 150;
    private final int CELL_HEIGHT = 30;
    private final int LINE_WIDTH = 1;

    private static final int NUM_OF_HOURS = 24;

    private final List<ReservationCell> cells;
    private final List<String> titles;

    private final int NUM_ROWS;
    private final int NUM_COLS;

    public ReservationPanel(List<String> titles) {


        this.titles = titles;
        titles.add(0, "Time");

        this.NUM_ROWS = NUM_OF_HOURS + 1;
        this.NUM_COLS = this.titles.size();
        this.cells = new ArrayList<>();

        Canvas canvas = new Canvas(NUM_COLS * CELL_WIDTH, NUM_ROWS * CELL_HEIGHT);
        this.getChildren().add(new ScrollPane(canvas));

        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);

        setupGridPane();
    }

    private void setupGridPane() {

    //TODO: Problem with the COLUMN Size and ROW when we change one
        // Solution is to make it use the last (X + cell width) and (y+height) location
        //and add its width and height accordingly
        //2D arraylist might be usefull
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                ReservationCell cell = new ReservationCell();

                cell.setX(col * CELL_WIDTH);
                cell.setY(row * CELL_HEIGHT);

                cell.setWidth(CELL_WIDTH);
                cell.setHeight(CELL_HEIGHT);

                cell.setRowId(row);
                cell.setColumnId(col);

                cell.setCellType(row == 0 ? CellType.HEADER : CellType.BODY);


                if (row == 0) {
                    cell.setHeight(cell.getHeight()+10);
                    cell.setText(this.getTitles().get(col));
                }

                if(col == 0){
                    cell.setWidth(cell.getWidth()-50);
                }


                if(cell.getCellType() == CellType.BODY
                        && cell.getColumnId() == 0){
                    cell.setCellType(CellType.INFO);
                    cell.setText(
                            cell.getRowId() <= 12
                                    ? cell.getRowId()+":00 AM"
                                    : (cell.getRowId()-12)+":00 PM"
                    );
                }

                cells.add(cell);
            }
        }

        drawCells();


    }

    private void drawCells() {
        for (ReservationCell cell : cells) {

            if (cell.getCellType() != CellType.BODY) {
                gc.setFill(Color.GRAY);
                gc.fillRect(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
                gc.setStroke(Color.WHITE);
                gc.strokeRect(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
                drawText(gc, cell.getText(), cell.getX(), cell.getY());
            } else {
                gc.setFill(Color.WHITE);
                gc.fillRect(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
                gc.setStroke(Color.GRAY);
                gc.strokeRect(cell.getX(), cell.getY(),cell.getWidth(), cell.getHeight());
            }
        }
    }

    private void drawText(GraphicsContext gc, String text, double x, double y) {
        gc.setFill(Color.WHITE);
        gc.fillText(text, x + 5, y + (double) CELL_HEIGHT / 2 + 5);
    }


    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public List<String> getTitles() {
        return titles;
    }
}

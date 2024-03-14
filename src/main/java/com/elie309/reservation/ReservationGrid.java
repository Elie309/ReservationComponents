package com.elie309.reservation;

import com.elie309.reservation.components.ReservationForm;
import com.elie309.reservation.components.ReservationRect;
import com.elie309.reservation.models.Reservation;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationGrid extends StackPane {

    private final int NUM_OF_ROWS;
    private final int NUM_OF_COLS;

    private final int CELL_WIDTH = 150;
    private final int CELL_HEIGHT = 30;


    private final List<String> headers;
    private final AnchorPane[][] cells;


    private GridPane gridPane;

    private static ReservationRect currentCell;

    private static ReservationRect targetCell;


    public ReservationGrid(List<String> columnHeaders) {

        columnHeaders.add(0, "time");

        //Will make them all to lowercase
        this.headers = List.of(columnHeaders.stream()
                .map(String::toLowerCase)
                .toArray(String[]::new));


        int NUM_OF_HOURS = 24;
        this.NUM_OF_ROWS = (NUM_OF_HOURS*2) + 1;
        this.NUM_OF_COLS = this.headers.size();

        this.cells = new AnchorPane[NUM_OF_ROWS][NUM_OF_COLS];

        createGrid();
        eventsHandler();

        this.getChildren().add(new ScrollPane(this.gridPane));
    }

    private void eventsHandler() {


    }

    private void createGrid() {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        String colorBorder = "#9f9e9e";

        for (int i = 0; i < headers.size(); i++) {
            Label headerLabel = new Label(headers.get(i));
            headerLabel.setPadding(new Insets(10));
            headerLabel.setTextAlignment(TextAlignment.CENTER);
            headerLabel.setStyle("-fx-border-color: " + colorBorder + "; -fx-border-width: 1px;");
            headerLabel.setMaxSize(CELL_WIDTH, CELL_HEIGHT);
            gridPane.add(headerLabel, i, 0);
        }

        int time = 0;
        for (int j = 1; j < NUM_OF_ROWS; j++) {

            Label headerLabel;
            if(j % 2 != 0){
                time++;
                headerLabel = new Label(
                        time <= 12
                                ? time + ":00 AM"
                                : (time - 12) + ":00 PM"
                );
                headerLabel.setPadding(new Insets(0, 10, 0, 10));
                headerLabel.setTextAlignment(TextAlignment.CENTER);
                headerLabel.setStyle("-fx-border-color: " + colorBorder + "; -fx-border-width: 1px 1px 0px 1px;");

            }else{

                headerLabel = new Label(" ");
                headerLabel.setPadding(new Insets(0, 10, 0, 10));
                headerLabel.setTextAlignment(TextAlignment.CENTER);
                headerLabel.setStyle("-fx-border-color: " + colorBorder + "; -fx-border-width: 0px 1px 1px 1px;");

            }
            headerLabel.setMaxSize(CELL_WIDTH, CELL_HEIGHT);
            gridPane.add(headerLabel, 0, j);


        }

        // Add cells
        for (int i = 1; i < NUM_OF_ROWS; i++) {
            for (int j = 1; j < NUM_OF_COLS; j++) {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setMinSize(CELL_WIDTH, CELL_HEIGHT);
                anchorPane.setStyle("-fx-border-color: " + colorBorder + "; -fx-border-width:1px;");

                int finalJ = j;
                anchorPane.setOnMouseClicked(event -> {

                    ReservationForm form = new ReservationForm(headers.get(finalJ));
                    form.showAndWait();
                    System.out.println(form.getReservation());


                });
                gridPane.add(anchorPane, j, i);
                cells[i][j] = anchorPane;
            }
        }
    }

    public void addReservation(Reservation reservation){

        LocalDateTime startTime = roundTime(reservation.getStartTime());
        LocalDateTime endTime = roundTime(reservation.getEndTime());
        int rowId = (startTime.getHour()*2)+1;
        int spanRowNumber = endTime.minusHours(startTime.getHour()).getHour();
        int colId = headers.indexOf(reservation.getService().toLowerCase());

        ReservationRect reservationRect = new ReservationRect(
                this.CELL_WIDTH,
                this.CELL_HEIGHT,
                rowId,
                colId
        );

        reservationRect.setText(reservation.getClient().getDetails());

        reservationRect.setSpanRowNumber(spanRowNumber);
        reservationRect.setSpanColNumber(2);


        AnchorPane.setTopAnchor(reservationRect, 0.0);
        gridPane.add(reservationRect, colId, rowId,  reservationRect.getSpanColNumber(), reservationRect.getSpanRowNumber());

    }


    private LocalDateTime roundTime(LocalDateTime localDateTime){

        int minute = localDateTime.getMinute();
        int roundedMinute = 0;

        if (minute > 10 && minute < 22) {
            roundedMinute = 15;
        } else if (minute < 37) {
            roundedMinute = 30;
        } else if ( minute < 53) {
            roundedMinute = 45;
        }

        return localDateTime.withMinute(roundedMinute).withSecond(0).withNano(0);

    }





}
package com.elie309.reservation.components;

import com.elie309.reservation.models.Client;
import com.elie309.reservation.models.Reservation;
import com.elie309.reservation.models.Status;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class ReservationForm extends Stage{

    private GridPane grid;

    private final String service;

    private TextField clientField;
    private DatePicker startDatePicker;

    private Spinner<Integer> startHourSpinner;
    private Spinner<Integer> startMinuteSpinner;

    private DatePicker endDatePicker;

    private Spinner<Integer> endHourSpinner;
    private Spinner<Integer> endMinuteSpinner;

    private ComboBox<String> statusComboBox;


    private Reservation reservation;

    public ReservationForm(String service) {

        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Reservation Form");

        this.service = service;

        initialize();

        Scene scene = new Scene(grid, 750, 500);
        this.setScene(scene);
    }

    private void initialize(){
        grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Add form elements
        clientField = new TextField();
        clientField.setPromptText("Client Name");
        GridPane.setConstraints(clientField, 0, 0);

        startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");
        GridPane.setConstraints(startDatePicker, 0, 1);

        startHourSpinner = new Spinner<>(0, 23, 0);
        startHourSpinner.setEditable(true);
        GridPane.setConstraints(startHourSpinner, 0,2);

        // Create Spinner for minutes (0-59)
        startMinuteSpinner = new Spinner<>(0, 59, 0);
        startMinuteSpinner.setEditable(true);
        GridPane.setConstraints(startHourSpinner, 0,3);

        endDatePicker = new DatePicker();
        endDatePicker.setPromptText("End Date");
        GridPane.setConstraints(endDatePicker, 1, 1);

        endHourSpinner = new Spinner<>(0, 23, 0);
        endHourSpinner.setEditable(true);
        GridPane.setConstraints(endHourSpinner, 1,2);

        // Create Spinner for minutes (0-59)
        endMinuteSpinner = new Spinner<>(0, 59, 0);
        endMinuteSpinner.setEditable(true);
        GridPane.setConstraints(endMinuteSpinner, 1,3);

//        serviceField = new TextField();
//        serviceField.setPromptText("Service");
//        GridPane.setConstraints(serviceField, 2, 0);

        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll(Status.getAllStatusValues());
        statusComboBox.setPromptText("Status");
        GridPane.setConstraints(statusComboBox, 2, 1);

        Button submitButton = new Button("Submit");
        submitButton.setOnMouseClicked(event -> submit());
        GridPane.setConstraints(submitButton, 0, 3);


        grid.getChildren().addAll(clientField, startDatePicker,
                endDatePicker, statusComboBox, submitButton, startHourSpinner, startMinuteSpinner, endHourSpinner, endMinuteSpinner);

    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void submit() {

        Client client = new Client(1, clientField.getText(), "email@gmail.com", "71-xxxxxx");

        LocalDateTime startTime = startDatePicker.getValue().atTime(startHourSpinner.getValue(),
                startMinuteSpinner.getValue(), 0);

        LocalDateTime endTime = endDatePicker.getValue().atTime(endHourSpinner.getValue(),
                endMinuteSpinner.getValue(), 0);

        Status status = Status.valueOf(statusComboBox.getValue());


        if (isValidReservation(client, startTime, endTime, service, status)) {

            this.reservation = new Reservation(1, client, startTime, endTime, service, status);

            System.out.println("Reservation submitted: " + reservation);
        } else {

            System.out.println("Invalid reservation data. Please check your input.");
        }
    }

    private boolean isValidReservation(Client client, LocalDateTime startTime, LocalDateTime endTime,
                                       String service, Status status) {
        return client != null && startTime != null && endTime != null && service != null && status != null &&
                startTime.isBefore(endTime);
    }
}

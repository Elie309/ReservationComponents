package com.elie309.reservation;

import com.elie309.reservation.models.Client;
import com.elie309.reservation.models.Reservation;
import com.elie309.reservation.models.Status;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    List<Reservation> reservations;
    List<Client> clients;

    public final double WIDTH = 1300;
    public final double HEIGHT = 550;
    @Override
    public void start(Stage stage) {

        ArrayList<String> outdoorActivities = new ArrayList<>(Arrays.asList(
                "Hiking", "Camping", "Cycling", "Fishing", "Kayaking",
                "Rock climbing", "Picnicking", "Birdwatching", "Horseback riding", "Geocaching"
        ));

        ReservationPanel reservationPanel = new ReservationPanel(outdoorActivities);

        Scene scene = new Scene(reservationPanel, WIDTH, HEIGHT);

        stage.setTitle("Reservation System");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    private void addBruteData(){
       reservations = new ArrayList<>();


        // Create brute data for clients
        clients = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Client client = new Client(
                    i + 1,                          // Client ID
                    "Client " + (i + 1),            // Client name
                    "client" + (i + 1) + "@example.com",  // Email
                    "+1234567890"                   // Phone number
            );
            clients.add(client);
        }

        for (int i = 0; i < 10; i++) {
            Reservation reservation = new Reservation(
                    i,
                    clients.get(i),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(1),
                    "football",
                    Status.NOT_CONFIRMED
                    );
            reservations.add(reservation);
        }
    }
}
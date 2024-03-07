module com.elie309.reservationjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.elie309.reservation to javafx.fxml;
    exports com.elie309.reservation;
    exports com.elie309.reservation.models;
    opens com.elie309.reservation.models to javafx.fxml;
}
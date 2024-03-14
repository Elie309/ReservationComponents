module com.elie309.reservationjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.elie309.reservation to javafx.fxml;
    exports com.elie309.reservation;
    exports com.elie309.reservation.models;
    opens com.elie309.reservation.models to javafx.fxml;
    exports com.elie309.reservation.components;
    opens com.elie309.reservation.components to javafx.fxml;
}
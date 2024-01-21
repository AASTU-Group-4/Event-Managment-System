module com.event_managment_system {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.event_managment_system to javafx.fxml;
    opens com.event_managment_system.Controller to javafx.fxml;
    exports com.event_managment_system;
    exports com.event_managment_system.Controller;
}

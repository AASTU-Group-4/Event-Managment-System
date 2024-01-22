module com.event_managment_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.event_managment_system to javafx.fxml;
    opens com.event_managment_system.Controller to javafx.fxml;
    opens com.event_managment_system.entities to com.fasterxml.jackson.databind; // Export the entities package
    exports com.event_managment_system;
    exports com.event_managment_system.Controller;
    exports com.event_managment_system.entities; // Export the entities package
}

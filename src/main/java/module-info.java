module com.event_managment_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    // Add the following lines to include the necessary modules
    requires java.desktop; // Required for java.awt.image.BufferedImage
    requires javafx.swing; // Required for javafx.embed.swing.SwingFXUtils

    opens com.event_managment_system to javafx.fxml, com.fasterxml.jackson.databind, javafx.graphics;
    opens com.event_managment_system.Controller to javafx.fxml, com.fasterxml.jackson.databind, javafx.graphics;
    opens com.event_managment_system.entities to com.fasterxml.jackson.databind, javafx.graphics;

    exports com.event_managment_system;
    exports com.event_managment_system.Controller;
    exports com.event_managment_system.entities;
}

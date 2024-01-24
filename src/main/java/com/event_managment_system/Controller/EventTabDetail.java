package com.event_managment_system.Controller;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.text.SimpleDateFormat;

import com.event_managment_system.App;
import com.event_managment_system.entities.Event;

public class EventTabDetail extends AnchorPane {

    private ImageView eventLogo;
    private Label titleLabel;
    private Label dateLabel;
    private Label timeLabel;
    private Label venueLabel;
    private Label organizerLabel;
    private Label registrationLabel;
    private Label updatedLabel; // Corrected the ID here
    private Event event;

    public EventTabDetail(Event eve) {
        this.event=eve;
        initializeUI();
        setEventDetails();
        getStylesheets().add(getClass().getResource(App.StorgePath + "css/eventTabDetail.css").toExternalForm());
    }
    public Event getEvent(){
        return this.event;
    }
    private void initializeUI() {
        eventLogo = new ImageView(new Image(getClass().getResource(App.StorgePath + "image/no-image.png").toExternalForm()));
        eventLogo.setFitWidth(164);
        eventLogo.setFitHeight(108);
        eventLogo.setId("eventLogo"); // Set the ID
    
        titleLabel = createStyledLabel();
        titleLabel.setId("titleLabel");
        titleLabel.setPrefWidth(620);
        titleLabel.setAlignment(Pos.CENTER);
    
        dateLabel = createStyledLabel();
        dateLabel.setId("dateLabel"); // Set the ID
    
        timeLabel = createStyledLabel();
        timeLabel.setId("timeLabel"); // Set the ID
    
        venueLabel = createStyledLabel();
        venueLabel.setId("venueLabel"); // Set the ID
    
        organizerLabel = createStyledLabel();
        organizerLabel.setId("organizerLabel"); // Set the ID
    
        registrationLabel = createStyledLabel();
        registrationLabel.setId("registrationLabel"); // Set the ID
    
        updatedLabel = createStyledLabel();
        updatedLabel.setId("updatedLabel"); // Corrected the ID here
    
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5.0);
        gridPane.setVgap(5.0);
        // Configure the grid pane with column and row constraints
    
        gridPane.add(titleLabel, 0, 0, 6, 1);
        gridPane.add(createStyledLabel("Date:"), 0, 1);
        gridPane.add(dateLabel, 1, 1);
        gridPane.add(createStyledLabel("Time:"), 2, 1);
        gridPane.add(timeLabel, 3, 1);
        gridPane.add(createStyledLabel("Venue:"), 4, 1);
        gridPane.add(venueLabel, 5, 1);
        gridPane.add(createStyledLabel("Organizer:"), 0, 2);
        gridPane.add(organizerLabel, 1, 2);
        gridPane.add(createStyledLabel("Registration:"), 2, 2);
        gridPane.add(registrationLabel, 3, 2);
        gridPane.add(createStyledLabel("Updated:"), 4, 2);
        gridPane.add(updatedLabel, 5, 2);
        
        HBox hBox = new HBox(eventLogo, gridPane);
        hBox.setId("eventContainer");

        AnchorPane.setTopAnchor(gridPane, 0.0);
        AnchorPane.setRightAnchor(gridPane, 0.0);
        AnchorPane.setBottomAnchor(gridPane, 0.0);
        AnchorPane.setLeftAnchor(gridPane, 0.0);
        this.setId("mainPane");
    
        getChildren().add(hBox);
    }
    
    private Label createStyledLabel() {
        Label label = new Label();
        label.setFont(new Font("System", 12.0));
        label.setMinWidth(90);
        return label;
    }

    private Label createStyledLabel(String value){
        Label label = new Label(value);
        label.setFont(Font.font("System", FontWeight.BOLD, 15.0));
        label.setTextFill(Color.BLUE);
        return label;
    }
    private void setEventDetails() {
        titleLabel.setText(event.getTitle());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        dateLabel.setText(dateFormat.format(event.getDateTime()));
        timeLabel.setText(timeFormat.format(event.getDateTime()));
        updatedLabel.setText(timeFormat.format(event.getLastUpdateTimestamp()) + "/" + dateFormat.format(event.getLastUpdateTimestamp()));
        venueLabel.setText(event.getVenue());
        organizerLabel.setText(App.OrganizerFile.getItemById(event.getOrganizerId()).getName());
        if((this.event.getDateTime()!=null && this.event.hasEventPassed()) || (this.event.getRegistrationDeadline()!=null && this.event.hasRegistrationPassed())){
            registrationLabel.setText("Close");
            registrationLabel.setTextFill(Color.RED);
            registrationLabel.setFont(Font.font("System", FontWeight.BOLD, 16.0));
        }
        else{
            registrationLabel.setText("Open");
            registrationLabel.setTextFill(Color.GREEN);
            registrationLabel.setFont(Font.font("System", FontWeight.BOLD, 16.0));
        }
    }
}

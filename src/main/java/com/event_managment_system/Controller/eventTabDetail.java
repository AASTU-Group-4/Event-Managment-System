package com.event_managment_system.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import com.event_managment_system.App;
import com.event_managment_system.entities.Event;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class eventTabDetail{

    @FXML
    private ImageView eventLogo;

    @FXML
    private Label titleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label venueLabel;

    @FXML
    private Label organizerLabel;

    @FXML
    private Label registrationLabel;

    @FXML
    private Label updatedlable;

    private Parent theParent;

    public void initialize(Event eve, Parent parent) {
        /* Image img= new Image(eve.getEventImageUrl().get(0));
        this.eventLogo.setImage(img); */
        this.titleLabel.setText(eve.getTitle());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        this.dateLabel.setText(dateFormat.format(eve.getDateTime()));
        this.timeLabel.setText(timeFormat.format(eve.getDateTime()));
        this.updatedlable.setText(timeFormat.format(eve.getLastUpdateTimestamp()) +"/"+ dateFormat.format(eve.getLastUpdateTimestamp()));
        this.venueLabel.setText(eve.getVenue());
        this.organizerLabel.setText(App.OrganizerFile.getItemById(eve.getOrganizerId()).getName());
        this.registrationLabel.setText("Open");
        this.theParent=parent;
    }   
    public void displayFullDetali(){
        if (theParent instanceof VBox) {
            VBox parent=(VBox) theParent;
            parent.getChildren().clear();
        }
    }
}

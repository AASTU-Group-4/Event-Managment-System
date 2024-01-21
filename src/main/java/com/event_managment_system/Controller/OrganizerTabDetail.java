package com.event_managment_system.Controller;

import java.text.SimpleDateFormat;

import com.event_managment_system.entities.Organizer;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class OrganizerTabDetail {
    @FXML
    private ImageView eventLogo;

    @FXML
    private Label titleLabel;

    @FXML
    private Label AddressLable;

    @FXML
    private Label PhoneNumberLable;

    @FXML
    private Hyperlink socialMediaLink;

    @FXML
    private Label creationTimeLable;

    private Parent theParent;

    public void initialize(Organizer organizer, Parent parent) {
        /* Image img= new Image(organizer.getEventImageUrl().get(0));
        this.eventLogo.setImage(img); */
        this.titleLabel.setText(organizer.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        this.AddressLable.setText(organizer.getAddress());
        this.PhoneNumberLable.setText(organizer.getPhoneNumber());
        this.PhoneNumberLable.setText(organizer.getSocialMediaLinks());
        this.creationTimeLable.setText(timeFormat.format(organizer.getCreationTimestamp()) +"/"+ dateFormat.format(organizer.getCreationTimestamp()));
        this.theParent=parent;
    }   
    public void displayFullDetali(){
        if (theParent instanceof VBox) {
            VBox parent=(VBox) theParent;
            parent.getChildren().clear();
        }
    }
}

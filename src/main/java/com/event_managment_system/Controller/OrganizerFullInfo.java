package com.event_managment_system.Controller;

import com.event_managment_system.App;
import com.event_managment_system.entities.Organizer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class OrganizerFullInfo extends AnchorPane {
    @FXML
    private ImageView mainLogo;

    private Organizer organizer;

    public OrganizerFullInfo(Organizer organizer){
        this.organizer=organizer;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("OrganizerFullInfo.fxml"));
        fxmlLoader.setController(this);
        try {
            this.getChildren().add(fxmlLoader.load()); 
            this.mainLogo.setImage(App.loadImage(this.organizer.getMainLogo()));
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    
}

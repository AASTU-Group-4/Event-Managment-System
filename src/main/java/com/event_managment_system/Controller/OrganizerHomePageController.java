package com.event_managment_system.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import com.event_managment_system.App;
import com.event_managment_system.entities.Event;
import com.event_managment_system.entities.Organizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.fxml.Initializable;
import java.util.ArrayDeque;
import java.util.Deque;


public class OrganizerHomePageController implements Initializable{

    @FXML
    private Pane mainPane;

    @FXML
    private VBox mainVBox;

    @FXML
    private AnchorPane headerPane;

    @FXML
    private ImageView logoImage;

    @FXML
    private HBox userNameHBox;

    @FXML
    private Label userNameLabel;

    @FXML
    private ImageView profilePic;

    @FXML
    private TabPane tabPane;

    // Event Tab
    @FXML
    private Tab PreviousEvent;

    @FXML
    private AnchorPane previousEventAnchorPane;

    @FXML
    private VBox PreviousEventVBox;

    @FXML
    private ScrollPane PreviousEventScrollPane;

    @FXML
    private VBox PreviousEventContentVBox;

    // Organizer Tab
    @FXML
    private Tab UpcomingEventTab;

    @FXML
    private AnchorPane UpcomingEventAnchorPane;

    @FXML
    private VBox UpcomingEventVBox;

    @FXML
    private ScrollPane UpcomingEventScrollPane1;

    @FXML
    private VBox UpcomingEventContentVBox1;

    // Search Tab
    @FXML
    private Tab NewEventTab;

    @FXML
    private AnchorPane NewEventAnchorPane;

    @FXML
    private VBox NewEventVBox;

    // Account Tab
    @FXML
    private Tab Account;

    @FXML
    private AnchorPane accountAnchorPane;

    @FXML
    private VBox accountVBox;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        App.stage.setWidth(850);
        App.stage.setHeight(750);
        this.userNameLabel.setText(App.org.getName());
        this.profilePic.setImage(App.loadImage(App.org.getMainLogo()));
        Circle clip = new Circle(this.profilePic.getFitWidth()/2, this.profilePic.getFitHeight()/2, 50);
        this.profilePic.setClip(clip);
    }
    public void browseHistroy(){
        PreviousEventContentVBox.getChildren().clear();
        for(UUID id: App.org.getEventsOrganized()){
            Event event=App.eventFile.getItemById(id);
            if(event.hasEventPassed()){
                EventTabDetail newTab=new EventTabDetail(event);
                newTab.setOnMouseClicked(e->PreviousEventFullDisplay(event));
                this.PreviousEventContentVBox.getChildren().add(newTab);
            }
        }
    }
    
    public void PreviousEventFullDisplay(Event event){
        tabPane.getSelectionModel().select(tabPane.getTabs().get(0));
        this.PreviousEventContentVBox.getChildren().clear();
        this.PreviousEventContentVBox.getChildren().add(new EventFullInfo(event));
    }
    public void browseUpcoming(){
        UpcomingEventContentVBox1.getChildren().clear();
        for(UUID id: App.org.getEventsOrganized()){
            Event event=App.eventFile.getItemById(id);
            if(!event.hasEventPassed()){
                EventTabDetail newTab=new EventTabDetail(event);
                this.UpcomingEventContentVBox1.getChildren().add(newTab);
            }
        }
    }
    
}
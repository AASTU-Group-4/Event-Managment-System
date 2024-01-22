package com.event_managment_system.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.event_managment_system.App;
import com.event_managment_system.entities.Event;
import com.event_managment_system.entities.Organizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
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
import javafx.fxml.Initializable;

public class AttendeeHomePageController implements Initializable{

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
    private Tab BrowseEvent;

    @FXML
    private AnchorPane eventAnchorPane;

    @FXML
    private VBox eventVBox;

    @FXML
    private ChoiceBox<String> filterChoiceBox;

    @FXML
    private ScrollPane eventScrollPane;

    @FXML
    private VBox eventContentVBox;

    // Organizer Tab
    @FXML
    private Tab BrowseOrganizer;

    @FXML
    private AnchorPane organizerAnchorPane;

    @FXML
    private VBox organizerVBox;

    @FXML
    private ChoiceBox<String> filterChoiceBox2;

    @FXML
    private ScrollPane organizerScrollPane1;

    @FXML
    private VBox organizerContentVBox1;

    // Search Tab
    @FXML
    private Tab Search;

    @FXML
    private AnchorPane searchAnchorPane;

    @FXML
    private VBox searchVBox;

    @FXML
    private ChoiceBox<String> filterChoiceBox3;

    @FXML
    private HBox searchHBox;

    @FXML
    private TextField SearchKeyInput;

    @FXML
    private Button SearchButton;

    @FXML
    private ScrollPane SearchScrollPane11;

    @FXML
    private VBox SearchContentVBox11;

    // Account Tab
    @FXML
    private Tab Account;

    @FXML
    private AnchorPane accountAnchorPane;

    @FXML
    private VBox accountVBox;
    private static final double SCROLL_THRESHOLD = 0.75;
    private boolean loading = false;


    private String[] browseChoise={"Upcoming", "Previous", "Regesterd", "History"};
    private String[] orgbrowseChoise={"A-Z", "Z-A"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        App.stage.setWidth(850);
        App.stage.setHeight(750);
        this.userNameLabel.setText(App.user.getFullName());
    }

    public void browseEvent() {
        this.filterChoiceBox.getItems().addAll(browseChoise);
        this.filterChoiceBox.setValue(browseChoise[0]);
        this.eventContentVBox.getChildren().clear();
    }

    public void browseOrganizer() {

        this.filterChoiceBox2.getItems().addAll(orgbrowseChoise);
        this.filterChoiceBox2.setValue(orgbrowseChoise[0]);
        this.organizerContentVBox1.getChildren().clear();
    }
    
    public void addToeventContentVBox(Event event){
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("eventTabDetail.fxml"));
        try {
            Parent ap = fxml.load();
            eventTabDetail etd = fxml.getController();
            etd.initialize(event, this.eventContentVBox);
            this.eventContentVBox.getChildren().add(ap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public void addToOrganizerContentVBox(Organizer organizer){
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("OrganizerTabDetail.fxml"));
        try {
            Parent ap = fxml.load();
            OrganizerTabDetail etd = fxml.getController();
            etd.initialize(organizer, this.organizerContentVBox1);
            this.organizerContentVBox1.getChildren().add(ap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    public void handleFilterChange(ActionEvent event) {
        String selectedFilter = filterChoiceBox.getValue();
    }

    public void handleScroll(ScrollEvent event) {
        double vValue = eventScrollPane.getVvalue();
        if (vValue > SCROLL_THRESHOLD && !loading) {
            eventScrollPane.setVvalue(0.7);
        }
    }
}
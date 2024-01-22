package com.event_managment_system.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

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
import java.util.ArrayDeque;
import java.util.Deque;

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

    private Deque<Event> EventStack = new ArrayDeque<>();
    private Deque<Organizer> OrganizerStack = new ArrayDeque<>();
    private String[] browseChoise={"Upcoming", "Previous", "Regesterd", "History"};
    private String[] orgbrowseChoise={"A-Z", "Z-A"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        App.stage.setWidth(850);
        App.stage.setHeight(750);
        this.userNameLabel.setText(App.user.getFullName());
        try {
            // Load a local image for testing
            Image originalImage = new Image("https://picsum.photos/10/10?random=728");
            this.profilePic.setImage(originalImage);
            this.profilePic.setFitWidth(150);
            this.profilePic.setFitHeight(100);
            this.filterChoiceBox2.getItems().clear();
            this.filterChoiceBox2.getItems().addAll(orgbrowseChoise);
            this.filterChoiceBox2.setValue(orgbrowseChoise[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void browseEvent() {
        this.filterChoiceBox.getItems().clear();
        this.filterChoiceBox.getItems().addAll(browseChoise);
        this.filterChoiceBox.setValue(browseChoise[0]);
    }

    public void browseOrganizer() {
        this.filterChoiceBox2.getItems().clear();
        this.filterChoiceBox2.getItems().addAll(orgbrowseChoise);
        this.filterChoiceBox2.setValue(orgbrowseChoise[0]);
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
        if (selectedFilter.equals(browseChoise[0])) {
            this.eventContentVBox.getChildren().clear();
            EventStack.clear();
            for (Event eve : App.eventFile.getAllItems()) {
                if (!eve.hasEventPassed()) {
                    EventStack.push(eve);
                }
            }
            for (int i = 0; i < 5; i++) {
                if (!EventStack.isEmpty()) {
                    addToeventContentVBox(EventStack.pop());
                } else {
                    break;
                }
            }
        }
        else if(selectedFilter.equals(browseChoise[1])){
            this.eventContentVBox.getChildren().clear();
            EventStack.clear();
            for (Event eve : App.eventFile.getAllItems()) {
                if (eve.hasEventPassed()) {
                    EventStack.push(eve);
                }
            }
            for (int i = 0; i < 5; i++) {
                if (!EventStack.isEmpty()) {
                    addToeventContentVBox(EventStack.pop());
                } else {
                    break;
                }
            }
        }
        else if(selectedFilter.equals(browseChoise[2])){
            this.eventContentVBox.getChildren().clear();
            EventStack.clear();
            for(UUID Eventid: App.user.getEventsAttended()){
                if (!App.eventFile.getItemById(Eventid).hasEventPassed()){
                    EventStack.push(App.eventFile.getItemById(Eventid));
                }
            }
            for (int i = 0; i < 5; i++) {
                if (!EventStack.isEmpty()) {
                    addToeventContentVBox(EventStack.pop());
                } else {
                    break;
                }
            }
        }
        else if (selectedFilter.equals(browseChoise[3])) {
            this.eventContentVBox.getChildren().clear();
            EventStack.clear();
            for(UUID Eventid: App.user.getEventsAttended()){
                if (App.eventFile.getItemById(Eventid).hasEventPassed()){
                    EventStack.push(App.eventFile.getItemById(Eventid));
                }
            }
            for (int i = 0; i < 5; i++) {
                if (!EventStack.isEmpty()) {
                    addToeventContentVBox(EventStack.pop());
                } else {
                    break;
                }
            }
        }
    }
    public void handleFilterChange2(ActionEvent event) {
        String selectedFilter = filterChoiceBox2.getValue();
        if (selectedFilter.equals(orgbrowseChoise[0])) {
            this.organizerContentVBox1.getChildren().clear();
            OrganizerStack.clear();
            List<Organizer> organizers = new ArrayList<>(App.OrganizerFile.getAllItems());
            Collections.sort(organizers, Comparator.comparing(Organizer::getName));

            OrganizerStack = new ArrayDeque<>(organizers);

            this.organizerContentVBox1.getChildren().clear();
            OrganizerStack.clear();

            for (int i = 0; i < 5; i++) {
                if (!OrganizerStack.isEmpty()) {
                    addToOrganizerContentVBox(OrganizerStack.pop());
                } else {
                    break;
                }
            }
        }
        else if(selectedFilter.equals(orgbrowseChoise[1])){
            this.organizerContentVBox1.getChildren().clear();
            OrganizerStack.clear();
            List<Organizer> organizers = new ArrayList<>(App.OrganizerFile.getAllItems());
            Collections.sort(organizers, Comparator.comparing(Organizer::getName).reversed());

            OrganizerStack = new ArrayDeque<>(organizers);

            this.organizerContentVBox1.getChildren().clear();
            OrganizerStack.clear();

            for (int i = 0; i < 5; i++) {
                if (!OrganizerStack.isEmpty()) {
                    addToOrganizerContentVBox(OrganizerStack.pop());
                } else {
                    break;
                }
            }
        }
    }


    public void handleScroll(ScrollEvent event) {
        double vValue = eventScrollPane.getVvalue();
        if (vValue > SCROLL_THRESHOLD && !loading && !EventStack.isEmpty()) {
            loading=true;
            addToeventContentVBox(EventStack.pop());
            loading=false;
        }
    }
    public void handleScroll2(ScrollEvent event) {
        double vValue = organizerScrollPane1.getVvalue();
        if (vValue > SCROLL_THRESHOLD && !loading && !OrganizerStack.isEmpty()) {
            loading=true;
            addToOrganizerContentVBox(OrganizerStack.pop());
            loading=false;
        }
    }
}
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

    @FXML
    private Button registerButton;

    @FXML
    private Label registerLable;

    private static final double SCROLL_THRESHOLD = 0.75;
    private boolean loading = false;

    private Deque<Event> EventStack = new ArrayDeque<>();
    private Deque<Organizer> OrganizerStack = new ArrayDeque<>();

    private String[] browseChoise={"Upcoming", "Previous", "Regesterd", "History"};
    private String[] orgbrowseChoise={"A-Z", "Z-A"};
    private String[] searchChoise={"Organizer", "Event"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        App.stage.setWidth(850);
        App.stage.setHeight(750);
        this.userNameLabel.setText(App.user.getFullName());

        this.filterChoiceBox2.getItems().addAll(orgbrowseChoise);
        this.filterChoiceBox2.setValue(orgbrowseChoise[0]);

        this.filterChoiceBox.getItems().addAll(browseChoise);
        this.filterChoiceBox.setValue(browseChoise[0]);

        this.filterChoiceBox3.getItems().addAll(this.searchChoise);
        this.filterChoiceBox3.setValue(searchChoise[0]);

        this.profilePic.setImage(App.loadImage(App.user.imageStream()));
        Circle clip = new Circle(this.profilePic.getFitWidth()/2, this.profilePic.getFitHeight()/2, 50);
        this.profilePic.setClip(clip);
    }
    
    public void addToeventContentVBox(Event event){
        EventTabDetail newTab=new EventTabDetail(event);
        newTab.setOnMouseClicked(e->fullDisplay(event));
        this.eventContentVBox.getChildren().add(newTab);
    }
    public void fullDisplayOrganizer(Organizer organizer){
        loading=true;
        organizerScrollPane1.setVvalue(0);
        tabPane.getSelectionModel().select(tabPane.getTabs().get(1));
        this.organizerContentVBox1.getChildren().clear();
        this.organizerContentVBox1.getChildren().add(new OrganizerFullInfo(organizer));
    }

    public void fullDisplay(Event event){
        loading=true;
        eventScrollPane.setVvalue(0);
        tabPane.getSelectionModel().select(tabPane.getTabs().get(0));
        this.eventContentVBox.getChildren().clear();
        if(event.canRegister(App.user)){
            this.registerButton.setVisible(true);
            this.registerButton.setText("Register");
            this.registerButton.setOnAction(e->Register(event));
        }
        if(event.isregisted(App.user) && !event.hasEventPassed()){
            this.registerButton.setVisible(true);
            this.registerButton.setText("Unregister");
            this.registerButton.setOnAction(e->unRegister(event));
        }
        this.eventContentVBox.getChildren().add(new EventFullInfo(event));
    }
    public void unRegister(Event event){
        event.removeAttendee(App.user);
        App.user.RemoveEventRegstord(event);
        App.eventFile.saveToFile();
        App.attendeesFile.saveToFile();
        this.registerButton.setVisible(false);
        this.registerLable.setVisible(true);
    }
    public void Register(Event event){
        event.addAttendee(App.user);
        App.user.addEventAttended(event);
        App.eventFile.saveToFile();
        App.attendeesFile.saveToFile();
        this.registerButton.setVisible(false);
        this.registerLable.setVisible(true);
    }

    public void addToOrganizerContentVBox(Organizer organizer){
        OrganizerTabDetail newTab=new OrganizerTabDetail(organizer);
        newTab.setOnMouseClicked(e->fullDisplayOrganizer(organizer));
        this.organizerContentVBox1.getChildren().add(newTab);
    }
    public void addToeventSearchContentVBox(Event event){
        EventTabDetail newTab=new EventTabDetail(event);
        newTab.setOnMouseClicked(e->fullDisplay(event));
        this.SearchContentVBox11.getChildren().add(newTab);
    }

    public void addToSearchOrganizerContentVBox(Organizer organizer){
        OrganizerTabDetail newTab=new OrganizerTabDetail(organizer);
        this.SearchContentVBox11.getChildren().add(newTab);
    }  

    public void handleFilterChange(ActionEvent event) {
        loading=false;
        String selectedFilter = filterChoiceBox.getValue();
        this.registerButton.setVisible(false);
        this.registerLable.setVisible(false);
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
        loading=false;
        String selectedFilter = filterChoiceBox2.getValue();
        if (selectedFilter.equals(orgbrowseChoise[0])) {
            this.organizerContentVBox1.getChildren().clear();
            OrganizerStack.clear();

            List<Organizer> organizers = new ArrayList<>(App.OrganizerFile.getAllItems());
            Collections.sort(organizers, Comparator.comparing(Organizer::getName));

            OrganizerStack = new ArrayDeque<>(organizers);

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
    public void filterChanged(ActionEvent e){
        SearchContentVBox11.getChildren().clear();
    }
    public void Search() {
        String filter = this.filterChoiceBox3.getValue();
        String searchKey = this.SearchKeyInput.getText();
        
        if (searchKey == null || searchKey.isEmpty()) {
            return;
        }
    
        if (filter.equals(searchChoise[0])) {
            this.SearchContentVBox11.getChildren().clear();
            for (Organizer organizer : App.OrganizerFile.getAllItems()) {
                if (organizer.getName().length() >= searchKey.length() && organizer.getName().substring(0, searchKey.length()).toLowerCase().equals(searchKey.toLowerCase())) {
                    addToSearchOrganizerContentVBox(organizer);
                }
            }
        } else {
            this.SearchContentVBox11.getChildren().clear();
            for (Event event : App.eventFile.getAllItems()) {
                // Check if the length of the title is greater than or equal to the length of the search key
                if (event.getTitle().length() >= searchKey.length() &&
                    event.getTitle().substring(0, searchKey.length()).toLowerCase().equals(searchKey.toLowerCase())) {
                    addToeventSearchContentVBox(event);
                }
            }
        }
    }
    
}
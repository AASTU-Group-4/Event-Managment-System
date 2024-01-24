package com.event_managment_system.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.event_managment_system.App;
import com.event_managment_system.entities.Activity;
import com.event_managment_system.entities.Agenda;
import com.event_managment_system.entities.Event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EventFullInfo extends AnchorPane {
    @FXML
    private HBox MainContentHbox;

    @FXML
    private VBox mainLeftContent;

    @FXML
    private ImageView LogoImage;

    @FXML
    private ScrollPane ImageListScrollPane;

    @FXML
    private VBox ImageListVbox;

    @FXML
    private VBox mainRightContent;

    @FXML
    private Label eventTitleLable;

    @FXML
    private Label DescriptionLable;

    @FXML
    private Label eventDescriptionLable;

    @FXML
    private Label tagLable;

    @FXML
    private ScrollPane eventTagListScrollPane;

    @FXML
    private HBox eventTagListHbox;

    @FXML
    private GridPane eventInfoGride;

    @FXML
    private Label DateTimeLable;

    @FXML
    private Label eventDateTimeLable;

    @FXML
    private Label VenueLable;

    @FXML
    private Label eventVenueLable;

    @FXML
    private Label RegistrationEndLable;

    @FXML
    private Label eventRegistrationEndLable;

    @FXML
    private Label lastUpdateLable;

    @FXML
    private Label eventlastUpdateLable;

    @FXML
    private Label eventCapacityLable11;

    @FXML
    private Label CapacityLable11;

    @FXML
    private Label eventAvailableLable;

    @FXML
    private Label AgendaLable;

    @FXML
    private ScrollPane eventAgendaScrollpane;

    @FXML
    private GridPane eventAgendaGridpane;

    @FXML
    private ColumnConstraints eventActivityColumn;

    @FXML
    private ColumnConstraints eventStartTimeColumn;

    @FXML
    private ColumnConstraints eventEndTimeColumn;

    @FXML
    private RowConstraints eventTitleRow;

    private Event event;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public EventFullInfo(Event event){
        this.event=event;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EventFullInfo.fxml"));
        fxmlLoader.setController(this);
        try {
            this.getChildren().add(fxmlLoader.load()); 
            this.LogoImage.setImage(!this.event.getEventImageUrl().isEmpty() ? new Image(this.event.getEventImageUrl().get(0)) : null);
            addImageToVbox();
            this.eventTitleLable.setText(this.event.getTitle());
            this.eventDescriptionLable.setText(this.event.getDescription());
            addTage();
            this.eventDateTimeLable.setText(
                this.event.getDateTime() != null ?
                dateFormat.format(this.event.getDateTime()) + " / " + timeFormat.format(this.event.getDateTime()) :
                "Date not available"
            );
            this.eventRegistrationEndLable.setText(
                this.event.getRegistrationDeadline() != null ?
                dateFormat.format(this.event.getRegistrationDeadline()) + " / " + timeFormat.format(this.event.getRegistrationDeadline()) :
                "Date not available"
            );
            this.eventVenueLable.setText(this.event.getVenue());
            this.eventlastUpdateLable.setText(
                this.event.getLastUpdateTimestamp() != null ?
                dateFormat.format(this.event.getLastUpdateTimestamp()) + " / " + timeFormat.format(this.event.getLastUpdateTimestamp()) :
                "Date not available"
            );
            this.eventCapacityLable11.setText(Integer.toString(this.event.getCapacity()));
            setAgendaData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addImageToVbox(){
        for(String str: this.event.getEventImageUrl()){
            ImageView viewImage=new ImageView(new Image(str));
            viewImage.setFitHeight(307);
            viewImage.setFitWidth(290);
            this.ImageListVbox.getChildren().add(viewImage);
        }
    }
    private void addTage(){
        for(String str: this.event.getTags()){
            Label label=new Label("#"+str);
            label.setPrefWidth(100);
            label.setTextFill(Color.BLUE);
            label.setFont(Font.font("System", FontWeight.BOLD, 14.0));
            this.eventTagListHbox.getChildren().add(label);
        }
    }
    public void setAgendaData() {
        Agenda agenda = this.event.getAgenda();
        if (agenda!=null){
            List<Activity>activities=this.event.getAgenda().getActivities();
            for (int i = 0; activities != null && i < activities.size(); i++) {
                    Activity activity = activities.get(i);
            
                    Label titleLabel = new Label(activity.getTitle());
                    Label startTimeLabel = new Label(activity.getStartTime());
                    Label endTimeLabel = new Label(activity.getEndTime());
            
                    eventAgendaGridpane.add(titleLabel, 0, i + 1);
                    eventAgendaGridpane.add(startTimeLabel, 1, i + 1);
                    eventAgendaGridpane.add(endTimeLabel, 2, i + 1);
                }
            }
    }
}

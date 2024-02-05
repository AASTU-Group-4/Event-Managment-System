package com.event_managment_system.Controller;

import java.text.SimpleDateFormat;

import com.event_managment_system.App;
import com.event_managment_system.entities.Event;
import com.event_managment_system.entities.Organizer;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class OrganizerTabDetail extends AnchorPane {
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

    private Organizer organizer;

    public OrganizerTabDetail(Organizer organizer) {
        this.organizer=organizer;
        initializeUI();
        setOrganizerDetails();
        getStylesheets().add(getClass().getResource(App.StorgePath + "css/eventTabDetail.css").toExternalForm());
    }
    public Organizer getOrganizer(){
        return this.organizer;
    }
    private void initializeUI() {
        eventLogo = new ImageView(App.loadImage(organizer.allImageStream().get(0)));
        eventLogo.setFitWidth(164);
        eventLogo.setFitHeight(108);
        eventLogo.setId("eventLogo"); // Set the ID
    
        titleLabel = createStyledLabel();
        titleLabel.setId("titleLabel");
        titleLabel.setPrefWidth(620);
        titleLabel.setAlignment(Pos.CENTER);
    
        AddressLable = createStyledLabel();
        AddressLable.setId("dateLabel"); // Set the ID
    
        PhoneNumberLable = createStyledLabel();
        PhoneNumberLable.setId("timeLabel"); // Set the ID
    
        socialMediaLink = new Hyperlink();
    
        creationTimeLable = createStyledLabel();
        creationTimeLable.setId("organizerLabel"); // Set the ID
    
    
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5.0);
        gridPane.setVgap(5.0);
        // Configure the grid pane with column and row constraints
    
        gridPane.add(titleLabel, 0, 0, 4, 1);
        gridPane.add(createStyledLabel("Addres:"), 0, 1);
        gridPane.add(this.AddressLable, 1, 1);
        gridPane.add(createStyledLabel("Phone Number:"), 2, 1);
        gridPane.add(this.PhoneNumberLable, 3, 1);
        gridPane.add(createStyledLabel("Social Media:"), 0, 2);
        gridPane.add(this.socialMediaLink, 1, 2);
        gridPane.add(createStyledLabel("Creation Time:"), 2, 2);
        gridPane.add(this.creationTimeLable, 3, 2);
        
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
    public void setOrganizerDetails() {
        /* Image img = new Image(organizer.getEventImageUrl().get(0));
        this.eventLogo.setImage(img); */
        this.titleLabel.setText(organizer.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        this.AddressLable.setText(organizer.getAddress());
        this.PhoneNumberLable.setText(organizer.getPhoneNumber());
        this.socialMediaLink.setText(organizer.getSocialMediaLinks());
        this.creationTimeLable.setText(timeFormat.format(organizer.getCreationTimestamp()) + "/" + dateFormat.format(organizer.getCreationTimestamp()));
    }  
}

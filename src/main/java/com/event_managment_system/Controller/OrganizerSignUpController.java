package com.event_managment_system.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.event_managment_system.App;
import com.event_managment_system.entities.Organizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class OrganizerSignUpController  implements Initializable  {

    @FXML
    private TextField nameField;

    @FXML
    private Pane body;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField logoField;

    @FXML
    private TextField websiteUrlField;

    @FXML
    private TextArea DescriptionFiled;

    @FXML
    private Button signUpButton;

    @FXML
    private Label name;

    @FXML
    private Label email_lable;

    @FXML
    private Label pasword_lable;

    @FXML
    private Label phone_naumber_lable;

    @FXML
    private Label AddressLable;

    @FXML
    private Label socialMediaLable;

    @FXML
    private Label DescriptionLable;
    @FXML
    private Label LogoUrlLable;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        App.stage.setWidth(750);
        App.stage.setHeight(650);
    }
    
    @FXML
    private void validateAndSignUp(ActionEvent event) throws IOException {
        boolean valid = true;

        if (nameField.getText().isEmpty()) {
            valid = false;
            setInvalidLabel(name);
        } else {
            resetLabel(name);
        }

        if (!emailField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            valid = false;
            setInvalidLabel(email_lable);
        } else {
            resetLabel(email_lable);
        }

        if (passwordField.getText().length() < 6) {
            valid = false;
            setInvalidLabel(pasword_lable);
        } else {
            resetLabel(pasword_lable);
        }

        if (!Pattern.matches("^[0-9\\-\\(\\)\\s]+$", phoneNumberField.getText())) {
            valid = false;
            setInvalidLabel(phone_naumber_lable);
        } else {
            resetLabel(phone_naumber_lable);
        }

        if (addressField.getText().isEmpty()) {
            valid = false;
            setInvalidLabel(AddressLable);
        } else {
            resetLabel(AddressLable);
        }

        if (!logoField.getText().matches("^https?://.+") && logoField.getText().isEmpty()) {
            valid = false;
            setInvalidLabel(LogoUrlLable);
        } else {
            resetLabel(LogoUrlLable);
        }

        if (!websiteUrlField.getText().matches("^https?://(?:www\\.)?([a-zA-Z0-9-]+)\\.(com|net|org|io|...)$")) {
            valid = false;
            setInvalidLabel(socialMediaLable);
        } else {
            resetLabel(socialMediaLable);
        }

        String[] words = DescriptionFiled.getText().split("\\s+");
        if (words.length > 100 || words.length < 20) {
            valid = false;
            setInvalidLabel(DescriptionLable);
        } else {
            resetLabel(DescriptionLable);
        }

        if (valid) {
            Organizer organizer = new Organizer();
            organizer.setName(nameField.getText());
            organizer.setEmail(emailField.getText());
            organizer.setPassword(passwordField.getText());
            organizer.setPhoneNumber(phoneNumberField.getText());
            organizer.setAddress(addressField.getText());
            organizer.setDescription(DescriptionFiled.getText());
            organizer.setLogoUrls(new ArrayList<>(List.of(logoField.getText())));
            organizer.setSocialMediaLinks(websiteUrlField.getText());
            App.OrganizerFile.addItem(organizer);
            App.setRoot("login");
        }
    }

    private void setInvalidLabel(Label label) {
        label.setTextFill(Color.RED);
    }

    private void resetLabel(Label label) {
        label.setTextFill(Color.BLACK);
    }
}

package com.event_managment_system.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.event_managment_system.App;
import com.event_managment_system.entities.Attendee;
import com.event_managment_system.entities.Organizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Label titleLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label failedLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Label Sign_up;

    @FXML
    private ChoiceBox<String> choiceBox;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        choiceBox.getItems().addAll("Attendee", "Organizer");
        choiceBox.setValue("Attendee");
    }


    public void sign_up_meth(MouseEvent event) throws IOException {
        if (choiceBox.getValue().equals("Attendee")) {
            App.setRoot("AttendeeSignUp");
        }
        else{
            System.out.println("orgnaizer");
            App.setRoot("OrganizerSignUp");
        }
    }

    public void login(ActionEvent e) throws IOException {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
    
        if (email.isEmpty() || password.isEmpty()) {
            showErrorMessage("Email and password cannot be empty.");
            return;
        }
    
        if (choiceBox.getValue().equals("Attendee")) {
            Attendee attendee = findAttendeeByEmail(email);
            if (attendee != null && attendee.getPassword().equals(password)) {
                App.user = attendee;
                App.setRoot("AttendeeHomePage");
            } else {
                showErrorMessage("Incorrect Email or password");
            }
        } else if (choiceBox.getValue().equals("Organizer")) {
            Organizer organizer = findOrganizerByEmail(email);
            if (organizer != null && organizer.getPassword().equals(password)) {
                App.org = organizer;
                App.setRoot("OrganizerHomePage");
            } else {
                showErrorMessage("Incorrect Email or password");
            }
        }
    }
    
    private Attendee findAttendeeByEmail(String email) {
        for (Attendee attendee : App.attendeesFile.getAllItems()) {
            if (attendee.getEmail().equals(email)) {
                return attendee;
            }
        }
        return null;
    }
    
    private Organizer findOrganizerByEmail(String email) {
        for (Organizer organizer : App.OrganizerFile.getAllItems()) {
            if (organizer.getEmail().equals(email)) {
                return organizer;
            }
        }
        return null;
    }

    private void showErrorMessage(String message) {
        failedLabel.setText(message);
        failedLabel.setVisible(true);
    }
}

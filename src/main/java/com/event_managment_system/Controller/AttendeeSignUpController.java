package com.event_managment_system.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.event_managment_system.App;
import com.event_managment_system.entities.Attendee;
import com.event_managment_system.entities.Organizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class AttendeeSignUpController implements Initializable {

    @FXML
    private Label name;

    @FXML
    private TextField nameField;

    @FXML
    private Label IdLable;

    @FXML
    private TextField IdField1;

    @FXML
    private Label email_lable;

    @FXML
    private TextField emailField;

    @FXML
    private Label pasword_lable;

    @FXML
    private TextField passwordField;

    @FXML
    private Label majorDepartmentLable;

    @FXML
    private ChoiceBox<String> majorDepartmentFiled;

    @FXML
    private Label yearofstudyLable;

    @FXML
    private ChoiceBox<String> yearofstudyFiled;

    @FXML
    private Label DateOfBirthLable;

    @FXML
    private DatePicker DateOfBirthFiled;

    @FXML
    private Label genderLable;

    @FXML
    private ChoiceBox<String> genderField;

    @FXML
    private Label ProfilePicLinkLable;

    @FXML
    private TextField ProfilePicLinkFiled;

    @FXML
    private Button signUpButton;

    String[] engineeringDepartments = {
        "Mechanical Engineering",
        "Electrical Engineering",
        "Civil Engineering",
        "Computer Science",
        "Chemical Engineering",
        "Aerospace Engineering",
        "Biomedical Engineering",
        "Environmental Engineering",
        "Materials Science and Engineering",
        "Industrial Engineering",
        "Nuclear Engineering",
        "Software Engineering",
        // Add more departments as needed
    };

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        App.stage.setWidth(750);
        App.stage.setHeight(650);
        yearofstudyFiled.getItems().addAll("1st year", "2nd year", "3rd year", "4th year");
        genderField.getItems().addAll("Female", "Male");
        majorDepartmentFiled.getItems().addAll(engineeringDepartments);
        
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

        if (IdField1.getText().isEmpty() || !IdField1.getText().matches("\\d{4}/\\d{2}")) {
            valid = false;
            setInvalidLabel(IdLable);
        } else {
            resetLabel(IdLable);
        }

        if (DateOfBirthFiled.getValue() == null || DateOfBirthFiled.getValue().isAfter(LocalDate.now())) {
            valid = false;
            setInvalidLabel(DateOfBirthLable);
        } else {
            resetLabel(DateOfBirthLable);
        }

        if (majorDepartmentFiled.getValue() != null && !majorDepartmentFiled.getValue().isEmpty()) {
            resetLabel(majorDepartmentLable);
        } else {
            valid = false;
            setInvalidLabel(majorDepartmentLable);
        }
    
        if (yearofstudyFiled.getValue() != null && !yearofstudyFiled.getValue().isEmpty()) {
            resetLabel(yearofstudyLable);
        } else {
            valid = false;
            setInvalidLabel(yearofstudyLable);
        }
    
        if (genderField.getValue() != null && !genderField.getValue().isEmpty()) {
            resetLabel(genderLable);
        } else {
            valid = false;
            setInvalidLabel(genderLable);
        }
    
        if (!ProfilePicLinkFiled.getText().isEmpty()) {
            resetLabel(ProfilePicLinkLable);
        } else {
            valid = false;
            setInvalidLabel(ProfilePicLinkLable);
        }
    
        if (valid) {
            Attendee attendee = new Attendee();
            attendee.setFullName(nameField.getText());
            attendee.setStudentId(IdField1.getText());
            attendee.setEmail(emailField.getText());
            attendee.setPassword(passwordField.getText());
            attendee.setMajorDepartment(majorDepartmentFiled.getValue());
            attendee.setYearOfStudy(yearofstudyFiled.getValue());
            LocalDate utilDate = DateOfBirthFiled.getValue();
            attendee.setDateOfBirth(utilDate);
            attendee.setGender(genderField.getValue());
            attendee.setProfilePictureUrl(App.downloadPic(ProfilePicLinkFiled.getText()));
            App.attendeesFile.addItem(attendee);
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
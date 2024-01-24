package com.event_managment_system;

import java.io.IOException;

import com.event_managment_system.DataManager.FileHandler;
import com.event_managment_system.entities.Attendee;
import com.event_managment_system.entities.Event;
import com.event_managment_system.entities.Organizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static final String StorgePath="/com/event_managment_system/";
    public static Scene scene;
    public static Stage stage;
    public static Attendee user;
    public static Organizer org;
    public static FileHandler<Event> eventFile=new FileHandler<>(StorgePath+"Storage/events.dat");
    public static FileHandler<Attendee> attendeesFile=new FileHandler<>(StorgePath+"Storage/attendees.dat");
    public static FileHandler<Organizer> OrganizerFile=new FileHandler<>(StorgePath+"Storage/organizers.dat");

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        App.stage=stage;
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
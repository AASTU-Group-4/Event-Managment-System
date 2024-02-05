package com.event_managment_system;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.event_managment_system.DataManager.FileHandler;
import com.event_managment_system.entities.Attendee;
import com.event_managment_system.entities.Event;
import com.event_managment_system.entities.Organizer;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;


public class App extends Application {

    public static final String StorgePath="/com/event_managment_system/";
    public static final String imageStorgePath="/com/event_managment_system/image/storage/";
    public static Scene scene;
    public static Stage stage;
    public static Attendee user;
    public static Organizer org;
    public static FileHandler<Event> eventFile = new FileHandler<>(StorgePath+"Storage/events.dat");
    public static FileHandler<Attendee> attendeesFile= new FileHandler<>(StorgePath + "Storage/attendees.dat");
    public static FileHandler<Organizer> OrganizerFile= new FileHandler<>(StorgePath + "Storage/organizers.dat");

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        App.stage=stage;
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public static Image loadImage(InputStream imgStream){
        try {
            BufferedImage bufferedImage = ImageIO.read(imgStream);
            if (bufferedImage != null) {
                return SwingFXUtils.toFXImage(bufferedImage, null);
                
            } else {
                System.out.println("Failed to read image.");
            }
        }
         catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
    public static String downloadPic(String link) {
        try {
            URL url = new URL(link);
            InputStream inputStream = url.openStream();
            String outputFilePath = UUID.randomUUID().toString() + ".png";
            Files.copy(inputStream, Path.of("../../../resources/com/event_managment_system/image/storage/" + outputFilePath), StandardCopyOption.REPLACE_EXISTING);
            return outputFilePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "no-image.png";
    }

}
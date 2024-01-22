package com.event_managment_system;

import com.event_managment_system.DataManager.FileHandler;
import com.event_managment_system.entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class DataPopulator {
    private static final int NUM_ORGANIZERS = 50;
    private static final int NUM_EVENTS_PER_ORGANIZER = 7;
    private static final int NUM_ATTENDEES = 350;
    private static final int MAX_EVENTS_ATTENDED = 30;

    private static final List<Event> allEvents = new ArrayList<>();
    private static final List<Attendee> allAttendees = new ArrayList<>();
    private static final List<Organizer> allOrganizers = new ArrayList<>();

    public static void main(){
        String storagePath = "/com/event_managment_system/Storage/";

        FileHandler<Organizer> organizerFileHandler = new FileHandler<>(storagePath + "organizers.dat");
        FileHandler<Event> eventFileHandler = new FileHandler<>(storagePath + "events.dat");
        FileHandler<Attendee> attendeeFileHandler = new FileHandler<>(storagePath + "attendees.dat");

        for (int i = 0; i < NUM_ATTENDEES; i++) {
            Attendee attendee = createRandomAttendee();
            allAttendees.add(attendee);
        }

        // Populate organizers.dat
        for (int i = 0; i < NUM_ORGANIZERS; i++) {
            Organizer organizer = createRandomOrganizer();
            allOrganizers.add(organizer);

            // Populate events.dat for this organizer
            for (int j = 0; j < NUM_EVENTS_PER_ORGANIZER; j++) {
                Event event = createRandomEvent(organizer);
                allEvents.add(event);
            }
        }

        // Link attendees to events
        linkAttendeesToEvents();

        linkEventsOrganizedToOrganizers();

        updateOrganizersWithImageURLs();
        updateEventsWithImageURLs();
        organizerFileHandler.setData(allOrganizers);
        eventFileHandler.setData(allEvents);
        attendeeFileHandler.setData(allAttendees);
        organizerFileHandler.download();
        eventFileHandler.download();
        attendeeFileHandler.download();
    }

    private static Organizer createRandomOrganizer() {
        Organizer organizer = new Organizer();
        organizer.setName(generateRandomName());
        organizer.setEmail(generateRandomEmail());
        organizer.setPassword(generateRandomPassword());
        organizer.setPhoneNumber(generateRandomPhoneNumber());
        organizer.setAddress(generateRandomAddress());
        organizer.setDescription(generateRandomSentence());
        organizer.setSocialMediaLinks(generateRandomUrl());
        return organizer;
    }

    private static Event createRandomEvent(Organizer organizer) {
        Event event = new Event();
        event.setTitle(generateRandomBuzzword());
        event.setDescription(generateRandomParagraph());
        event.setDateTime(generateRandomDate(30));
        event.setVenue(generateRandomAddress());
        event.setOrganizerId(organizer.getId());
        event.setRegistrationDeadline(generateRandomDate(10));
        event.setAgenda(generateRandomAgenda());
    
        // Add random tags
        int numTags = generateRandomNumber(1, 5);
        for (int k = 0; k < numTags; k++) {
            event.addTag(generateRandomWord());
        }
    
        // Add attendees
        int numAttendees = generateRandomNumber(1, 20);
        for (int i = 0; i < numAttendees; i++) {
            Attendee randomAttendee = allAttendees.get(generateRandomNumber(allAttendees.size()));
            event.addAttendee(randomAttendee);
        }
    
        // Add image URLs
        int numImageURLs = generateRandomNumber(1, 3);
        for (int i = 0; i < numImageURLs; i++) {
            event.addEventImageUrl("https://picsum.photos/200/300?random=" + generateRandomNumber(1000));
        }
    
        // Add event to the FileHandler
        return event;
    }
    

    private static Attendee createRandomAttendee() {
        Attendee attendee = new Attendee();
        attendee.setProfilePictureUrl("https://picsum.photos/200/300?random=" + generateRandomNumber(1000));
        attendee.setFullName(generateRandomName());
        attendee.setStudentId(generateRandomDigits(8));
        attendee.setEmail(generateRandomEmail());
        attendee.setPassword(generateRandomPassword());
        attendee.setMajorDepartment(generateRandomCourse());
        attendee.setYearOfStudy(generateRandomNumber(1, 5) + " year");
        attendee.setGender(generateRandomGender());
        return attendee;
    }
    private static Date generateRandomDate(int daysIntoFuture) {
        long currentMillis = System.currentTimeMillis();
        long randomMillis = ThreadLocalRandom.current().nextLong(0, daysIntoFuture * 24L * 60L * 60L * 1000L);
        return new Date(currentMillis + randomMillis);
    }

    private static String generateRandomName() {
        String[] firstNames = {"John", "Jane", "Alice", "Bob", "Eve", "Charlie"};
        String[] lastNames = {"Doe", "Smith", "Johnson", "Brown", "Taylor"};
        String firstName = firstNames[generateRandomNumber(firstNames.length)];
        String lastName = lastNames[generateRandomNumber(lastNames.length)];
        return firstName + " " + lastName;
    }

    private static String generateRandomEmail() {
        String[] emailProviders = {"gmail.com", "yahoo.com", "outlook.com", "example.com"};
        String randomProvider = emailProviders[generateRandomNumber(emailProviders.length)];
        return "user" + generateRandomNumber(1000) + "@" + randomProvider;
    }

    private static String generateRandomPassword() {
        String[] passwordOptions = {"password123", "securePass", "letMeIn", "secretPass"};
        return passwordOptions[generateRandomNumber(passwordOptions.length)];
    }

    private static String generateRandomPhoneNumber() {
        return "+1-" + generateRandomDigits(10);
    }

    private static String generateRandomAddress() {
        return generateRandomNumber(1000) + " Random St, Cityville";
    }



    private static void linkAttendeesToEvents() {
        for (Attendee attendee : allAttendees) {
            int numEventsAttended = generateRandomNumber(MAX_EVENTS_ATTENDED);
            for (int j = 0; j < numEventsAttended; j++) {
                Event randomEvent = allEvents.get(generateRandomNumber(allEvents.size()));
                attendee.addEventAttended(randomEvent);
            }
        }
    }
    private static Agenda generateRandomAgenda() {
        Agenda agenda = new Agenda();
    
        // Generate a random number of activities (between 1 and 5)
        int numActivities = generateRandomNumber(1, 6);
    
        for (int i = 0; i < numActivities; i++) {
            String title = generateRandomWord() + " Activity";
            String startTime = generateRandomTime();
            String endTime = generateRandomTime();
    
            Activity activity = new Activity(title, startTime, endTime);
            agenda.addActivity(activity);
        }
    
        return agenda;
    }
    
    private static String generateRandomTime() {
        int hour = generateRandomNumber(24);
        int minute = generateRandomNumber(60);
    
        return String.format("%02d:%02d", hour, minute);
    }
    
    private static void linkEventsOrganizedToOrganizers() {
        for (Organizer organizer : allOrganizers) {
            List<UUID> organizedEvents = new ArrayList<>();
            int numOrganizedEvents = generateRandomNumber(5, 15);
            for (int j = 0; j < numOrganizedEvents; j++) {
                Event randomEvent = allEvents.get(generateRandomNumber(allEvents.size()));
                organizedEvents.add(randomEvent.getId());
            }
            organizer.setEventsOrganized(organizedEvents);
        }
    }

    private static void updateOrganizersWithImageURLs() {
        for (Organizer organizer : allOrganizers) {
            organizer.addLogoUrl("https://picsum.photos/200/300?random=" + generateRandomNumber(1000));
        }
    }

    private static void updateEventsWithImageURLs() {
        for (Event event : allEvents) {
            event.addEventImageUrl("https://picsum.photos/200/300?random=" + generateRandomNumber(1000));
        }
    }

    private static String generateRandomSentence() {
        String[] sentences = {"Lorem ipsum dolor sit amet.", "Consectetur adipiscing elit.",
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris."};
        return sentences[generateRandomNumber(sentences.length)];
    }

    private static String generateRandomUrl() {
        return "http://www.example.com/" + generateRandomWord();
    }

    private static String generateRandomBuzzword() {
        String[] buzzwords = {"Innovative", "Revolutionary", "Cutting-edge", "Transformative", "Disruptive"};
        return buzzwords[generateRandomNumber(buzzwords.length)];
    }

    private static String generateRandomParagraph() {
        return "This is a random paragraph. " + generateRandomSentence() + " "
                + generateRandomSentence() + " " + generateRandomSentence();
    }

    private static String generateRandomWord() {
        String[] words = {"apple", "banana", "orange", "grape", "kiwi", "pear"};
        return words[generateRandomNumber(words.length)];
    }

    private static int generateRandomNumber(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("bound must be positive");
        }
        return ThreadLocalRandom.current().nextInt(max);
    }
    
    private static int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be less than max");
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private static String generateRandomDigits(int length) {
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < length; i++) {
            digits.append(generateRandomNumber(10));
        }
        return digits.toString();
    }

    private static String generateRandomCourse() {
        String[] courses = {"Computer Science", "Engineering", "Biology", "Mathematics", "Physics"};
        return courses[generateRandomNumber(courses.length)];
    }

    private static String generateRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return genders[generateRandomNumber(genders.length)];
    }

}


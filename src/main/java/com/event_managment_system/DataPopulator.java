package com.event_managment_system;

import com.event_managment_system.DataManager.FileHandler;
import com.event_managment_system.entities.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.net.URL;
import java.nio.file.Path;


public class DataPopulator {
    private static final int NUM_ORGANIZERS = 15;
    private static final int NUM_EVENTS_PER_ORGANIZER = 5;
    private static final int NUM_ATTENDEES = 75;
    private static final int MAX_EVENTS_ATTENDED = 15;

    private static final List<Event> allEvents = new ArrayList<>();
    private static final List<Attendee> allAttendees = new ArrayList<>();
    private static final List<Organizer> allOrganizers = new ArrayList<>();

    public static void main(){
        String storagePath = "/home/yeabsira/Documents/GitHub/db/";

        // Now you can create FileHandler instances
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

        organizerFileHandler.setData(allOrganizers);
        eventFileHandler.setData(allEvents);
        attendeeFileHandler.setData(allAttendees);
        organizerFileHandler.download();
        eventFileHandler.download();
        attendeeFileHandler.download();
    }

    private static Organizer createRandomOrganizer() {
        Organizer organizer = new Organizer();
        List<String> img=new ArrayList<String>();
        for(int i=0; i<10; i++)
            img.add(RandomPic());

        organizer.setName(generateRandomName());
        organizer.setEmail(generateRandomEmail(organizer.getName()));
        organizer.setPassword(generateRandomPassword());
        organizer.setPhoneNumber(generateRandomPhoneNumber());
        organizer.setAddress(generateRandomAddress());
        organizer.setDescription(generateRandomSentence());
        organizer.setSocialMediaLinks(generateRandomUrl());
        organizer.setLogoUrls(img);
        return organizer;
    }

    private static Event createRandomEvent(Organizer organizer) {
        Random random=new Random();
        Event event = new Event();
        event.setTitle(generateRandomBuzzword());
        event.setDescription(generateRandomParagraph());
        event.setDateTime(generateRandomDate(30));
        event.setVenue(generateRandomAddress());
        event.setOrganizerId(organizer.getId());
        event.setRegistrationDeadline(generateRandomDate(10));
        event.setAgenda(generateRandomAgenda());
        event.setCapacity(generateRandomNumber(MAX_EVENTS_ATTENDED, 100));
    
        // Add random tags
        int numTags = generateRandomNumber(5, 11);
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
            event.addEventImageUrl(RandomPic());
        }
    
        // Add event to the FileHandler
        return event;
    }
    

    private static Attendee createRandomAttendee() {
        Attendee attendee = new Attendee();
        attendee.setProfilePictureUrl(RandomPic());
        attendee.setFullName(generateRandomName());
        attendee.setStudentId(generateRandomDigits(8));
        attendee.setEmail(generateRandomEmail(attendee.getFullName()));
        attendee.setPassword(generateRandomPassword());
        attendee.setMajorDepartment(generateRandomCourse());
        attendee.setYearOfStudy(generateRandomNumber(1, 5) + " year");
        attendee.setGender(generateRandomGender());
        attendee.setDateOfBirth(generateRandomBirthdate());
        return attendee;
    }
    private static String RandomPic() {
        try {
            String imageUrl = "https://picsum.photos/100/100?random=" + Math.floor(Math.random() * 1000);
            URL url = new URL(imageUrl);
            try (InputStream inputStream = url.openStream()) {
                String outputFilePath = UUID.randomUUID().toString() + ".png";
                Files.copy(inputStream, Path.of("/home/yeabsira/MyProjects/Java/event/src/main/resources/com/event_managment_system/image/storage/" + outputFilePath), StandardCopyOption.REPLACE_EXISTING);
                return outputFilePath;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static LocalDate generateRandomBirthdate() {
        Random random = new Random();
        int year = 1970 + random.nextInt(30); // Random year between 1970 and 1999
        int month = random.nextInt(12) + 1;   // Random month between 1 and 12
        int day = random.nextInt(28) + 1;     // Random day between 1 and 28 (considering February)

        return LocalDate.of(year, month, day);
    }

    private static Date generateRandomDate(int daysIntoFuture) {
        long currentMillis = System.currentTimeMillis();
        long randomMillis = ThreadLocalRandom.current().nextLong(0, daysIntoFuture * 24L * 60L * 60L * 1000L);
        return new Date(currentMillis + randomMillis);
    }

    private static String generateRandomName() {
        String[] firstNames = {"John", "Jane", "Alice", "Bob", "Eve", "Charlie", "Olivia", "James", "Sophia", "Liam",
                               "Emma", "Noah", "Ava", "Mia", "Lucas", "Isabella", "Ethan", "Amelia", "Logan", "Ella"};
        
        String[] lastNames = {"Doe", "Smith", "Johnson", "Brown", "Taylor", "Anderson", "Williams", "Jones", "Moore", "Clark",
                              "Martinez", "Harris", "Nelson", "Turner", "Perez", "Murphy", "Martin", "White", "Hill", "Allen"};

        String firstName = firstNames[generateRandomNumber(firstNames.length)];
        String lastName = lastNames[generateRandomNumber(lastNames.length)];
        return firstName + " " + lastName;
    }

    private static String generateRandomEmail(String name) {
        String[] emailProviders = {"gmail.com", "yahoo.com", "outlook.com", "example.com"};
        String randomProvider = emailProviders[generateRandomNumber(emailProviders.length)];
        return name.replaceAll("\\s", "") + generateRandomNumber(1000) + "@" + randomProvider;
    }

    private static String generateRandomPassword() {
        String[] passwordOptions = {"password123", "securePass", "letMeIn", "secretPass"};
        return passwordOptions[generateRandomNumber(passwordOptions.length)];
    }

    private static String generateRandomPhoneNumber() {
        return "+1-" + generateRandomDigits(10);
    }

    private static String generateRandomAddress() {
        Random random = new Random();
        String[] streetNames = {"Maple", "Oak", "Cedar", "Elm", "Pine", "Birch", "Willow", "Cypress", "Hickory", "Poplar"};
        String[] cityNames = {"Springfield", "Riverside", "Meadowville", "Hillcrest", "Woodland", "Sunnydale", "Lakewood", "Greenfield", "Oceanview", "Pleasantville"};

        String street = streetNames[random.nextInt(streetNames.length)];
        int streetNumber = 1 + random.nextInt(1000); // Random street number between 1 and 1000
        String city = cityNames[random.nextInt(cityNames.length)];

        return streetNumber + " " + street + " St, " + city;
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


    private static String generateRandomSentence() {
        Random random = new Random();
        String[] sentences = {"Lorem ipsum dolor sit amet.", "Consectetur adipiscing elit.",
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.",
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Nullam varius, turpis et commodo pharetra, est eros bibendum elit, nec luctus magna felis sollicitudin mauris.",
                "Proin eu nisl et arcu porttitor commodo. Nullam euismod, velit vel cursus tristique, ligula sapien dignissim odio, non consectetur orci enim vel nisi."};

        return sentences[random.nextInt(sentences.length)];
    }

    private static String generateRandomUrl() {
        return "http://www.example.com/" + generateRandomWord();
    }

    private static String generateRandomBuzzword() {
        Random random=new Random();
        String[] eventWords = {"Conference", "Summit", "Symposium", "Expo", "Seminar",
                "Workshop", "Convention", "Festival", "Exhibition", "Gala",
                "Networking", "Panel", "Showcase", "Meetup", "Hackathon",
                "Retreat", "Forum", "Roundtable", "Webinar", "Concert",
                "Product Launch", "Competition", "Show", "Ceremony", "Demo"};

        return eventWords[random.nextInt(eventWords.length)];
    }

    private static String generateRandomParagraph() {
        Random random = new Random();
        int paragraphLength = random.nextInt(3) + 2; // Random length between 2 and 4 sentences
        StringBuilder paragraph = new StringBuilder();

        for (int i = 0; i < paragraphLength; i++) {
            paragraph.append(generateRandomSentence()).append(" ");
        }

        return paragraph.toString().trim();
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


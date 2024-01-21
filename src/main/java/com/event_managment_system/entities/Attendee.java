package com.event_managment_system.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

public class Attendee implements Serializable, root {
    private static final long serialVersionUID = 1L;
    private UUID attendeeId;
    private String fullName;
    private String studentId;
    private String email;
    private String password;
    private String majorDepartment;
    private String yearOfStudy;
    private LocalDate dateOfBirth;
    private String gender;
    private List<UUID> eventsAttended;
    private Date registrationTimestamp;
    private Date lastUpdateTimestamp;
    private String profilePictureUrl;

    public Attendee() {
        this.attendeeId = UUID.randomUUID();
        this.eventsAttended = new ArrayList<>();
        this.registrationTimestamp = new Date();
        this.lastUpdateTimestamp = new Date();
    }

    public UUID getId() {
        return attendeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        updateTimestamp();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
        updateTimestamp();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        updateTimestamp();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        updateTimestamp();
    }

    public String getMajorDepartment() {
        return majorDepartment;
    }

    public void setMajorDepartment(String majorDepartment) {
        this.majorDepartment = majorDepartment;
        updateTimestamp();
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
        updateTimestamp();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        updateTimestamp();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        updateTimestamp();
    }

    public List<UUID> getEventsAttended() {
        return eventsAttended;
    }

    public void setEventsAttended(List<UUID> eventsAttended) {
        this.eventsAttended = eventsAttended;
        updateTimestamp();
    }

    public void addEventAttended(Event event) {
        this.eventsAttended.add(event.getId());
        updateTimestamp();
    }

    public Date getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public Date getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
        updateTimestamp();
    }

    private void updateTimestamp() {
        this.lastUpdateTimestamp = new Date();
    }
}

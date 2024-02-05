package com.event_managment_system.entities;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.event_managment_system.App;

public class Event implements Serializable , root{
    private static final long serialVersionUID = 1L;
    private UUID eventId;
    private String title;
    private String description;
    private Date dateTime;
    private String venue;
    private UUID organizerId;
    private List<String> tags;
    private int capacity;
    private Date registrationDeadline;
    private Agenda agenda;
    private Date lastUpdateTimestamp;
    private List<String> eventImageUrl;
    private List<UUID> attendees; // New field

    public Event() {
        this.eventId = UUID.randomUUID();
        this.tags = new ArrayList<>();
        this.lastUpdateTimestamp = new Date();
        this.eventImageUrl = new ArrayList<>();
        this.attendees = new ArrayList<>(); // Initialize the list
    }

    public UUID getId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateTimestamp();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updateTimestamp();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        updateTimestamp();
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
        updateTimestamp();
    }

    public UUID getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(UUID organizerId) {
        this.organizerId = organizerId;
        updateTimestamp();
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
        updateTimestamp();
    }

    public void addTag(String tag) {
        this.tags.add(tag);
        updateTimestamp();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        updateTimestamp();
    }

    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
        updateTimestamp();
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
        updateTimestamp();
    }

    public Date getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public List<String> getEventImageUrl() {
        return eventImageUrl;
    }
    public List<InputStream> allImageStream(){
        List<InputStream> all=new ArrayList<>();
        for(String str:this.eventImageUrl){
            all.add(getClass().getResourceAsStream(App.imageStorgePath+str));
        }
       return all;
    }
    public void addEventImageUrl(String url){
        this.eventImageUrl.add(url);
    }
    public void setEventImageUrl(List<String> eventImageUrl) {
        this.eventImageUrl = eventImageUrl;
        updateTimestamp();
    }
    public boolean isregisted(Attendee person){
        return (this.attendees.contains(person.getId()))? true:false;
    }
    public List<UUID> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<UUID> attendees) {
        this.attendees = attendees;
        updateTimestamp();
    }

    public void addAttendee(Attendee attendee) {
        this.attendees.add(attendee.getId());
    }
    public void removeAttendee(Attendee attendee){
        this.attendees.remove(attendee.getId());
    }

    public boolean isFull(){
        return this.attendees.size()<capacity? false:true;
    }

    private void updateTimestamp() {
        this.lastUpdateTimestamp = new Date();
    }
    public boolean hasEventPassed() {
        Date currentDate = new Date();
        return dateTime.before(currentDate);
    }
    public boolean hasRegistrationPassed() {
        Date currentDate = new Date();
        return this.registrationDeadline.before(currentDate);
    }
    public boolean canRegister(Attendee attendee){
        if(hasEventPassed()||hasRegistrationPassed()||isFull()||isregisted(attendee)){
            return false;
        }
        return true;
    }
}

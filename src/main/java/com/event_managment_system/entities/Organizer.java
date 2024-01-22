package com.event_managment_system.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Organizer implements Serializable, root {
    private static final long serialVersionUID = 1L;
    private UUID organizerId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String description;
    private List<String> logoUrls; // Changed to a List of URLs
    private List<UUID> eventsOrganized;
    private String socialMediaLinks;
    private Date creationTimestamp;
    private Date lastUpdateTimestamp;

    public Organizer() {
        this.organizerId = UUID.randomUUID();
        this.logoUrls = new ArrayList<>();
        this.eventsOrganized = new ArrayList<>();
        this.creationTimestamp = new Date();
        this.lastUpdateTimestamp = new Date();
    }

    public UUID getId() {
        return organizerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        updateTimestamp();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        updateTimestamp();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updateTimestamp();
    }
    
    public void addLogoUrls(String url){
        this.logoUrls.add(url);
    }

    public List<String> getLogoUrls() {
        return logoUrls;
    }

    public void setLogoUrls(List<String> logoUrls) {
        this.logoUrls = logoUrls;
        updateTimestamp();
    }

    public void addLogoUrl(String logoUrl) {
        this.logoUrls.add(logoUrl);
        updateTimestamp();
    }

    public List<UUID> getEventsOrganized() {
        return eventsOrganized;
    }

    public void setEventsOrganized(List<UUID> eventsOrganized) {
        this.eventsOrganized = eventsOrganized;
        updateTimestamp();
    }

    public void addEventOrganized(Event event) {
        this.eventsOrganized.add(event.getId());
        updateTimestamp();
    }

    public String getSocialMediaLinks() {
        return socialMediaLinks;
    }

    public void setSocialMediaLinks(String socialMediaLinks) {
        this.socialMediaLinks = socialMediaLinks;
        updateTimestamp();
    }


    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public Date getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    private void updateTimestamp() {
        this.lastUpdateTimestamp = new Date();
    }
}

package com.event_managment_system.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Activity> activities;

    public Agenda() {
        this.activities = new ArrayList<>();
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public boolean isEmpty(){
        return this.activities.isEmpty();
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
}

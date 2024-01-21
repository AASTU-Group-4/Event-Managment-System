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

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("Agenda:\n");
        for (Activity activity : activities) {
            sb.append(activity).append("\n");
        }
        return sb.toString();
    }
}

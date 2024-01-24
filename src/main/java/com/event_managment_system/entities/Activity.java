package com.event_managment_system.entities;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Activity implements Serializable {
        private static final long serialVersionUID = 1L;

        private String title;
        private String startTime;
        private String endTime;

        public Activity(String title, String startTime, String endTime) {
            this.title = new String(title);
            this.startTime = new String(startTime);
            this.endTime = new String(endTime);
        }

        public String getTitle() {
            return this.title;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }
        public void setTitle (String title) {
            this.title=title;
        }
        public void setStartTime(String sTime) {
            this.startTime=sTime;
        }
        public void setEndTime (String eTime) {
            this.endTime=eTime;
        }
}
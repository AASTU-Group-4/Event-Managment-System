package com.event_managment_system.entities;

import java.io.Serializable;

public class Activity implements Serializable {
        private static final long serialVersionUID = 1L;

        private String title;
        private String startTime;
        private String endTime;

        public Activity(String title, String startTime, String endTime) {
            this.title = title;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getTitle() {
            return title;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public String toString() {
            return "Activity{" +
                    "title='" + title + '\'' +
                    ", startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    '}';
        }
    }
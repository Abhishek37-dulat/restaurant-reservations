package com.pairlearning.expensetrackerapi.domain;

import java.util.List;
import java.util.Map;

public class Restaurant {
    private Integer restaurantId;
    private String name;
    private List<String> cuisines;
    private String location;
    private Map<String, String> workingDays;
    private int timeSlotInterval;
    private Map<String, TableRequest> capacity;  // Change this to Map

    private String imageUrl;

    public Restaurant(Integer restaurantId, String name, String location, List<String> cuisines,
                      Map<String, String> workingDays, int timeSlotInterval, String imageUrl,
                      Map<String, TableRequest> capacity) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisines = cuisines;
        this.location = location;
        this.workingDays = workingDays;
        this.timeSlotInterval = timeSlotInterval;
        this.imageUrl = imageUrl;
        this.capacity = capacity;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, String> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Map<String, String> workingDays) {
        this.workingDays = workingDays;
    }

    public int getTimeSlotInterval() {
        return timeSlotInterval;
    }

    public void setTimeSlotInterval(int timeSlotInterval) {
        this.timeSlotInterval = timeSlotInterval;
    }

    public Map<String, TableRequest> getCapacity() {
        return capacity;
    }

    public void setCapacity(Map<String, TableRequest> capacity) {
        this.capacity = capacity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Static inner class
    public static class TableRequest {
        private int numberOfTables;
        private int seatingCapacity;

        // Constructors, getters, and setters

        public TableRequest(int numberOfTables, int seatingCapacity) {
            this.numberOfTables = numberOfTables;
            this.seatingCapacity = seatingCapacity;
        }

        public int getNumberOfTables() {
            return numberOfTables;
        }

        public void setNumberOfTables(int numberOfTables) {
            this.numberOfTables = numberOfTables;
        }

        public int getSeatingCapacity() {
            return seatingCapacity;
        }

        public void setSeatingCapacity(int seatingCapacity) {
            this.seatingCapacity = seatingCapacity;
        }
    }
}

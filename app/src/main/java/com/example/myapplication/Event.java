package com.example.myapplication;

public class Event {
    private String name;
    private String date;
    private String time;
    private String location;
    private String type;

    public   Event(String name, String date, String time, String location, String type) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.type = type;
    }
    public  Event() {
        this.name = "asd";
        this.date = "asd";
        this.time = "asd";
        this.location = "asd";
        this.type = "asd";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

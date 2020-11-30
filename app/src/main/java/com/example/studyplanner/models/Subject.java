package com.example.studyplanner.models;

public class Subject {

    private String title;
    private int classRoom;
    private String hour;

    public Subject(String title, int classRoom, String hour) {
        this.title = title;
        this.classRoom = classRoom;
        this.hour = hour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}

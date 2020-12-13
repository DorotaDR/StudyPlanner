package com.example.studyplanner.models;

public class Subject implements Comparable<Subject> {

    private String title;
    private int classRoom;
    private String startTime;
    private String endTime;

    public Subject(String title, int classRoom, String startTime, String endTime) {
        this.title = title;
        this.classRoom = classRoom;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;

    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private Integer getStartTimeInMinutes(){
        String[] splittedTime = this.startTime.split(":");
        Integer hour = Integer.valueOf(splittedTime[0]);
        Integer minutes = Integer.valueOf(splittedTime[1]);
        return hour*60+minutes;
    }

    @Override
    public int compareTo(Subject sub) {
        return this.getStartTimeInMinutes().compareTo(sub.getStartTimeInMinutes());
    }
}

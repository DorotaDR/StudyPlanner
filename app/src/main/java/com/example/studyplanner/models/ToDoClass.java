package com.example.studyplanner.models;

public class ToDoClass {
    private String title;
    private String subject;
    private String date;
    private boolean done;

    public ToDoClass(String title, String subject, String date, boolean done) {
        this.title = title;
        this.subject = subject;
        this.date = date;
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

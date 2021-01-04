package com.example.studyplanner.models;

public class News {

    private String title;
    private String detail;
    private String Uri;

    public News(String title, String detail, String Uri){

        this.detail = detail;
        this.title = title;
        this.Uri = Uri;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUri() {return Uri;}
    public void setUri(String Uri) {this.Uri = Uri;}
}

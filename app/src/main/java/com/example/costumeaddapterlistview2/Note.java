package com.example.costumeaddapterlistview2;

import java.io.Serializable;

public class Note implements Serializable {

    private String time;
    private String title;
    private String description;

    private String subtitle;



    public Note(String time, String title, String description){
        this.time=time;
        this.title=title;
        this.description=description;
    }

    public Note(String title,String description){
        this.title=title;
        this.description=description;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}


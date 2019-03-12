package com.example.journal;

import java.io.Serializable;

public class Entry implements Serializable {
    private String title, content, datatime;
    private int id, mood;

    public Entry(int id, String title, String content, String datetime, int mood){
        this.title = title;
        this.content  = content;
        this.datatime = datetime;
        this.id = id;
        this.mood = mood;
    }

    public int getId() { return id; }
    public int getMood() { return mood; }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getDatatime() { return datatime;}

    public void setMood(int mood) { this.mood = mood; }
    public void setContent(String content) {
        this.content = content;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}


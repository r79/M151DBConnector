package io.r79.mp151_projekt.dto;

import java.io.Serializable;
import java.util.Date;

public class PerformanceDTO implements Serializable {
    private int id;
    private Date date;
    private String room;
    private String title;
    private String titleLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public PerformanceDTO(int id, Date date, String room, String title, String titleLink) {

        this.id = id;
        this.date = date;
        this.room = room;
        this.title = title;
        this.titleLink = titleLink;
    }
}

package io.r79.mp151_projekt.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class PerformanceDTO implements Serializable {
    final static long serialVersionUID = 1337;
    private int id;
    private Date date;
    private String room;
    private String title;
    private String titleLink;

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlAttribute
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @XmlAttribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute
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

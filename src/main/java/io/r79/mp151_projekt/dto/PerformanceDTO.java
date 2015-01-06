package io.r79.mp151_projekt.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="performance")
public class PerformanceDTO implements Serializable {
    final static long serialVersionUID = 1337;
    private int id;
    private long date;
    private String room;
    private String title;
    private String titleLink;

    public PerformanceDTO() {
        //empty stub
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @XmlElement
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getTitleLink() {
        return titleLink;
    }

    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public PerformanceDTO(int id, long date, String room, String title, String titleLink) {

        this.id = id;
        this.date = date;
        this.room = room;
        this.title = title;
        this.titleLink = titleLink;
    }
}

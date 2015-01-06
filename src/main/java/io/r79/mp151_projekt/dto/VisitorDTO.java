package io.r79.mp151_projekt.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class VisitorDTO implements Serializable {
    final static long serialVersionUID = 1338;
    private int id;
    private String name;
    private String prename;
    private String phone;

    public VisitorDTO() {
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    @XmlElement
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public VisitorDTO(int id, String name, String prename, String phone) {

        this.id = id;
        this.name = name;
        this.prename = prename;
        this.phone = phone;
    }
}

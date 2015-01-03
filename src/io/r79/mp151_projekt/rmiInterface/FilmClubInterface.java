package io.r79.mp151_projekt.rmiInterface;

import io.r79.mp151_projekt.dto.PerformanceDTO;
import io.r79.mp151_projekt.dto.VisitorDTO;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

public interface FilmClubInterface extends Remote {
    public ArrayList<PerformanceDTO> getPerformances(Date start, Date end);
    public ArrayList<VisitorDTO> getVisitors(int id);
}

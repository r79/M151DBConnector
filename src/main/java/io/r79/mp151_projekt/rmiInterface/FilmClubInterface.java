package io.r79.mp151_projekt.rmiInterface;

import io.r79.mp151_projekt.dto.PerformanceDTO;
import io.r79.mp151_projekt.dto.VisitorDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public interface FilmClubInterface extends Remote {
    public ArrayList<PerformanceDTO> getPerformances(Date start, Date end) throws RemoteException;
    public ArrayList<VisitorDTO> getVisitors(int id) throws RemoteException;
}

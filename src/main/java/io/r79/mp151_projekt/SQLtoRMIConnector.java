package io.r79.mp151_projekt;

import io.r79.mp151_projekt.dao.ImdbUtils;
import io.r79.mp151_projekt.dao.MySQLDAO;
import io.r79.mp151_projekt.dto.PerformanceDTO;
import io.r79.mp151_projekt.dto.VisitorDTO;
import io.r79.mp151_projekt.rmiInterface.FilmClubInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rbuechle on 03.12.2014.
 */
public class SQLtoRMIConnector extends UnicastRemoteObject implements FilmClubInterface {
    private MySQLDAO dao = new MySQLDAO("url");

    public SQLtoRMIConnector() throws RemoteException {
    }

    @Override
    public ArrayList<PerformanceDTO> getPerformances(Date start, Date end) throws RemoteException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

		System.out.println("incoming Request | Performances | start= " + sdf.format(start) + " | end= " + sdf.format(end) + " |");

		ArrayList<PerformanceDTO> performances = dao.getPerformancesInRange(start, end);

		for(PerformanceDTO performance : performances) {
			performance.setTitleLink(ImdbUtils.receiveImdbLink(performance.getTitle()));
		}

		System.out.println("responded " + performances.size() + " Performances");

		return performances;
    }

    @Override
    public ArrayList<VisitorDTO> getVisitors(int id) throws RemoteException {

		System.out.println("incoming Request | Visitors | id= " + id + " |");

		ArrayList<VisitorDTO> visitors = dao.getVisitors(id);

		System.out.println("responded " + visitors.size() + " Visitors");

        return visitors;

    }
    
    public static void main(String[] args) {
		try
		{
			try {
				LocateRegistry.createRegistry(1099);
			} catch (RemoteException e) {
				System.out.println("Could not start Registry");
			}
			
			SQLtoRMIConnector impl = new SQLtoRMIConnector();
			
			String SQLService = "SQLService";
			Naming.rebind("//localhost/" + SQLService, impl);
		}
		catch(Exception exc) {
			exc.printStackTrace();
			System.exit(1);
		}
		System.out.println("Server started successfully.");
	}
}

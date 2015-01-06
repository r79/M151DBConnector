package io.r79.mp151_projekt.dao;

import io.r79.mp151_projekt.dto.PerformanceDTO;
import io.r79.mp151_projekt.dto.VisitorDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rbuechle on 03.12.2014.
 */
public class MySQLDAO {

    private Connection connection = null;

    public MySQLDAO(String url) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filmclub", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PerformanceDTO> getPerformancesInRange(Date start, Date end) {
        ArrayList<PerformanceDTO> performances = new ArrayList<PerformanceDTO>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    String.format("SELECT veranstaltung.VeranstaltungID, UNIX_TIMESTAMP(veranstaltung.Datum), raum.Bezeichnung, film.Titel " +
                            "FROM veranstaltung, raum, film " +
                            "WHERE veranstaltung.FilmIDFS = film.FilmID " +
                            "AND %s < UNIX_TIMESTAMP(veranstaltung.datum) " +
                            "AND UNIX_TIMESTAMP(veranstaltung.datum) < %s " +
                            "AND veranstaltung.raumIDFS = raum.RaumID " +
                            "ORDER BY veranstaltung.Datum;", start.getTime()/1000, end.getTime()/1000));
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                performances.add(new PerformanceDTO(rs.getInt(1), rs.getLong(2)*1000, rs.getString(3), rs.getString(4), ""));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return performances;
    }

    public ArrayList<VisitorDTO> getVisitors(int performanceId) {
        ArrayList<VisitorDTO> visitors = new ArrayList<VisitorDTO>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    String.format("SELECT mitglied.PersID, mitglied.Vorname, mitglied.Name, mitglied.Privattelefon " +
                            "FROM mitglied, besuch " +
                            "WHERE besuch.VeranstaltungID =%d " +
                            "AND besuch.mitgliedID = mitglied.PersID " +
                            "ORDER BY mitglied.Vorname;", performanceId)
            );
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                visitors.add(new VisitorDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return visitors;
    }


    public static void main(char[] args) {

    }
}

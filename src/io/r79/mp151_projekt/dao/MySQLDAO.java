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
            connection = DriverManager.getConnection("jdbc:mysql:/asdf.io");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PerformanceDTO> getPerformancesInRange(Date start, Date end) {
        ArrayList<PerformanceDTO> performances = new ArrayList<PerformanceDTO>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT veranstaltung.VeranstaltungsID, veranstaltung.Datum, raum.Bezeichnung, film.Titel " +
                    "FROM veranstaltung, raum, film " +
                    "WHERE " + start + " < veranstaltung.Datum" +
                    "AND veranstaltung.Datum < " + end +
                    "AND veranstaltung.FilmIDFS = film.FilmID" +
                    "AND veranstaltung.raumIDFS = raum.RaumID");
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                performances.add(new PerformanceDTO(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), ""));
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
                    "SELECT mitglied.PersID, mitglied.Vorname, mitglied.Name, mitglied.Privattelefon" +
                    "FROM mitglied, besuch" +
                    "WHERE besuch.VeranstaltungsID = " + performanceId +
                    "AND besuch.mitgliedID = mitglied.PersID"
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

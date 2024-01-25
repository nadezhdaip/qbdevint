package com.quickbase.devint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

/**
 * This DBManager implementation provides a connection to the database
 * containing population data.
 *
 * Created by ckeswani on 9/16/15.
 */
public class DBManager implements IDBManager {
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:resources/data/citystatecountry.db");
            System.out.println("Opened database successfully");

        } catch (ClassNotFoundException cnfe) {
            System.out.println("could not load driver" + cnfe.toString());
        } catch (SQLException sqle) {
            System.out.println("sql exception:" + sqle.getStackTrace());
        }
        return connection;
    }

    public List<Pair<String, Integer>> GetCountryPopulations(){
        String sqlQueryGetPopulation = 
                "SELECT CountryName, SUM(Population) AS CountryPopulation FROM City\r\n" + //
                "JOIN State as s\r\n" + //
                "ON City.StateId = s.StateId\r\n" + //
                "JOIN COUNTRY\r\n" + //
                "ON COUNTRY.CountryId = s.CountryId\r\n" + //
                "GROUP BY Country.CountryId, Country.CountryName;";

        List<Pair<String, Integer>> dbCountryPopulation = new ArrayList<Pair<String, Integer>>();
        try (Statement stmt = this.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlQueryGetPopulation);
            while (rs.next()) {
                String country = rs.getString("CountryName");
                int population = rs.getInt("CountryPopulation");
                dbCountryPopulation.add(Pair.of(country, population));
            }
        } catch (SQLException sqle) {
            System.out.println("Cannot get population from database: sql exception:" + sqle.getStackTrace());
        }

        return dbCountryPopulation;
    }
}

package com.quickbase;

import com.quickbase.devint.ConcreteStatService;
import com.quickbase.devint.CountryPopulationService;
import com.quickbase.devint.DBManager;
import com.quickbase.devint.DBStatService;
import com.quickbase.devint.ICountryPopulationService;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

/**
 * The main method of the executable JAR generated from this repository. This is
 * to let you
 * execute something from the command-line or IDE for the purposes of
 * demonstration, but you can choose
 * to demonstrate in a different way (e.g. if you're using a framework)
 */
public class Main {
    //public static void main(String args[]) {
    //    System.out.println("Starting.");
    //    System.out.print("Getting DB Connection...");
//
    //    DBManager dbm = new DBManagerImpl();
    //    Connection c = dbm.getConnection();
    //    if (null == c) {
    //        System.out.println("failed.");
    //        System.exit(1);
    //    }
//
    //    Hashtable<String, Integer> countryPopulation = new Hashtable<>();
    //    List<Pair<String, Integer>> dbRecords = new DBManagerImpl().GetCountryPopulations();
    //    dbRecords.forEach((entry) -> {
    //        countryPopulation.put(entry.getKey(), entry.getValue());
    //    });
//
    //    List<Pair<String, Integer>> statRecords = new ConcreteStatService().GetCountryPopulations();
    //    statRecords.forEach(pair -> {
    //        // in case of duplicates the db record has a precedence
    //        countryPopulation.putIfAbsent(pair.getKey(), pair.getValue());
    //    });
//
    //    System.out.println(dbRecords);
    //    System.out.println(countryPopulation);
    //}

    public static void main(String args[]) {
        ICountryPopulationService countryPopulationService = new CountryPopulationService();
        countryPopulationService.RegisterStatService(new DBStatService(new DBManager()));
        countryPopulationService.RegisterStatService(new ConcreteStatService());

        List<Pair<String, Integer>> population = countryPopulationService.GetPopulation();
        System.out.println(population);

    }
}
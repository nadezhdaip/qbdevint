package com.quickbase;

import com.quickbase.devint.ConcreteStatService;
import com.quickbase.devint.CountryPopulationService;
import com.quickbase.devint.DBManager;
import com.quickbase.devint.DBStatService;
import com.quickbase.devint.ICountryPopulationService;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class Main {
    public static void main(String args[]) {
        ICountryPopulationService countryPopulationService = new CountryPopulationService();
        countryPopulationService.RegisterStatService(new DBStatService(new DBManager()));
        countryPopulationService.RegisterStatService(new ConcreteStatService());

        List<Pair<String, Integer>> population = countryPopulationService.GetPopulation();
        population.forEach(System.out::println);
    }
}
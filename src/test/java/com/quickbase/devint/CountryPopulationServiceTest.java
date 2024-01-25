package com.quickbase.devint;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class CountryPopulationServiceTest {

    @Test
    public void GetPopulations_should_return_List_from_stat_service() {
        // Arrange
        CountryPopulationService service = new CountryPopulationService();
        service.RegisterStatService(new IStatService() {
            @Override
            public List<Pair<String, Integer>> GetCountryPopulations() {
                ArrayList<Pair<String, Integer>> list = new ArrayList<Pair<String, Integer>>();
                list.add(new ImmutablePair<String,Integer>("USA", 555));
                list.add(new ImmutablePair<String,Integer>("BG", 100));
                return list;
            }
        });

        // Act
        List<Pair<String, Integer>> result = service.GetPopulation();

        // Assert
        assertEquals(result.size(), 2);
    }

    @Test
    public void When_2_services_are_registered_the_first_has_precedence() {
        // Arrange
        CountryPopulationService service = new CountryPopulationService();
        service.RegisterStatService(new IStatService() {
            @Override
            public List<Pair<String, Integer>> GetCountryPopulations() {
                ArrayList<Pair<String, Integer>> list = new ArrayList<Pair<String, Integer>>();
                list.add(new ImmutablePair<String,Integer>("USA", 555));
                return list;
            }
        });

        // register the second service
        service.RegisterStatService(new IStatService() {
            @Override
            public List<Pair<String, Integer>> GetCountryPopulations() {
                ArrayList<Pair<String, Integer>> list = new ArrayList<Pair<String, Integer>>();
                list.add(new ImmutablePair<String,Integer>("USA", 111));
                return list;
            }
        });

        // Act
        List<Pair<String, Integer>> result = service.GetPopulation();

        // Assert
        assertEquals(result.size(), 1);
        int value = result.get(0).getValue();
        assertEquals(value , 555);
    }
}
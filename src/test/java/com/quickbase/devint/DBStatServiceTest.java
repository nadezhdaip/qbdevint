package com.quickbase.devint;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class DBStatServiceTest {
    @Test
    public void GetCountryPopulations_should_return_result_from_DBManager() {
        // Arrange
        DBStatService service = new DBStatService(new IDBManager() {

            @Override
            public Connection getConnection() {
                throw new UnsupportedOperationException("Unimplemented method 'getConnection'");
            }

            @Override
            public List<Pair<String, Integer>> GetCountryPopulations() {
                ArrayList<Pair<String, Integer>> list = new ArrayList<Pair<String, Integer>>();
                list.add(new ImmutablePair<String,Integer>("USA", 555));

                return list;
            }
        });

        // Act
        List<Pair<String, Integer>> population = service.GetCountryPopulations();

        // Assert
        assertEquals(population.size(), 1);
        assertEquals(population.get(0).getKey(), "USA");
        assertEquals(population.get(0).getValue(), (Integer)555);
    }
}

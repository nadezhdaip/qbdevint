package com.quickbase.devint;

import static org.junit.Assert.assertTrue;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

import org.junit.Test;

public class DBManagerTest {
    @Test
    public void testGetCountryPopulations_returns_results() {
        // Arrange
        DBManager dbManager = new DBManager();

        // Act
        List<Pair<String, Integer>> results = dbManager.GetCountryPopulations();

        // Assert
        assertTrue(results.size() > 0);
    }
}

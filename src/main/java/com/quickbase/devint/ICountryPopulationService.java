package com.quickbase.devint;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public interface ICountryPopulationService {
    public void RegisterStatService(IStatService service);
    public List<Pair<String, Integer>> GetPopulation();
}

package com.quickbase.devint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;


public class CountryPopulationService implements ICountryPopulationService {
    private ArrayList<IStatService> _statServices = new ArrayList<IStatService>();

    @Override
    public void RegisterStatService(IStatService service) {
        _statServices.add(service);
    }

    @Override
    public List<Pair<String, Integer>> GetPopulation() {
        
        HashMap<String, Integer> countryPopulation = new HashMap<>();

        for (IStatService statService : _statServices) {
            List<Pair<String, Integer>> statRecords = statService.GetCountryPopulations(); 
            statRecords.forEach(pair -> {
                // in case of duplicates the first record has a precedence
                countryPopulation.putIfAbsent(pair.getKey(), pair.getValue());
            });
        }

        List<Pair<String, Integer>> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : countryPopulation.entrySet()) {
            result.add(new ImmutablePair<String, Integer>(entry.getKey(), entry.getValue()));
        }

        return result;
    }
}

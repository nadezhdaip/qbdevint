package com.quickbase.devint;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class DBStatService implements IStatService {

    private IDBManager _dbManager;

    public DBStatService(IDBManager dbManager) {
        _dbManager = dbManager;
    }
    
    @Override
	public List<Pair<String, Integer>> GetCountryPopulations() {
        return _dbManager.GetCountryPopulations();
    }
}
package com.frauas.scs.covidapp.repo;

import com.frauas.scs.covidapp.models.Area;
import com.frauas.scs.covidapp.models.JsonDb;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CreatedBy : edangol
 * CreatedOn : 22/06/2020
 * Description :
 **/
@Repository
public class CovidDataRepo {
    private Map<String, Area> data1 = new ConcurrentHashMap<>();
    private List<JsonDb> data = new ArrayList<>();

    public CovidDataRepo() {

    }

    public List<Area> getData1() {
        Collection<Area> d = data1.values();
        return new ArrayList<>(d);
    }

    public List<JsonDb> getData() {
        return data;
    }

    public void setData(List<JsonDb> data) {
        this.data = data;
    }
}

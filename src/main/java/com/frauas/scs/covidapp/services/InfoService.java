package com.frauas.scs.covidapp.services;

import com.frauas.scs.covidapp.models.JsonDb;
import com.frauas.scs.covidapp.repo.CovidDataRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CreatedBy : edangol
 * CreatedOn : 02/07/2020
 * Description :
 **/
@Service
public class InfoService {
    private CovidDataRepo dataRepo;

    public InfoService(CovidDataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    public List<JsonDb> getAreaInfo() {
        return this.dataRepo.getData();
    }
}

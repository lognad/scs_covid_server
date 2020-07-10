package com.frauas.scs.covidapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frauas.scs.covidapp.models.JsonDb;
import com.frauas.scs.covidapp.repo.CovidDataRepo;
import com.google.api.services.storage.Storage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * CreatedBy : edangol
 * CreatedOn : 22/06/2020
 * Description :
 **/
@Service
public class UpdaterService {

    CovidDataRepo covidDataRepo;
    private ObjectMapper mapper = new ObjectMapper();

    public UpdaterService(CovidDataRepo covidDataRepo) {
        this.covidDataRepo = covidDataRepo;
    }

    @Scheduled(fixedRate = 1000L * 20, initialDelay = 1000L)
    private void checkForUpdates() {
        System.out.println("checking for updates\t: " + new Date().toString());
        this.readData();
    }

    private void readData() {
        List<JsonDb> data = null;
        try {
            data = Arrays.asList(mapper.readValue(Paths.get("areaInfo.json").toFile(), JsonDb[].class));

            // todo: check for data timestamp if same data no need to update.
            this.covidDataRepo.setData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

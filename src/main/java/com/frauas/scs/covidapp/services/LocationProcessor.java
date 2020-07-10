package com.frauas.scs.covidapp.services;

import com.frauas.scs.covidapp.helpers.LocationHelper;
import com.frauas.scs.covidapp.models.Area;
import com.frauas.scs.covidapp.models.Coordinates;
import com.frauas.scs.covidapp.models.Subscriber;
import com.frauas.scs.covidapp.repo.CovidDataRepo;
import com.frauas.scs.covidapp.repo.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * CreatedBy : edangol
 * CreatedOn : 22/06/2020
 * Description :
 **/
@Service
public class LocationProcessor {
    private CovidDataRepo dataRepo;
    private FCMClient fcmClient;

    @Autowired
    public LocationProcessor(CovidDataRepo dataRepo, FCMClient fcmClient, RegisterRepo registerRepo) {
        this.dataRepo = dataRepo;
        this.fcmClient = fcmClient;
    }

    public void process(Subscriber s) {
        if (isInsideAffectedArea(s.getLocation())) {

            Map<String, String> data = new HashMap<>();
            data.put("title", "Your are in the affected area. Please leave as soon as possible for your safety");
            data.put("title1", "hey there");

            try {
                fcmClient.sendPersonalMessage(s.getToken(), data);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error while sending msg for : " + s.toString());
                e.printStackTrace();
            }
        }
    }

    private boolean isInsideAffectedArea(Coordinates location) {
        List<Area> areaList = dataRepo.getData1();
        Optional<Area> result = areaList.stream()
                .filter(
                        x -> LocationHelper.isInside(
                                x.getBoundaries().toArray(new Coordinates[x.getBoundaries().size()])
                                , x.getBoundaries().size()
                                , location)
                ).findFirst();

//        return result.isPresent();
        return false;
    }


}

package com.frauas.scs.covidapp.api;

import com.frauas.scs.covidapp.models.Subscriber;
import com.frauas.scs.covidapp.services.ClientRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CreatedBy : edangol
 * CreatedOn : 22/06/2020
 * Description :
 **/
@RestController
public class LocationController {
    private ClientRegistry registry;

    public LocationController(ClientRegistry registry) {
        this.registry = registry;
    }

    @PostMapping("/position")
    Subscriber updateLocation(@RequestBody Subscriber s) {
        System.out.println("LOCATION RECIEVED: " + s.toString());
        registry.saveLocation(s);
        return s;
    }
}

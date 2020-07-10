package com.frauas.scs.covidapp.configs;

import com.frauas.scs.covidapp.models.Area;
import com.frauas.scs.covidapp.services.FCMClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * CreatedBy : edangol
 * CreatedOn : 22/06/2020
 * Description :
 **/
@Configuration
public class Provider {

    @Bean
    public Area getArea() {
        Area a = new Area();
        a.setBoundaries(new ArrayList<>());
        return a;
    }
}

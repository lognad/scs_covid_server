package com.frauas.scs.covidapp.api;

import com.frauas.scs.covidapp.models.JsonDb;
import com.frauas.scs.covidapp.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CreatedBy : edangol
 * CreatedOn : 02/07/2020
 * Description :
 **/
@RestController
public class DataController {

    final InfoService infoService;

    public DataController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/data")
    List<JsonDb> getAreaInfo() {
        return this.infoService.getAreaInfo();
    }
}

package com.frauas.scs.covidapp.models;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * CreatedBy : edangol
 * CreatedOn : 22/06/2020
 * Description :
 **/
@Data
@ToString
public class Area implements Serializable {
    private int id;
    private String name;
    private Info data;
    private List<Coordinates> boundaries;
}

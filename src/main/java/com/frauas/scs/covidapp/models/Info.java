package com.frauas.scs.covidapp.models;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * CreatedBy : edangol
 * CreatedOn : 22/06/2020
 * Description :
 **/
@Data
@ToString
public class Info implements Serializable {
    private int total;
    private int active;
    private int cured;
    private int critical;
    private int deaths;
    private String areaId;
}

package com.frauas.scs.covidapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * CreatedBy : edangol
 * CreatedOn : 22/06/2020
 * Description :
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates implements Serializable {
    private double lat;
    private double lng;
}

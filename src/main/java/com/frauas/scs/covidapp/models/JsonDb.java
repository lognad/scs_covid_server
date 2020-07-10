package com.frauas.scs.covidapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * CreatedBy : edangol
 * CreatedOn : 02/07/2020
 * Description :
 **/
@Data
@ToString
@JsonIgnoreProperties
public class JsonDb implements Serializable {
    private String id;
    private String name;
    private List<Info> data;
    private Date timestamp;
}

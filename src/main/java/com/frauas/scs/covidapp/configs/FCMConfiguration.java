package com.frauas.scs.covidapp.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * CreatedBy : edangol
 * CreatedOn : 09/06/2020
 * Description :
 **/
@ConfigurationProperties(prefix = "fcm")
@Component
@Data
public class FCMConfiguration {
    private String serviceAccountFile;

}

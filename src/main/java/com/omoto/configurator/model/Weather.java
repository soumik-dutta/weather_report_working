package com.omoto.configurator.model;

import lombok.Data;
import org.hibernate.annotations.Columns;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by soumik on 22/12/16.
 */
@Data
@Entity
@Component
@Table(name = "WEATHER")
public class Weather {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;


    @NotNull
    @Size(min=2, max=40)
    @Column(name = "LOCATION")
    private String location;

    @NotNull
    @Column(name="AVG_TEMP")
    private Float avgTemp;

    @NotNull
    @Column(name = "MAX_TEMP")
    private Float maxTemp;

    @NotNull
    @Column(name="MIN_TEMP")
    private Float minTemp;

    @Column(name="CURRENTTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date currentTime;

}

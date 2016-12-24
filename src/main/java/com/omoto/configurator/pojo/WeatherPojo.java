package com.omoto.configurator.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by omoto on 23/12/16.
 */

@Data
@Component
public class WeatherPojo {

    private float temp;
    private int pressure;
    private int humidity;
    private float temp_min;
    private float temp_max;
}

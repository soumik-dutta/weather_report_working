package com.omoto.configurator.service;

import com.omoto.configurator.model.Weather;

import java.util.Collection;

/**
 * Created by omoto on 23/12/16.
 */
public interface WeatherRepositoryService {

    public Collection<Weather> dailyAsOfNow(String location);
}

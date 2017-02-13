package com.omoto.weather.service.Impl;

import com.omoto.weather.model.Weather;
import com.omoto.weather.repository.WeatherRepository;
import com.omoto.weather.service.WeatherRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Created by omoto on 23/12/16.
 */
@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherRepositoryServiceImpl implements WeatherRepositoryService {

    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherRepositoryServiceImpl(WeatherRepository weatherRepository){
        Assert.notNull(weatherRepository,"WeatherRepository is null");
        this.weatherRepository=weatherRepository;
    }


    /**
     * Get todays Time with temparature
     * @param location
     * @param response
     * @return
     */
    @RequestMapping(
            value = "/getdaily",
            params = {"location"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Weather>> updateProfileData(@RequestParam("location") String location, HttpServletResponse response) {
        log.info("UserRepositoryServiceImpl.updateProfileData");
        Assert.notNull(location, "user is a required attribute!");
        Collection<Weather> result = weatherRepository.todaysForcast(location);
        return new ResponseEntity<Collection<Weather>>(result,
                HttpStatus.OK);
    }





    @Override
    public Collection<Weather> dailyAsOfNow(String location) {
        return null;
    }
}

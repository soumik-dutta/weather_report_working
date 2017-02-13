package com.omoto.weather.batch;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.omoto.weather.model.Weather;
import com.omoto.weather.pojo.WeatherPojo;
import com.omoto.weather.repository.WeatherRepository;
import com.omoto.weather.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;


/**
 * Created by soumik on 22/12/16.
 */


@Controller
public class WeatherUpdateBatch {

    @Autowired
    WeatherPojo weatherPojo;
    @Autowired
    WeatherRepository weatherRepository;


    /**
     * Scheduler that will run hourly and bring all the 
     */
    @Scheduled(cron = "1 */1 * * * ?")
    public void hourlyWeatherUpdate(){
        ObjectMapper objectMapper=new ObjectMapper();
        String responseJson=new CommonUtils().getJson("http://api.openweathermap.org/data/2.5/weather?q=Bangalore,IN&appid=dfef04093fa06a7ddb963979e8c48341");
        Map<String,Object> map=new BasicJsonParser().parseMap(responseJson);
        for(Map.Entry<String, Object> entry : map.entrySet()){
            if(entry.getKey().equalsIgnoreCase("main")){
                try {
                    String json =(objectMapper.writer().withDefaultPrettyPrinter()).writeValueAsString(entry.getValue());
                    weatherPojo=objectMapper.readValue(json,WeatherPojo.class);
                    Weather weather=new Weather();
                    weather.setLocation("Bangalore");
                    weather.setCurrentTime(new Date());
                    weather.setAvgTemp(weatherPojo.getTemp());
                    weather.setMaxTemp(weatherPojo.getTemp_max());
                    weather.setMinTemp(weatherPojo.getTemp_min());
                    weatherRepository.save(weather);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

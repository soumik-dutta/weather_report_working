package com.omoto.configurator.repository;

import com.omoto.configurator.model.Weather;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by omoto on 23/12/16.
 */
@Transactional
public interface WeatherRepository extends CrudRepository<Weather, Long> {

    @Query(value = "SELECT * FROM weather where LOCATION=:location and CAST(CURRENTTIME as date)  = '2016-12-23' ", nativeQuery = true)
    Collection<Weather> dailyAsOfNow(@Param("location") String location);


}

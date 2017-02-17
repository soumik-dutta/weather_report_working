package com.omoto.configurator.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by omoto on 17/2/17.
 */
@Data
@Component
public class StartMachineResponse {

    @Value("{$azure.username}")
    private String userName;
}

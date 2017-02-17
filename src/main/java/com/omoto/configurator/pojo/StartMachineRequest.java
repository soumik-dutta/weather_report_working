package com.omoto.configurator.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by omoto on 17/2/17.
 */
@Data
@Component
public class StartMachineRequest {
    private String vmId;
    private String machineName;
}

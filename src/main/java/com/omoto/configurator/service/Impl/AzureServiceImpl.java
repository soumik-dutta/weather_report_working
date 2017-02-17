package com.omoto.configurator.service.Impl;

import com.omoto.configurator.pojo.StartMachineRequest;
import com.omoto.configurator.pojo.StartMachineResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by omoto on 17/2/17.
 */
@RestController
@RequestMapping("/azure")
public class AzureServiceImpl {

    @RequestMapping(value = "/start", params = {""})
    public StartMachineResponse start(@RequestBody StartMachineRequest request) {


        return null;
    }


}

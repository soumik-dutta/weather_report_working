package com.omoto.configurator.service.Impl;

import com.microsoft.azure.management.compute.VirtualMachine;
import com.omoto.configurator.azure.CreateMachine;
import com.omoto.configurator.pojo.StartMachineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by omoto on 17/2/17.
 */
@RestController
@RequestMapping("/azure")
public class AzureServiceImpl {

    @Autowired
    private CreateMachine createMachine;

    @RequestMapping(value = "/start",
            params = {""},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VirtualMachine> start(@RequestBody StartMachineRequest request) {
        VirtualMachine virtualMachine = createMachine.createLinuxMachine();
        return new ResponseEntity<VirtualMachine>(virtualMachine, HttpStatus.OK);
    }


}

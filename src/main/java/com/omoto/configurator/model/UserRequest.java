package com.omoto.configurator.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by omoto on 16/12/16.
 */
@Data
@Component
public class UserRequest {

    private String userName;
    private String password;


}

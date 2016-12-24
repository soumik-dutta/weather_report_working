package com.omoto.configurator.service;

import com.omoto.configurator.model.User;

/*
 Created by omoto on 16/12/16.
 */
public interface UserRepositryService {

    User findByUser(String user);
}

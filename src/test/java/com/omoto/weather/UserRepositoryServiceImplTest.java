package com.omoto.weather;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryServiceImplTest implements UserSampleData {

    private static ArrayList<User> users = new ArrayList<User>();

    @Autowired
    private UserRepositryServiceImpl userRepositoryServiceImpl;


    @Test
    public void A_setUpBeforeClass() {
        log.info("setUpBeforeClass()");

        // Set the username to be something unique for this run. So searches don't collide with other



        // Insert some test data
        users = new ArrayList<User>();
        // No. of sample records to create
        int count = 1;
        for (int i = 0; i < count; i++) {
            User user = createSampleUser();
            users.add(user);
        }

        // Save the records created.
        for (User user  : users)
            userRepositoryServiceImpl.save(user);

        // Ensure save worked

        User ua = userRepositoryServiceImpl.findByUser(user);
        assertThat("User save on users did not work!",
                ua.getUserName(),
                is("testuser@omoto.com"));

    }

    @Test
    public void Z_tearDownAfterClass() {
        log.info("Z_tearDownAfterClass()");
        clean();
    }


    private User createSampleUser() {

        User userObj = new User();
        userObj.setUserName(user);
        userObj.setPassword(password);

        return userObj;
    }


    public void clean() {
        log.info("clean()");

        for (User user : users) {
            userRepositoryServiceImpl.delete(user);
        }
        // Ensure delete worked

        users.clear();
    }

}

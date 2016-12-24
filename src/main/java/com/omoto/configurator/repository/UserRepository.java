package com.omoto.configurator.repository;

import com.omoto.configurator.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by omoto on 16/12/16.
 */

//@RepositoryRestResource(collectionResourceRel = "User")
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("from User u WHERE u.userName = :user")
    User findByUser(@Param("user") String user);

}

package com.example.springbootlab.portal.resp;

import com.example.springbootlab.portal.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// @RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByName(@Param("name") String name);

    @Query(value = "{'_id':?0}",fields = "{'name':1,'_id':0}")
    User findUserById(String id);

}
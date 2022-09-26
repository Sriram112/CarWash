package com.casestudy.springsecurity.repository;

import com.casestudy.springsecurity.models.Roles;
import com.casestudy.springsecurity.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends MongoRepository<Users,String> {
    Users findByEmail(String email);

    List<Users> findByRolesIn(Set<Roles> roles);

}

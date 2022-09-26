package com.casestudy.springsecurity.repository;

import com.casestudy.springsecurity.models.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Roles,String> {
    Roles findByRole(String role);

}

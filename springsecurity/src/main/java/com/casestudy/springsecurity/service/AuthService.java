package com.casestudy.springsecurity.service;

import com.casestudy.springsecurity.exceptionHandler.API_RequestionException;
import com.casestudy.springsecurity.models.Roles;
import com.casestudy.springsecurity.models.Users;
import com.casestudy.springsecurity.repository.RoleRepository;
import com.casestudy.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    @Autowired
    private UserRepository ur;
    @Autowired
    private RoleRepository roleRepository;
    //To get all users from DB
    public List<Users> getAllUser(){
        return ur.findAll();
    }
    //find user by name
    public Users getSpecificUser(String name){
        return ur.findAll().stream().filter(x->x.getFullname().contains(name)).findFirst().get();
    }
    //to delete a user
    public ResponseEntity<Map<String,Boolean>> deleteUser(String id){
        Users user = ur.findById(id).orElseThrow(()->new API_RequestionException("User with Id -> "+id+" not found, deletion failed"));
        ur.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("User Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    //to find list of users with role
    public List<Users> findListByRole(String role){
        Roles r = roleRepository.findByRole(role);
        Set<Roles> roles = new HashSet<>();
        roles.add(r);
        return ur.findByRolesIn(roles);
    }
    public Users getWasher(String name){
        Roles r = roleRepository.findByRole("WASHER");
        Set<Roles> roles = new HashSet<>();
        roles.add(r);
        return ur.findByRolesIn(roles).stream().filter(x->x.getFullname().contains(name)).findFirst().orElseThrow(()->new API_RequestionException("Washer with name "+name+" not found"));
    }

}

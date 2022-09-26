package com.casestudy.springsecurity.controller;

import com.casestudy.springsecurity.models.Users;
import com.casestudy.springsecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage")
public class RepoController {
    @Autowired
    private AuthService as;
    @GetMapping("/all")
    public List<Users> getAllUsers(){
        return as.getAllUser();
    }
    @GetMapping("/UserByName/{name}")
    public Users getSpecificUser(@PathVariable String name){
        return as.getSpecificUser(name);
    }
    @GetMapping("/deleteUser/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable String id){
        return as.deleteUser(id);
    }
    @GetMapping("/users/{role}")
    public List<Users> getUserByRole(@PathVariable String role){
        return as.findListByRole(role);
    }
    @GetMapping("/washer/{name}")
    public Users getWasher(@PathVariable String name){
        return as.getWasher(name);
    }
}


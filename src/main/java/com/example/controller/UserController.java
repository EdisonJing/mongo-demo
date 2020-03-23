package com.example.controller;

import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping()
    public void saveUser(@RequestBody User user){
        System.out.println("=============");
        mongoTemplate.insert(user);
    }
}

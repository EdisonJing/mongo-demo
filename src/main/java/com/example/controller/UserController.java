package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public Integer saveUser(@RequestBody User user) {
        userService.save(user);
        return user.getId();
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) throws Exception {
        userService.updateById(user);
        return user;
    }

    @DeleteMapping()
    public void deleteUser(@RequestBody User user) throws Exception {
        userService.delete(user);
    }

    @PostMapping("page")
    public List<User> page(@RequestBody Map<String,Object> params, int pageNum, int size) throws Exception {
        return userService.page(params, pageNum, size);
    }

    @PostMapping("like")
    public List<User> like(@RequestBody User user){
        return userService.like(user);
    }

}

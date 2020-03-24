package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public Integer saveUser(@RequestBody User user){
        userService.save(user);
        return user.getId();
    }

    @PutMapping()
    public void updateUser(@RequestBody User user) throws Exception {
        userService.updateById(user);
    }

    @DeleteMapping()
    public void deleteUser(@RequestBody User user) throws Exception {
        userService.delete(user);
    }

    @GetMapping("page")
    public List<User> page(int pageNum, int size) throws Exception {
        return userService.page(pageNum,size);
    }

}

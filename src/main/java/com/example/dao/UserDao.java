package com.example.dao;

import com.example.entity.User;

import java.util.List;

public interface UserDao extends MongoBasicDao<User> {
    List<User> like(User user);
}

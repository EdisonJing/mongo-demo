package com.example.service;

import com.example.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    void save(User user);

    void delete(User user) throws Exception;

    void updateById(User user) throws Exception;

    List<User> page(Map<String, Object> params, int pageNum, int size) throws Exception;

    List<User> like(User user);
}

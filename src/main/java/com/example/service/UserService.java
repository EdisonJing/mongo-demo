package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    void delete(User user) throws Exception;

    void updateById(User user) throws Exception;

    List<User> page(int pageNum, int size)throws Exception;
}

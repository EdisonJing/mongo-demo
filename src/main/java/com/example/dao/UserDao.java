package com.example.dao;

import com.example.dao.impl.MongoBasicDaoImpl;
import com.example.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends MongoBasicDaoImpl<User> {

}
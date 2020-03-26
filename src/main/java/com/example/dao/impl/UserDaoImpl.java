package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.dao.impl.MongoBasicDaoImpl;
import com.example.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends MongoBasicDaoImpl<User> implements UserDao {

    @Override
    public List<User> like(User user) {
        Query query = new Query();
        Criteria criteria = new Criteria("name").regex(("^.*" + user.getName() + ".*$"));
        query.addCriteria(criteria);
        return mongoTemplate.find(query,User.class);
    }
}
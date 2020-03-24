package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) throws Exception {
        userDao.delete(user);
    }

    @Override
    public void updateById(User user) throws Exception {
        Query query = new Query(
                Criteria.where("id").is(user.getId())
        );
        userDao.update(query, user);
    }

    @Override
    public List<User> page(int pageNum, int size) throws Exception {
        return userDao.pagingSelete(pageNum, size);
    }
}

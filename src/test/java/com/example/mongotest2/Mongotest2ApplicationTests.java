package com.example.mongotest2;

import com.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
class Mongotest2ApplicationTests {
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void addUser() {
        User user = new User();
        user.setId(1);
        user.setName("凌康");
        user.setSex("男");
        user.setBirthday(LocalDate.now());
        mongoTemplate.insert(user);
        user.setId(2);
        user.setName("李白");
        user.setSex("男");
        user.setBirthday(LocalDate.now());
        mongoTemplate.insert(user);
        log.info("插入成功！");
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(1);
        mongoTemplate.remove(user);
        user.setId(2);
        mongoTemplate.remove(user);
        log.info("删除成功！");
    }

    @Test
    public void findAll() {
        List<User> list = mongoTemplate.findAll(User.class);
        for (User user : list) {
            log.info(user.toString());
        }
        log.info("查询成功！");
    }

    @Test
    public void findByDate() {
        Query query = new Query(
                Criteria.where("birthday").lte(LocalDate.of(2020,3,20))
        );

        List<User> list = mongoTemplate.find(query,User.class);
        for (User user : list) {
            log.info(user.toString());
        }
        log.info("查询成功！");
    }

    @Test
    public User getById(int id) {
        User user = mongoTemplate.findById(id == 0 ? 1 : id, User.class);
        log.info(user.toString());
        log.info("查询成功！");
        return user;
    }

    @Test
    public void updateUser() {
        User user = mongoTemplate.findById(1, User.class);

        mongoTemplate.updateFirst(query(where("name").is("凌康"))
                , Update.update("name", "我是凌康"), User.class);

        log.info(getById(1).toString());

        log.info("修改成功！");
    }

}

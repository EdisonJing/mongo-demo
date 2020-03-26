package com.example.dao;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

public interface MongoBasicDao<T> {
    void save(T obj);

    void delete(String key, String value);

    void update(String key, String value, T obj) throws Exception;

    void update(Query query, T o) throws IllegalAccessException, Exception;

    T singleTableSelete(String key, String value);

    List<T> pageSelete(Integer pageIndex, Integer pageSize);

    List<T> pageSelete(Map<String, Object> params, Integer pageIndex, Integer pageSize);

    List<T> compositeSelete();

    void delete(T o) throws Exception;
}

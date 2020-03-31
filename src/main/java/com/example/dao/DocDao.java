package com.example.dao;

import com.example.entity.Doc;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface DocDao extends MongoBasicDao<Doc>{
    Doc getOne(Query query);

    List<Doc> list(Query query);
}

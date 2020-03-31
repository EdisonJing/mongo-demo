package com.example.dao.impl;

import com.example.dao.DocDao;
import com.example.entity.Doc;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocDaoImpl extends MongoBasicDaoImpl<Doc> implements DocDao {


    @Override
    public Doc getOne(Query query) {
        return mongoTemplate.findOne(query,Doc.class);
    }

    @Override
    public List<Doc> list(Query query) {
        return mongoTemplate.find(query,Doc.class);
    }
}

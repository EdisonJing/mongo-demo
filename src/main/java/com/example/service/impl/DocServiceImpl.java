package com.example.service.impl;

import com.example.dao.DocDao;
import com.example.entity.Doc;
import com.example.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class DocServiceImpl implements DocService {

    @Autowired
    DocDao docDao;

    @Override
    public void save(Doc doc) {
        docDao.save(doc);
    }

    @Override
    public void updateById(Doc doc) throws Exception {
        Query query = new Query(
                Criteria.where("id").is(doc.getId())
        );
        docDao.update(query, doc);
    }

    @Override
    public void delete(Doc doc) throws Exception {
        docDao.delete(doc);
    }

    @Override
    public List<Doc> page(Map<String, Object> params, int pageNum, int size) {
        return null;
    }

    @Override
    public List<Doc> like(Doc doc) {
        return null;
    }

    @Override
    public Doc getOne(Doc doc) {
        Query query = new Query(
                Criteria.where("mzh").is(doc.getMzh())
                        .and("type").is(doc.getType())
        );
        Sort sort = Sort.by("version").descending();
        query.with(sort);

        return docDao.getOne(query);
    }

    @Override
    public List<Doc> list(Doc doc) {
        Query query = new Query(
                Criteria.where("mzh").is(doc.getMzh())
                        .and("type").is(doc.getType())
        );
        Sort sort = Sort.by("version").ascending();
        query.with(sort);
        return docDao.list(query);
    }

    @Override
    public List<Doc> between(LocalDate begin, LocalDate end) {

        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("birthday").gte(begin),
                Criteria.where("birthday").lt(end));

        Query query = new Query(criteria);
        return docDao.list(query);
    }
}

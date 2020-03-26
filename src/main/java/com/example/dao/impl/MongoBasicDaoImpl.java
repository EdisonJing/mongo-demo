package com.example.dao.impl;

import com.example.dao.MongoBasicDao;
import com.example.utils.MyClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public abstract class MongoBasicDaoImpl<T> implements MongoBasicDao<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Class<T> obj;

    /**
     * 获取T的实际类型
     */
    protected MongoBasicDaoImpl() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        this.obj = (Class<T>) trueType;
    }

    @Override
    public void save(Object obj) {
        // TODO Auto-generated method stub
        mongoTemplate.save(obj);
    }

    @Override
    public void delete(String key, String value) {
        // TODO Auto-generated method stub
        Query query = new Query(Criteria.where(key).is(value));
        mongoTemplate.remove(query, obj);
    }

    @Override
    public void delete(T o) throws Exception {
        Query query = new Query(Criteria.where("id").is(MyClassUtils.getFieldValueByName("id", o)));
        mongoTemplate.remove(query, obj);
    }


    @Override
    public void update(Query query,T o) throws Exception {
        Update update = new Update();
        String[] field = MyClassUtils.getFieldName(o);

        for (int j = 0; j < field.length; j++) {
            Object v = MyClassUtils.getFieldValueByName(field[j], o);
            update.set(field[j], v);
        }
        mongoTemplate.updateFirst(query, update, obj);
    }

    @Override
    public void update(String key, String value, Object o) throws Exception {
        Query query = new Query(Criteria.where(key).is(value));
        Update update = new Update();
        String[] field = MyClassUtils.getFieldName(o);

        for (int j = 0; j < field.length; j++) {
            Object v = MyClassUtils.getFieldValueByName(field[j], o);
            //System.out.println(field[j]+"---"+v);
            update.set(field[j], v);
        }
        mongoTemplate.updateFirst(query, update, obj);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T singleTableSelete(String key, String value) {
        // TODO Auto-generated method stub
        Query query = new Query(Criteria.where(key).is(value));
        Type t = this.getClass().getGenericSuperclass();
        return (T) mongoTemplate.findOne(query, obj);
    }

    @Override
    public List<T> pageSelete(Integer pageIndex, Integer pageSize) {
        // TODO Auto-generated method stub
        Query query = new Query();
        //Pageable pageable = new PageRequest(pageIndex ,pageSize);
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        query.with(pageable);
        return (List<T>) mongoTemplate.find(query, obj);
    }

    @Override
    public List<T> pageSelete(Map<String, Object> params, Integer pageIndex, Integer pageSize) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        params.forEach((k,v)->{
            criteria.where(k).is(v);
        });
        query.addCriteria(criteria);
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        query.with(pageable);
        return (List<T>) mongoTemplate.find(query, obj);
    }

    @Override
    public List compositeSelete() {
        // TODO Auto-generated method stub
        return null;
    }
}

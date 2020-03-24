package com.example.dao;

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

public abstract class MongoBasicsDAOImpl<T> implements MongoBasicDao<T> {

    @Autowired

    private MongoTemplate mongoTemplate;


    private Class<T> obj;

    /**
     * 获取T的实际类型
     */


    protected MongoBasicsDAOImpl() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        this.obj = (Class<T>) trueType;
    }


    @Override


    public void mongoSave(Object obj) {
        // TODO Auto-generated method stub
        mongoTemplate.save(obj);
    }


    @Override


    public void mongoDelete(String key, String value) {
        // TODO Auto-generated method stub
        Query query = new Query(Criteria.where(key).is(value));
        mongoTemplate.remove(query, obj);
    }


    @Override
    public void mongoUpdate(String key, String value, Object o) throws Exception {
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
    public T mongoSingleTableSelete(String key, String value) {
        // TODO Auto-generated method stub
        Query query = new Query(Criteria.where(key).is(value));
        Type t = this.getClass().getGenericSuperclass();
        return (T) mongoTemplate.findOne(query, obj);
    }


    @Override
    public List<T> mongoPagingSelete(Integer pageIndex, Integer pageSize) {
        // TODO Auto-generated method stub
        Query query = new Query();
        //Pageable pageable = new PageRequest(pageIndex ,pageSize);
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        query.with(pageable);
        return (List<T>) mongoTemplate.find(query, obj);
    }


    @Override
    public List mongoCompositeSelete() {
        // TODO Auto-generated method stub
        return null;
    }
}

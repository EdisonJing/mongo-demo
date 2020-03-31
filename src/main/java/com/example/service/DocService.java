package com.example.service;

import com.example.entity.Doc;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DocService {
    void save(Doc doc);

    void updateById(Doc doc) throws Exception;

    void delete(Doc doc) throws Exception;

    List<Doc> page(Map<String,Object> params, int pageNum, int size);

    List<Doc> like(Doc doc);

    Doc getOne(Doc doc);

    List<Doc> list(Doc doc);

    List<Doc> between(LocalDate begin, LocalDate end);
}

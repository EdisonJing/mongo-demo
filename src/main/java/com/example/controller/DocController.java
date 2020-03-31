package com.example.controller;

import com.example.entity.Doc;
import com.example.entity.DocVo;
import com.example.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/doc")
public class DocController {
    @Autowired
    DocService docService;

    @PostMapping()
    public Integer savedoc(@RequestBody Doc doc) {
        docService.save(doc);
        return doc.getId();
    }

    @PutMapping()
    public Doc updatedoc(@RequestBody Doc doc) throws Exception {
        docService.updateById(doc);
        return doc;
    }

    @DeleteMapping()
    public void deletedoc(@RequestBody Doc doc) throws Exception {
        docService.delete(doc);
    }

    @PostMapping("page")
    public List<Doc> page(@RequestBody Map<String, Object> params, int pageNum, int size) throws Exception {
        return docService.page(params, pageNum, size);
    }

    @PostMapping("like")
    public List<Doc> like(@RequestBody Doc doc) {
        return docService.like(doc);
    }

    @PostMapping("one")
    public Doc one(@RequestBody Doc doc) {
        return docService.getOne(doc);
    }

    @PostMapping("list")
    public List<Doc> list(@RequestBody Doc doc) {
        return docService.list(doc);
    }

    @PostMapping("between")
    public List<Doc> between(@RequestBody DocVo docVo) {
        return docService.between(docVo.getBegin(), docVo.getEnd());
    }
}

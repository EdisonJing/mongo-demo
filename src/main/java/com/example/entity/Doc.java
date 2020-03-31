package com.example.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Doc {
    private int id;
    private String mzh;
    private String type;
    private int version;
    private String name;
    private String sex;
    private LocalDate birthday;
    private LocalDateTime createTime;
}

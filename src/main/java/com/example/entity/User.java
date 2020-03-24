package com.example.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class User {
    private int id;
    private String name;
    private String sex;
    private LocalDate birthday;
    private LocalDateTime createTime;
}

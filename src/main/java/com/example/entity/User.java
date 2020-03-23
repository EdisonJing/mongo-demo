package com.example.entity;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;


@Data
public class User {
    private int id;
    private String name;
    private String sex;
    private LocalDate birthday;
}

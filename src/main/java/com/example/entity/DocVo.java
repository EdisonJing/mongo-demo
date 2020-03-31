package com.example.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DocVo {
    private LocalDate begin;
    private LocalDate end;
}

package com.resultMap1.dto;

import lombok.Data;

import java.util.List;

@Data
public class Professor {
    int id;
    String name;
    int departmentId;
    String phone;
    String email;
    String office;

    List<Lecture> lectures;
}

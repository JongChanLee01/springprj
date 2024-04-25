package com.resultMap2.dto;

import java.util.List;
import lombok.Data;

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

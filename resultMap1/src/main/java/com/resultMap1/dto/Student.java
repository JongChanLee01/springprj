package com.resultMap1.dto;

import lombok.Data;

@Data
public class Student {
    int id;
    String studentNo;
    String name;
    int departmentId;
    String phone;
    String sex;
    String email;

    Department department;
}

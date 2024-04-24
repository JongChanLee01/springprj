package com.mybatis2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    int id;
    String studentNo;
    String name;
    int departmentId;
    String email;
    String sex;
    String phone;
    String departmentName;
}

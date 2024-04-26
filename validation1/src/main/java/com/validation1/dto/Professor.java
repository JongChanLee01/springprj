package com.validation1.dto;

import lombok.Data;

@Data
public class Professor {
    int id;
    String name;
    int departmentId;
    String phone;
    String email;
    String office;

    String departmentName;
}

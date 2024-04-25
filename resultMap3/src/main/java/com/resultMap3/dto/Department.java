package com.resultMap3.dto;

import lombok.Data;
import java.util.List;

@Data
public class Department {
    int id;
    String name;
    String shortName;
    String phone;

    List<Student> students;
}

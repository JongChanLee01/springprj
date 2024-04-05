package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Data;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Student {
	String studentNo;
    String name;
    int departmentId;
    String email;
}

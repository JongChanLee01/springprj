package com.resultMap1.dto;

import lombok.Data;

@Data
public class Sugang {
    int id;
    int lectureId;
    int studentId;
    boolean repeated;
    boolean cancel;
    String grade;


    // student와 lecture 정보를 포함해서 조회해야 하므로 추가함
    Lecture lecture;
    Student student;
    
    // String studentNo;
    // String name;
    //
    // String title;
    // int year;
    // String semester;
}

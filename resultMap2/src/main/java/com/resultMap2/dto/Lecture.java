package com.resultMap2.dto;

import lombok.Data;

@Data
public class Lecture {
    int id;
    String title;
    int professorId;
    int year;
    String semester;
    String room;

    Professor professor;
}

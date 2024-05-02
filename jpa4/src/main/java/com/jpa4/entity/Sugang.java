package com.jpa4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sugang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    boolean repeated;
    boolean cancel;
    String grade;

    @ManyToOne
    @JoinColumn(name = "studentId")
    Student student;

    @ManyToOne
    @JoinColumn(name = "lectureId")
    Lecture lecture;
}

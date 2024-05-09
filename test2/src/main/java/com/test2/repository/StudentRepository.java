package com.test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test2.entity.Student;

public interface StudentRepository  extends JpaRepository<Student, Integer> {

    Student findByStudentNo(String studentNo);
}


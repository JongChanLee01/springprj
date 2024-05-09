package com.test2.controller;

import com.test2.entity.Student;
import com.test2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("students")
    public List<Student> students() {
        return studentRepository.findAll();
    }

    @GetMapping("student/{id}")
    public Student student(@PathVariable("id") int id) {
        return studentRepository.findById(id).get();
    }

    @PostMapping("student")
    public String insert(@RequestBody Student student) {
        studentRepository.save(student);
        return "success";
    }

    @PutMapping("student")
    public String update(@RequestBody Student student) {
        studentRepository.save(student);
        return "success";
    }

    @DeleteMapping("student/{id}")
    public String delete(@PathVariable("id") int id) {
        studentRepository.deleteById(id);
        return "success";
    }
}

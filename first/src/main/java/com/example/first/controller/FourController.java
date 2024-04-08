package com.example.first.controller;

import com.example.first.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class FourController {
    @GetMapping("four/test1")
    public List<Student> test1(Model model){
        List<Student> studentList = new ArrayList<>();
        Student s1 = new Student(1,"20001010","이순신","admin@naver.com");
        Student s2 = new Student(2,"20001210","강감찬","admin2@naver.com");
        Student s3 = new Student(3,"20001310","이방원","admin3@naver.com");
        Student s4 = new Student(4,"20001410","원균","admin4@naver.com");
        Student s5 = new Student(5,"20001510","장동건","admin5@naver.com");
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);
        
        model.addAttribute("studentList", studentList);
        return studentList;
    }
}

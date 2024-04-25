package com.resultMap3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.resultMap3.dto.Lecture;
import com.resultMap3.dto.Professor;
import com.resultMap3.mapper.LectureMapper;
import com.resultMap3.mapper.ProfessorMapper;

@Controller
@RequestMapping("lecture")
public class LectureController {

    @Autowired LectureMapper lectureMapper;
    @Autowired ProfessorMapper professorMapper;

    @RequestMapping("list")
    public String list(Model model) {
        List<Lecture> lectures = lectureMapper.findAll();
        for (Lecture lecture : lectures) {
            Professor professor = professorMapper.findOne(lecture.getProfessorId());
            lecture.setProfessor(professor);
        }
        model.addAttribute("lectures", lectures);
        return "lecture/list";
    }

}

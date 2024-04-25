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
@RequestMapping("professor")
public class ProfessorController {

    @Autowired LectureMapper lectureMapper;
    @Autowired ProfessorMapper professorMapper;

    @RequestMapping("list")
    public String list(Model model) {
        List<Professor> professors = professorMapper.findAll();
        for (Professor professor : professors) {
            List<Lecture> lectures = lectureMapper.findByProfessorId(professor.getId());
            professor.setLectures(lectures);
        }
        model.addAttribute("professors", professors);
        return "professor/list";
    }

}

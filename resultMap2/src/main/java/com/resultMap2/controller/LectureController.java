package com.resultMap2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.resultMap2.mapper.LectureMapper;

@Controller
@RequestMapping("lecture")
public class LectureController {

    @Autowired LectureMapper lectureMapper;

    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("lectures", lectureMapper.findAll());
        return "lecture/list";
    }

}

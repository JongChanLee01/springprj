package com.resultMap1.controller;

import com.resultMap1.mapper.LectureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("lecture")
public class LectureController {
    @Autowired
    LectureMapper lectureMapper;

    @RequestMapping("list")
    public String list(Model model){
        model.addAttribute("lectures", lectureMapper.findAll());
        return "lecture/list";
    }
}

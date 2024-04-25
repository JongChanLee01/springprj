package com.resultMap1.controller;

import com.resultMap1.mapper.SugangMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("sugang")
public class SugangController {

    @Autowired
    SugangMapper sugangMapper;

    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("sugangs", sugangMapper.findAll());
        return "sugang/list";
    }

}

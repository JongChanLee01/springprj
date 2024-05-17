package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping({"","/"})
    public String index() {
        // /WEB/views/index.jsp
        return "index"; // index.jsp 파일 이름 (확장자 제외)
    }
}

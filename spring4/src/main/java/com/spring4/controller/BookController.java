package com.spring4.controller;

import com.spring4.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    @Autowired
    BookMapper bookMapper;

    @RequestMapping("student/bookList")
    public String list(Model model){
        // 아래에서 book이 mapper에 있는데이터들을 이어 받고 jsp 파일에서
        //<c:forEach var="book" items="${ book }"> items로 값을 넘겨줌
        model.addAttribute("book", bookMapper.findAll());
        return "student/bookList";
    }
}

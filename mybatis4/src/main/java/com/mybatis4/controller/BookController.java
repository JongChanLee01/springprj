package com.mybatis4.controller;

import com.mybatis4.dto.Book;
import com.mybatis4.dto.Category;
import com.mybatis4.dto.Department;
import com.mybatis4.model.BookEdit;
import com.mybatis4.model.Pagination;
import com.mybatis4.model.StudentEdit;
import com.mybatis4.service.BookService;
import com.mybatis4.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("list")
    public String list(Model model, Pagination pagination, HttpServletRequest request) {
        log.info("get URL {} ", request.getRequestURL());

        pagination.setUrl(request.getRequestURL().toString());
        List<Book> books = bookService.findAll(pagination);
        model.addAttribute("books", books);
        return "book/list";
    }


    @GetMapping("create")
    public String create(Model model, Pagination pagination) {
        BookEdit bookEdit = new BookEdit();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("bookEdit", bookEdit);
        model.addAttribute("categories", categories);
        return "book/edit";
    }

    @PostMapping("create")
    public String create(Model model,
                         @Valid BookEdit bookEdit, BindingResult bindingResult, Pagination pagination) {
        try {
            bookService.insert(bookEdit, bindingResult, pagination);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            model.addAttribute("categories", categoryService.findAll());
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            return "book/edit";
        }
    }

    @GetMapping("edit")
    public String edit(Model model, int id, Pagination pagination) {
        BookEdit bookEdit = bookService.findOne(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("bookEdit", bookEdit);
        model.addAttribute("categories", categories);
        System.out.println(categories);
        return "book/edit";
    }




    @PostMapping(value="edit", params="cmd=save")
    public String edit(Model model,
                       @Valid BookEdit bookEdit, BindingResult bindingResult, Pagination pagination) {
        try {
            bookService.update(bookEdit, bindingResult);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            model.addAttribute("categories", categoryService.findAll());
            bindingResult.rejectValue("", null, "수정할 수 없습니다.");
            return "book/edit";
        }
    }

    @PostMapping(value="edit", params="cmd=delete")
    public String delete(Model model, Pagination pagination,
                         BookEdit bookEdit, BindingResult bindingResult) {
        try {
            bookService.delete(bookEdit.getId());
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            model.addAttribute("categories", categoryService.findAll());
            bindingResult.rejectValue("", null, "삭제할 수 없습니다.");
            return "book/edit";
        }
    }
}

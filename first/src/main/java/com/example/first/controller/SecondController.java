package com.example.first.controller;

import com.example.first.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("second")
public class SecondController {
    @GetMapping("test1")
    public String test1(Model model){
        model.addAttribute("message","welcome to my world");
        return "second/test1";
    }
    @GetMapping("test2")
    public String test2(Model model){
        Product product = new Product("맥주",2000);
        model.addAttribute("product",product);
        return "second/test2";
    }

    @GetMapping("loop")
    public String loop(Model model){
        List<Product> productList = new ArrayList<>();

        productList.add(new Product("소주", 4000));
        productList.add(new Product("양주", 34000));
        productList.add(new Product("맥주", 14000));

        log.info("제품명 :"+productList.toString());
        model.addAttribute("productList", productList);
        return "second/loop";
    }
}

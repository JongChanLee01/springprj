package com.shop2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {
    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model){
        return "item/itemForm";
    }
}

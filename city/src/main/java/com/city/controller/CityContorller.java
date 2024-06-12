package com.city.controller;

import com.city.entity.City;
import com.city.entity.District;
import com.city.model.CityEdit;
import com.city.model.Pagination;
import com.city.repository.DistrictRepository;
import com.city.service.CityService;
import com.city.service.DistrictService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("city")
public class CityContorller {
    @Autowired
    CityService cityService;
    @Autowired
    DistrictService districtService;

    @GetMapping("list")
    public String list(Model model, Pagination pagination){
        List<City> cities = cityService.findAll(pagination);

        model.addAttribute("cities", cities);
        model.addAttribute("orders", cityService.getOrders());

        return "city/list";
    }

    @GetMapping("create")
    public String create(Model model, Pagination pagination){
        CityEdit cityEdit = new CityEdit();
        List<District> districts = districtService.findAll();

        model.addAttribute("cityEdit", cityEdit);
        model.addAttribute("districts", districts);


        return "city/edit";
    }

    @PostMapping("create")
    public String create(Model model, Pagination pagination,
                         @Valid CityEdit cityEdit, BindingResult bindingResult){
        try {
            cityService.insert(cityEdit, bindingResult, pagination);
            return "redirect:list?"+ pagination.getQueryString();
        } catch (Exception e){
            model.addAttribute("districts", districtService.findAll());
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            return "city/edit";
        }
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id, Pagination pagination){
        CityEdit cityEdit = cityService.findOne(id);
        log.info("id:"+String.valueOf(id));
        List<District> districts = districtService.findAll();

        model.addAttribute("cityEdit",cityEdit);
        model.addAttribute("districts",districts);

        return "city/edit";
    }

    @PostMapping(value = "edit", params = "cmd=save")
    public String edit(Model model, Pagination pagination,
                       @Valid CityEdit cityEdit, BindingResult bindingResult){
        try {
            cityService.update(cityEdit, bindingResult);
            return "redirect:list?" + pagination.getQueryString();
        }catch (Exception e){
            model.addAttribute("districts",districtService.findAll());
            bindingResult.rejectValue("",null,"수정할 수 없습니다.");
            return "city/edit";
        }
    }

    @PostMapping(value = "edit", params = "cmd=delete")
    public String delete(Model model, Pagination pagination,
                         CityEdit cityEdit, BindingResult bindingResult){
        try {
            cityService.delete(cityEdit.getId());
            return "redirect:list?" + pagination.getQueryString();
        }catch (Exception e){
            model.addAttribute("districts", districtService.findAll());
            bindingResult.rejectValue("",null,"삭제할 수 없습니다.");
            return "city/edit";
        }
    }
}

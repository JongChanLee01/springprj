package com.city.service;

import com.city.entity.City;
import com.city.model.CityEdit;
import com.city.model.Pagination;
import com.city.repository.CityReopsitory;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class CityService {

    @Data @AllArgsConstructor
    public class Order {
        int value;
        String label;
    }

    Order[] orders = new Order[]{
            new Order(0,"정렬 순서"),
            new Order(1,"도시명 오름차순"),
            new Order(2,"인구 내림차순"),
            new Order(3,"면적 내림차순"),
            new Order(4,"소속도명 오름차순")
    };

    static Sort[] sorts = new Sort[]{
            Sort.by(Sort.Direction.ASC, "id"),
            Sort.by(Sort.Direction.ASC, "name"),
            Sort.by(Sort.Direction.DESC, "population"),
            Sort.by(Sort.Direction.DESC, "area"),
            Sort.by(Sort.Direction.ASC, "districtId")
    };

    public Order[] getOrders() {
        return orders;
    }


    @Autowired
    public CityReopsitory cityReopsitory;
    ModelMapper modelMapper = new ModelMapper();

    public CityEdit findOne(int id) {
        City cityEntity = cityReopsitory.findById(id).orElse(null);
        return toEditModel(cityEntity);
    }

    public City findByName(String name) {
        return cityReopsitory.findByName(name);
    }

    public List<City> findAll(Pagination pagination){
        int orderIndex = pagination.getOd();
        PageRequest pageRequest = PageRequest.of(pagination.getPg()-1, pagination.getSz(), sorts[orderIndex]);
        Page<City> page;

        if (pagination.getSt().length() == 0) {
            page = cityReopsitory.findAll(pageRequest);
        } else {
            page = cityReopsitory.findByNameStartsWith(
                    pagination.getSt(), pageRequest
            );
        }
        pagination.setRecordCount((int)page.getTotalElements());
        return page.getContent();
    }

    // public List<City> findAll(){
    //     return cityReopsitory.findAll();
    // }


    public void insert(CityEdit cityEdit, BindingResult bindingResult,
                       Pagination pagination) throws Exception {
        if (hasErrors(cityEdit, bindingResult)){
            throw new Exception("입력 데이터 오류");
        }
        City city = toEntity(cityEdit);
        cityReopsitory.save(city);
    }

    public void update(CityEdit cityEdit, BindingResult bindingResult) throws Exception{
        if (hasErrors(cityEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        City city = toEntity(cityEdit);
        cityReopsitory.save(city);
    }

    public void delete(int id){
        cityReopsitory.deleteById(id);
    }

    public boolean hasErrors(CityEdit cityEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return true;
        City city2 = findByName(cityEdit.getName());
        if (city2 != null && city2.getId() != cityEdit.getId()) {
            bindingResult.rejectValue("name", null, "도시명이 중복됩니다.");
            return true;
        }
        return false;
    }

    public City toEntity(CityEdit cityEdit) {
        return modelMapper.map(cityEdit, City.class);
    }



    public CityEdit toEditModel(City cityEntity){
        return modelMapper.map(cityEntity, CityEdit.class);
    }
}

package com.mybatis4.service;

import com.mybatis4.dto.Category;
import com.mybatis4.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    public CategoryMapper categoryMapper;

    public List<Category> findAll() {
        return categoryMapper.findAll();
    }
}

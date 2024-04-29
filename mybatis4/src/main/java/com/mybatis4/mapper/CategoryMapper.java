package com.mybatis4.mapper;

import com.mybatis4.dto.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM category")
    List<Category> findAll();
}

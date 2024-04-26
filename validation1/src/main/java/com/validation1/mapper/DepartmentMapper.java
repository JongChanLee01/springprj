package com.validation1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.validation1.dto.Department;

@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department")
    List<Department> findAll();
}

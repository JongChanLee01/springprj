package com.spring4.mapper;

import com.spring4.dto.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface DepartmentMapper {
    @Select("""
            SELECT * FROM department
            """)
    List<Department> findAll();
}

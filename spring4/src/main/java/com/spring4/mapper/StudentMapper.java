package com.spring4.mapper;

import java.util.List;

import com.spring4.dto.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface StudentMapper {
    @Select("""
             SELECT s.*, d.name departmentName
             FROM student s JOIN department d ON s.departmentId = d.id """)
    List<Student> findAll();
}

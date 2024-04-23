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

    @Select("SELECT s.*, d.departmentName FROM student s LEFT JOIN department d ON s.departmentId = d.id"
            + " WHERE s.name LIKE #{name}")
    List<Student> findByName(String name);

    @Select("SELECT s.*, d.departmentName FROM student s LEFT JOIN department d ON s.departmentId = d.id"
            + " WHERE s.id = #{id}")
    Student findById(int id);
}

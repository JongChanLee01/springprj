package com.order1.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.order1.dto.Student;

@Mapper
public interface StudentMapper {

    @Select("""
        SELECT s.*, d.departmentName departmentName
        FROM student s JOIN department d ON s.departmentId = d.id
        ORDER BY
          (CASE WHEN #{order} = 0 THEN s.studentNo END) ASC,
          (CASE WHEN #{order} = 1 THEN s.studentNo END) DESC,
          (CASE WHEN #{order} = 2 THEN s.name END) ASC,
          (CASE WHEN #{order} = 3 THEN s.name END) DESC,
          (CASE WHEN #{order} = 4 THEN d.departmentName END) ASC """)
    List<Student> findAll(int order);
}

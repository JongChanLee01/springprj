package com.resultMap1.mapper;

import java.util.List;

import com.resultMap1.dto.Student;
import com.resultMap1.dto.Sugang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SugangMapper {

    @ResultMap("findAll")
    @Select("""
      SELECT g.*, s.studentNo, s.name, l.title, l.year, l.semester
      FROM sugang g
        JOIN student s ON g.studentId = s.id
        JOIN lecture l ON g.lectureId = l.id
      ORDER BY s.studentNo
    """)
    List<Sugang> findAll();
}

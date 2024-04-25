package com.resultMap1.mapper;

import com.resultMap1.dto.Professor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.time.chrono.MinguoDate;
import java.util.List;

@Mapper
public interface ProfessorMapper {
    @ResultMap("findAll")
    @Select("""
       SELECT p.*, l.id lectureId, l.title, l.year, l.semester, l.room
       FROM professor p JOIN lecture l
       ON p.id = l.professorId
    """)
    List<Professor> findAll();
}

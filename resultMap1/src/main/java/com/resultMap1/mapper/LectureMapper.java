package com.resultMap1.mapper;

import com.resultMap1.dto.Lecture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LectureMapper {
    @ResultMap("findAll")
    @Select("""
        SELECT l.*, p.name, p.departmentId, p.phone, p.email, p.office
        FROM lecture l JOIN professor p
        ON l.professorId = p.id                
    """)
    List<Lecture> findAll();
}

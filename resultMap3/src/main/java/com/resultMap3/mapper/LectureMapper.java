package com.resultMap3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.resultMap3.dto.Lecture;

@Mapper
public interface LectureMapper {

    @Select("SELECT * FROM lecture")
    List<Lecture> findAll();

    @Select("SELECT * FROM lecture WHERE professorId = #{professorId}")
    List<Lecture> findByProfessorId(int professorId);
}

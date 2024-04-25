package com.resultMap3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.resultMap3.dto.Professor;

@Mapper
public interface ProfessorMapper {

    @Select("SELECT * FROM professor")
    List<Professor> findAll();

    @Select("SELECT * FROM professor WHERE id = #{id}")
    Professor findOne(int id);
}


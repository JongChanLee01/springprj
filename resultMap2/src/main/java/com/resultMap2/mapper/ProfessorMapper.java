package com.resultMap2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.resultMap2.dto.Professor;

@Mapper
public interface ProfessorMapper {

    @Results({
            @Result(property="id", column="id", id=true),
            @Result(property="lectures", column="id",
                    many=@Many(select="com.resultMap2.mapper.LectureMapper.findByProfessorId"))
    })
    @Select("SELECT * FROM professor")
    List<Professor> findAll();

    @Select("SELECT * FROM professor WHERE id = #{id}")
    Professor findOne(int id);
}

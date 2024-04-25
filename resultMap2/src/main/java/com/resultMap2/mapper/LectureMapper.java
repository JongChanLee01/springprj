package com.resultMap2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.resultMap2.dto.Lecture;

@Mapper
public interface LectureMapper {
    @Results({
            @Result(property="professorId", column="professorId"),
            @Result(property="professor", column="professorId",
                    one=@One(select="com.resultMap2.mapper.ProfessorMapper.findOne"))
    })
    @Select("SELECT * FROM lecture")
    List<Lecture> findAll();

    @Select("SELECT * FROM lecture WHERE professorId = #{professorId}")
    List<Lecture> findByProfessorId(int professorId);
}

package com.validation1.mapper;

import com.validation1.dto.Professor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProfessorMapper {
    @Select("SELECT * FROM professor WHERE id = #{id}")
    Professor findOne(int id);

    @Select("SELECT * FROM professor WHERE office = #{office}")
    Professor findByOffice(String office);

    @Select("""
        SELECT p.*, d.departmentName
        FROM professor p JOIN department d ON p.departmentId = d.id
    """)
    List<Professor> findAll();

    @Insert("""
        INSERT professor (name, departmentId, phone, email, office)
        VALUES (#{name}, #{departmentId}, #{phone}, #{email}, #{office)
    """)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Professor professor);

    @Update("""
        UPDATE professor SET
          name = #{name},
          departmentId = #{departmentId},
          phone = #{phone},
          email = #{email},
          office = #{office}
        WHERE id = #{id} """)
    void update(Professor professor);

    @Delete("DELETE FROM professor WHERE id = #{id}")
    void delete(int id);
}

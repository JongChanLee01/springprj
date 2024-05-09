package com.test1.mapper;

import com.test1.dto.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student findById(int id);

    @Select("SELECT * FROM student WHERE studentNo = #{studentNo}")
    Student findByStudentNo(String studentNo);

    @Select("SELECT s.*, d.departmentName departmentName " +
            "FROM student s LEFT JOIN department d ON s.departmentId = d.id")
    List<Student> findAll();

    @Update("UPDATE student SET                " +
            "  studentNo = #{studentNo},       " +
            "  name = #{name},                 " +
            "  departmentId = #{departmentId}, " +
            "  phone = #{phone},               " +
            "  sex = #{sex},                   " +
            "  email = #{email}                " +
            "WHERE id = #{id}")
    void update(Student student);

    @Insert("INSERT student (studentNo, name, departmentId, phone, sex, email) " +
            "VALUES (#{studentNo}, #{name}, #{departmentId}, #{phone}, #{sex}, #{email})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Student student);

    @Delete("DELETE FROM student WHERE id = #{id};")
    void deleteById(int id);
}

package com.mybatis4.mapper;

import java.util.List;

import com.mybatis4.model.Pagination;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mybatis4.dto.Student;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student findOne(int id);

    @Select("SELECT * FROM student WHERE studentNo = #{studentNo}")
    Student findByStudentNo(String studentNo);

    // @Select("""
    //     SELECT s.*, d.departmentName departmentName
    //     FROM student s LEFT JOIN department d ON s.departmentId = d.id """)
    // List<Student> findAll();
    @Select("""
            SELECT s.*, d.departmentName departmentName
            FROM student s LEFT JOIN department d ON s.departmentId = d.id
            
            WHERE #{st} = '' OR
                  s.studentNo = #{st} OR
                  s.name LIKE CONCAT('%', #{st}, '%') OR
                  d.departmentName LIKE CONCAT('%', #{st}, '%')
            ORDER BY s.id
            LIMIT #{firstRecordIndex}, #{sz} 
            """)
    List<Student> findAll(Pagination pagination);



    // @Select("SELECT COUNT(id) FROM student")
    // int getCount();
    @Select("""
            SELECT COUNT(*)
            FROM student s LEFT JOIN department d ON s.departmentId = d.id
            WHERE #{st} = '' OR
              s.studentNo = #{st} OR
              s.name LIKE CONCAT('%', #{st}, '%') OR
              d.departmentName LIKE CONCAT('%', #{st}, '%') """)
    int getCount(Pagination pagination);



    @Insert("""
        INSERT student (studentNo, name, departmentId, phone, sex, email)
        VALUES (#{studentNo}, #{name}, #{departmentId}, #{phone}, #{sex}, #{email}) """)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Student student);

    @Update("""
        UPDATE student SET
          studentNo= #{studentNo},
          name = #{name},
          departmentId = #{departmentId},
          phone = #{phone},
          sex = #{sex},
          email = #{email}
        WHERE id = #{id} """)
    void update(Student student);

    @Delete("DELETE FROM student WHERE id = #{id}")
    void delete(int id);
}


package com.jpa4.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jpa4.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>  {

    Student findByStudentNo(String studentNo);
    List<Student> findByName(String name);
    List<Student> findByNameStartsWith(String name);
    List<Student> findByDepartmentName(String name);
    List<Student> findByDepartmentNameStartsWith(String name);

    List<Student> findByOrderByName();
    List<Student> findByOrderByNameDesc();
    List<Student> findByDepartmentIdOrderByNameDesc(int id);

    List<Student> findBySugangsLectureTitle(String title);
    List<Student> findByDepartmentProfessorsName(String name);
}

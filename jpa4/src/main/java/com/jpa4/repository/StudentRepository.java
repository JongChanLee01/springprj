package com.jpa4.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jpa4.entity.Student;
import org.springframework.data.jpa.repository.Query;

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

    // 최신 아이디가 먼저 나오게하기
    List<Student> findAllByOrderByIdDesc();

    // 학번으로 오름차순 정렬하기
    List<Student> findAllByOrderByStudentNo();


    Page<Student> findByStudentNoOrNameStartsWithOrDepartmentNameStartsWith(
            String studentNo, String name, String departmentName, Pageable pageable);


    @Query("SELECT s.studentNo, s.name, SIZE(s.sugangs) FROM Student s ORDER BY SIZE(s.sugangs) DESC")
    List<Object[]> findSugangCount();

    @Query("SELECT s FROM Student s JOIN s.sugangs g WHERE g.lecture.title = ?1")
    List<Student> findByLectureTite(String title);

    @Query("""
         SELECT s
         FROM Student s
         WHERE EXISTS (SELECT g.lecture
                       FROM s.sugangs g
                       WHERE g.lecture.professor.id = ?1 OR g.lecture.professor.name = ?2) """)
    List<Student> findByProfessorIdOrProfessorName(int id, String name);
    // 위의 줄을 아래처럼 구현할수도 있음
    // List<Student> findBySugangsLectureProfessorIdOrSugangsLectureProfessorName(int id, String name);

    @Query("""
         SELECT s
         FROM Student s
         WHERE EXISTS (SELECT g.lecture
                        FROM s.sugangs g
                        WHERE g.lecture.professor.id = :id OR g.lecture.professor.name = :name) """)
    List<Student> findByProfessorNameOrProfessorId(String name, int id);

}

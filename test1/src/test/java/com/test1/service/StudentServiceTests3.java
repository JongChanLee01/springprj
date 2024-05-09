package com.test1.service;

import com.test1.dto.Student;
import com.test1.mapper.StudentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceTests3 {
    @Mock
    StudentMapper studentMapper;
    @InjectMocks
    StudentService studentService;

    Student student;
    List<Student> students;

    @BeforeEach
    public void prepare() {
        // 테스트 메소드들에서 사용할 객체를 미리 생성함
        this.student = new Student();
        this.student.setId(337);
        this.student.setStudentNo("201132011");
        this.student.setName("임꺽정");
        this.student.setDepartmentId(2);
        this.student.setPhone("010-123-4567");
        this.student.setEmail("lim@skhu.ac.kr");

        this.students = new ArrayList<>();
        this.students.add(this.student);
    }

    @Test
    public void test_findAll() {
        // 테스트 하기 위해서 mock 객체를 설정함.
        Mockito.when(studentMapper.findAll())
                .thenReturn(this.students);

        List<Student> students2 = studentService.findAll();

        // 테스트 결과 확인
        assertEquals(this.students, students2);
        Mockito.verify(studentMapper).findAll();
    }

    @Test
    public void test_deleteById() {
        studentService.deleteById(3);  // 삭제
        Mockito.verify(studentMapper).deleteById(3); // 삭제 호출되었는지 확인
    }

    @Test
    public void test_hasErrors_학번중복() {
        Mockito.when(studentMapper.findByStudentNo(this.student.getStudentNo()))
                .thenReturn(this.student);
        BindingResult bindingResult = new BeanPropertyBindingResult(this.student, "student");
        assertTrue(studentService.hasErrors(student, bindingResult));
        Mockito.verify(studentMapper).findByStudentNo(this.student.getStudentNo());
    }

    @Test
    public void test_hasErrors_Ok() {
        Mockito.when(studentMapper.findByStudentNo(ArgumentMatchers.anyString()))
                .thenReturn(null);
        BindingResult bindingResult = new BeanPropertyBindingResult(this.student, "student");
        assertFalse(studentService.hasErrors(student, bindingResult));
        Mockito.verify(studentMapper).findByStudentNo(this.student.getStudentNo());
    }

}

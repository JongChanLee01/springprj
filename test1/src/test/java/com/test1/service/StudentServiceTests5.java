package com.test1.service;

import com.test1.dto.Student;
import com.test1.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTests5 {
    @Mock
    StudentMapper studentMapper;
    @InjectMocks
    StudentService studentService;

    @Mock
    Student student;

    @Test
    public void test_insert_중복() {
        Mockito.when(student.getStudentNo()).thenReturn("201132011");
        Mockito.when(studentMapper.findByStudentNo("201132011")).thenReturn(student);

        studentService.insert(this.student);  // 저장

        Mockito.verify(student).getStudentNo();
        Mockito.verify(studentMapper).findByStudentNo("201132011");
        Mockito.verify(studentMapper, Mockito.times(0)).insert(ArgumentMatchers.any());
    }

}

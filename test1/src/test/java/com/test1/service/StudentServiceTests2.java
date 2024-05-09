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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentServiceTests2 {

    // 목업 -> 가짜 StudentMapper 객체(테스트용 mock 객체)를 만들어서 실행하기 때문에 DB를 사용하지 않음
    // 따라서 rollback할 필요도 없음
    @Mock
    StudentMapper studentMapper;

    @InjectMocks
    StudentService studentService;

    Student student;

    
    // @BeforeEach는 @Test 테스트 메소드가 호출되기 전에 @BeforEach가 자동으로 먼저 호출됨.
    // 모든 테스트 객체가 호출되기 전에 @BeforeEach가 호출됨. 테스트가 여러개면 여러번 호출됨
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
    }

    @Test
    public void test_findById() {
        // 테스트 하기 위해서 mock 객체를 설정함.
        Mockito.when(studentMapper.findById(ArgumentMatchers.anyInt()))
                .thenReturn(this.student);

        Student student2 = studentService.findById(this.student.getId());

        // 테스트 결과 확인
        assertEquals(this.student, student2);
        Mockito.verify(studentMapper).findById(this.student.getId());
    }

}

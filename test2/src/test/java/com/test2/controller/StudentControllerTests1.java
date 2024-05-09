package com.test2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.test2.entity.Student;
import com.test2.repository.StudentRepository;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class StudentControllerTests1 {

    // 서버 URL을 요청하기 위한 클라이언트 객체
    // 마치 웹브라우저가 서버 URL을 요청하는 것 처럼,
    // 테스트 코드가 서버 URL을 요청하기 위해 사용할 객체
    @Autowired WebTestClient webClient;

    // 서버가 출력한 데이터가 맞는지 확인하기 위해
    // DB에서 데이터를 조회해야 한다.
    @Autowired StudentRepository studentRepository;

    // 테스트에 필요한 데이터 객체
    Student student1, student2;

    // 각각의 @Test 테스트 메소드가 실행되기 전에 자동 호출되는 메소드
    // 테스트에 필요한 데이터를 준비한다
    @Before @BeforeEach
    public void prepare() {
        // 테스트에 필요한 데이터 객체를 생성한다.
        student1 = new Student();
        student1.setName("홍길동");
        student1.setStudentNo("98769876");
        student1.setEmail("h@skhu.net");
        student1.setDepartmentId(1);
        student1.setPhone("010-999-8888");
        student1.setSex("남");

        // 혹시 테스트용 데이터가 DB에 남아있다면 삭제한다.
        Student s1 = studentRepository.findByStudentNo("98769876");
        if (s1 != null) studentRepository.deleteById(s1.getId());

        // 테스트에 필요한 데이터 객체를 생성한다.
        student2 = new Student();
        student2.setName("성춘향");
        student2.setStudentNo("87878787");
        student2.setEmail("s@skhu.net");
        student2.setDepartmentId(2);
        student2.setPhone("010-888-7777");
        student2.setSex("여");

        // 혹시 테스트용 데이터가 DB에 남아있다면 삭제한다.
        Student s2 = studentRepository.findByStudentNo("87878787");
        if (s2 != null) studentRepository.deleteById(s2.getId());
    }

    @Test
    public void test_students() throws Exception {
        // students URL을 GET 방식으로 서버에 요청하고 그 결과 데이터를 받는다.
        // 그 결과 테이터가 List<Studnet> 타입인지 확인한다.
        var s1 = webClient.get().uri("students")
                .exchange().expectStatus().isOk()
                .expectBodyList(Student.class).returnResult().getResponseBody();

        // 서버가 출력한 데이터가 맞는지, DB에서 조회한 데이터와 비교한다.
        List<Student> s2 = studentRepository.findAll();
        assertEquals(s1, s2);
    }

    @Test
    public void test_student() throws Exception {
        // 학생 한 명을 서버에 요청하기 위해 필요한 id를 구한다
        int id = studentRepository.findAll().get(0).getId();

        // student/{id} URL을 GET 방식으로 서버에 요청하고 그 결과 데이터를 받는다.
        // 그 결과 테이터가 Student 타입인지 확인한다.
        var s1 = webClient.get().uri("student/" + id)
                .exchange().expectStatus().isOk()
                .expectBody(Student.class).returnResult().getResponseBody();

        // 서버가 출력한 데이터가 맞는지, DB에서 조회한 데이터와 비교한다.
        Student s2 = studentRepository.findById(id).get();
        assertEquals(s1, s2);
    }

    @Test
    public void test_insert_update_delete() {
        // insert 테스트
        // student URL을 POST 방식으로 서버에 요청하고 그 결과 데이터를 받는다.
        //   student1 객체를 request parameter로 서버에 전송한다.
        //   이 request parameter를 JSON 형식으로 전송한다.
        // 요청 결과로 서버가 출력한 데이터가 "success" 문자열인지 확인한다.
        String r = webClient.post().uri("student")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(student1), Student.class)
                .exchange().expectStatus().isOk()
                .expectBody(String.class).returnResult().getResponseBody();
        assertEquals(r, "success");

        // DB에 insert가 잘 되었는지 확인한다.
        // insert될 때 자동으로 생성된 id 필드 값을 student1 객체의 id에 대입해서 비교해야 한다.
        Student s = studentRepository.findByStudentNo(student1.getStudentNo());
        int id = s.getId();
        student1.setId(id);
        assertEquals(student1, s);

        // update 테스트
        // 방금 insert한 레코드를 update 하기 위한 요청을 전송한다.
        // student/{id} URL을 PUT 방식으로 서버에 요청하고 그 결과 데이터를 받는다.
        //    student2 객체를 request parameter로 서버에 전송한다.
        //    student1 객체의 id를 student2 객체에 대입했으므로,
        //    그 id의 레코드가 student2 객체의 내용으로 update 될 것이다.
        //    request parameter를 JSON 형식으로 전송한다.
        // 요청 결과 서버가 출력한 데이터가 "success" 문자열인지 확인한다.
        student2.setId(id);
        r = webClient.put().uri("student")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(student2), Student.class)
                .exchange().expectStatus().isOk()
                .expectBody(String.class).returnResult().getResponseBody();
        assertEquals(r, "success");

        // update가 잘 되었는지 확인한다.
        s = studentRepository.findById(id).get();
        assertEquals(student2, s);

        // delete 테스트
        // 방금 update한 레코드를 삭제하기 위한 요청을 전송한다.
        // student/{id} URL을 DELETE 방식으로 서버에 요청하고 그 결과 데이터를 받는다.
        r = webClient.delete().uri("student/" + id)
                .exchange().expectStatus().isOk()
                .expectBody(String.class).returnResult().getResponseBody();
        assertEquals(r, "success");

        // DB에서 삭제되었는지 확인한다.
        assertTrue(studentRepository.findById(id).isEmpty());
    }

}


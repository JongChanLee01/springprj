package com.shop2;

import com.shop2.entity.Member;
import com.shop2.entity.Question;
import com.shop2.repository.QuestionRepository;
import com.shop2.service.MemberService;
import com.shop2.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class Shop2ApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private MemberService memberService;
	@Autowired
	private QuestionService questionService;

	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("test");
		q1.setContent("test content");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);  // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("test2.");
		q2.setContent("test2 content");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장
	}

	// @Test
	// void testJpa12() {
	// 	for (int i = 1; i <= 300; i++) {
	// 		String subject = String.format("테스트 데이터입니다:[%03d]", i);
	// 		String content = "내용무";
	//
	// 		// Member member = memberService.getUser("홍길동");
	// 		this.questionService.create(subject, content);
	// 		// this.questionService.create(subject, content, member);
	// 		this.questionService.create(subject, content);
	// 	}
	// }
}

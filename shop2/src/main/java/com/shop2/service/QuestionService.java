package com.shop2.service;

import com.shop2.entity.Answer;
import com.shop2.entity.Member;
import com.shop2.entity.Question;
import com.shop2.exception.DataNotFoundException;
import com.shop2.repository.QuestionRepository;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question getQuestion(Integer id) {
        // https://mangkyu.tistory.com/70
        // Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로,
        // 참조하더라도 NPE가 발생하지 않도록 도와준다.
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            // 조회수 추가
            Question question1 = question.get();
            question1.setView(question1.getView()+1);
            this.questionRepository.save(question1);

            // return question.get();

            // 조회수 추가
            return question1;
        }else {
            throw new DataNotFoundException("question not found");
        }
    }

    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 5, Sort.by(sorts));
        // Specification<Question> spec = search(kw);
        // 자바 코드로 쿼리 생성
        // return this.questionRepository.findAll(spec, pageable);

        // 직접 쿼리 작성으로 생성
        return this.questionRepository.findAllByKeyword(kw, pageable);
    }

    public void create(String subject, String content, Member member){
        Question question = new Question();

        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setAuthor(member);
        this.questionRepository.save(question);
    }

    // 수정
    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    // 삭제
    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    // 추천
    public void vote(Question question, Member member) {
        question.getVoter().add(member);
        this.questionRepository.save(question);
    }

    // 검색 기능
    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Question, Member> m1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, Member> m2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(m1.get("name"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(m2.get("name"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }
}
